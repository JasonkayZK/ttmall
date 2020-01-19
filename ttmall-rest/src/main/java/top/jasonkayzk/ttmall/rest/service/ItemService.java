package top.jasonkayzk.ttmall.rest.service;

import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;

public interface ItemService {

    TTMallCommonResult getItemBaseInfo(long itemId);

    TTMallCommonResult getItemDesc(long itemId);

    TTMallCommonResult getItemParam(long itemId);

}
