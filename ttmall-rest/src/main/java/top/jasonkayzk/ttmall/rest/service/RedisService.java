package top.jasonkayzk.ttmall.rest.service;

import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;

/**
 * @author zk
 */
public interface RedisService {

    /**
     * 同步内容
     *
     * @param contentCid 内容Id
     *
     * @return 是否成功
     */
    TTMallCommonResult syncContent(long contentCid);

}
