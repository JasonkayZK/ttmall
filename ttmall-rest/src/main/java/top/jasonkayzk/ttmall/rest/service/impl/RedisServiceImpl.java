package top.jasonkayzk.ttmall.rest.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.ExceptionUtil;
import top.jasonkayzk.ttmall.rest.dao.JedisClient;
import top.jasonkayzk.ttmall.rest.service.RedisService;

/**
 * Redis相关服务
 *
 * @author zk
 */
@Service
public class RedisServiceImpl implements RedisService {

    private final JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    public RedisServiceImpl(JedisClient jedisClient) {
        this.jedisClient = jedisClient;
    }

    @Override
    public TTMallCommonResult syncContent(long contentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        } catch (Exception e) {
            e.printStackTrace();
            return TTMallCommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TTMallCommonResult.ok();
    }

}
