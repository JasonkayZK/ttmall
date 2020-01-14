package top.jasonkayzk.ttmall.service;

import top.jasonkayzk.ttmall.common.pojo.EUTreeNode;

import java.util.List;

/**
 * 商品种类表
 *
 * @author zk
 */
public interface ItemCatService {

    /**
     * 根据父Id获取商品种类子内容
     *
     * @param parentId 父节点Id
     *
     * @return 子节点内容
     */
    List<EUTreeNode> getCatList(long parentId);

}
