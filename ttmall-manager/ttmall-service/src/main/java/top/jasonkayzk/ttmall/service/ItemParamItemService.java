package top.jasonkayzk.ttmall.service;

/**
 * 展示商品规格参数
 *
 * @author zk
 */
public interface ItemParamItemService {

    /**
     * 根据商品id查询规格参数
     *
     * @param itemId 产品Id
     *
     * @return 根据产品Id获得产品参数
     */
    String getItemParamByItemId(Long itemId);

}
