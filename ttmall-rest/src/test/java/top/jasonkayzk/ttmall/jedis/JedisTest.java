package top.jasonkayzk.ttmall.jedis;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

@Ignore
public class JedisTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JedisTest.class);

    @Test
    public void testJedisSingle() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //调用jedis对象的方法，方法名称和redis的命令一致
        jedis.set("key1", "jedis test");
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭jedis
        jedis.close();
    }

    /**
     * 使用连接池
     */
    @Test
    public void testJedisPool() {
        //创建jedis连接池
        JedisPool pool = new JedisPool("127.0.0.1", 6379);

        //从连接池中获得Jedis对象
        Jedis jedis = pool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);

        //关闭jedis对象
        jedis.close();
        pool.close();
    }

    /**
     * 单机版测试
     */
    @Test
    public void testSpringJedisSingle() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        jedis.close();
        pool.close();
    }

    /**
     * 集群版测试
     */
    @Test
    public void testJedisCluster() {
        LOGGER.debug("调用redisCluster开始");

        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 7001));
        nodes.add(new HostAndPort("127.0.0.1", 7002));
        nodes.add(new HostAndPort("127.0.0.1", 7003));

        LOGGER.info("创建一个JedisCluster对象");
        JedisCluster cluster = new JedisCluster(nodes);

        LOGGER.debug("设置key1的值为1000");
        cluster.set("key1", "1000");

        LOGGER.debug("从Redis中取key1的值");
        String string = cluster.get("key1");
        System.out.println(string);
        cluster.close();
    }

    @Test
    public void testSpringJedisCluster() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
        String string = jedisCluster.get("key1");
        System.out.println(string);
        jedisCluster.close();
    }

}
