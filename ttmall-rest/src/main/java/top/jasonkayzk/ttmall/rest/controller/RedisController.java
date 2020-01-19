package top.jasonkayzk.ttmall.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.rest.service.RedisService;

/**
 * 缓存同步Controller
 *
 * @author zk
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public TTMallCommonResult contentCacheSync(@PathVariable Long contentCid) {
        return redisService.syncContent(contentCid);
    }

}
