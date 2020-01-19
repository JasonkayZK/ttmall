package top.jasonkayzk.ttmall.util;

import org.junit.Ignore;
import org.junit.Test;
import top.jasonkayzk.ttmall.common.utils.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

@Ignore
public class HttpClientTest {

    @Test
    public void doGet() {
        System.out.println(HttpClientUtil.doGet("http://www.baidu.com"));
    }

    @Test
    public void doGetWithParam() {
        Map<String, String> param = new HashMap<>();
        param.put("wd", "helloWorld");
        System.out.println(HttpClientUtil.doGet("http://www.baidu.com/s", param));
    }

    @Test
    public void doPost() {
        System.out.println(HttpClientUtil.doPost("http://localhost:8082/httpclient/post.action"));
    }

    @Test
    public void doPostWithParam() {
        Map<String, String> param = new HashMap<>();
        param.put("username", "hello");
        param.put("password", "world");
        param.put("test", "test");

        System.out.println(HttpClientUtil.doPost("http://localhost:8082/httpclient/post.action", param));
    }

}
