package top.jasonkayzk.ttmall.service;


import top.jasonkayzk.ttmall.common.pojo.EUTreeNode;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;

import java.util.List;

/**
 * 商品详情种类
 *
 * @author zk
 */
public interface ContentCategoryService {

    /**
     * 获取类别列表
     *
     * @param parentId 父节点Id
     *
     * @return 父节点Id下的内容
     */
    List<EUTreeNode> getCategoryList(long parentId);

    /**
     * 向父节点插入子节点
     *
     * @param parentId 父节点Id
     *
     * @param name 子节点名称
     *
     * @return 处理响应
     */
    TTMallCommonResult insertContentCategory(long parentId, String name);

    /**
     * 删除一个节点
     *
     * @param id 节点Id
     *
     * @return 处理响应
     */
    TTMallCommonResult deleteContentCategory(long id);

}
