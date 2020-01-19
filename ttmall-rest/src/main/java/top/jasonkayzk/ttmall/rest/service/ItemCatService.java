package top.jasonkayzk.ttmall.rest.service;

import top.jasonkayzk.ttmall.rest.pojo.CatResult;

/**
 * 商品分类Service
 *
 * @author zk
 */
public interface ItemCatService {

    /**
     * 获取商品分类List
     *
     * @return 商品分类List
     */
    CatResult getItemCatList();

}
