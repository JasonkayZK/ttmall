package top.jasonkayzk.ttmall.service;

import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.pojo.TbContent;

/**
 * 商品详情
 *
 * @author zk
 */
public interface ContentService {

    /**
     * 加入商品详情
     *
     * @param content 商品详情
     *
     * @return 处理响应
     */
    TTMallCommonResult insertContent(TbContent content);

}
