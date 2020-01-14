package top.jasonkayzk.ttmall.service;

import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.pojo.TbItem;

public interface ItemService {

    /**
     * 根据Id获取商品
     *
     * @param itemId 商品id
     * @return 商品
     */
    TbItem getItemById(long itemId);

    /**
     * 根据商品页数, 每页个数返回List商品对象
     *
     * @param page 页数
     * @param rows 每页行数
     * @return List商品对象
     */
    EUDataGridResult getItemList(int page, int rows);

    /**
     * 加入商品
     *
     * @param item 商品
     * @param desc 描述
     * @param itemParam 商品参数
     *
     * @return 处理结果
     */
    TTMallCommonResult createItem(TbItem item, String desc, String itemParam) throws Exception;

}
