package top.jasonkayzk.ttmall.portal.service;

import top.jasonkayzk.ttmall.portal.pojo.ItemInfo;

public interface ItemService {

    ItemInfo getItemById(Long itemId);

    String getItemDescById(Long itemId);

    String getItemParam(Long itemId);
}
