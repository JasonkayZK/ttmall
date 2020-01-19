package top.jasonkayzk.ttmall.service.impl;

import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.EUTreeNode;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.mapper.TbContentCategoryMapper;
import top.jasonkayzk.ttmall.pojo.TbContentCategory;
import top.jasonkayzk.ttmall.pojo.TbContentCategoryExample;
import top.jasonkayzk.ttmall.service.ContentCategoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理
 *
 * @author zk
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    private final TbContentCategoryMapper contentCategoryMapper;

    public ContentCategoryServiceImpl(TbContentCategoryMapper contentCategoryMapper) {
        this.contentCategoryMapper = contentCategoryMapper;
    }

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        // 根据parentId查询节点列表
        var example = new TbContentCategoryExample();
        var criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        // 执行查询
        var list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> result = new ArrayList<>();

        list.forEach(x -> result.add(new EUTreeNode(x.getId(), x.getName(), x.getIsParent() ? "closed" : "open")));

        return result;
    }

    @Override
    public TTMallCommonResult insertContentCategory(long parentId, String name) {
        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        //'状态。可选值:1(正常),2(删除)',
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);

        var now = new Date();
        contentCategory.setCreated(now);
        contentCategory.setUpdated(now);

        //添加记录
        contentCategoryMapper.insert(contentCategory);

        //查看父节点的isParent列是否为true，如果不是true改成true
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);

        //判断是否为true
        if (!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }

        //返回结果
        return TTMallCommonResult.ok(contentCategory);
    }

    @Override
    public TTMallCommonResult deleteContentCategory(long id) {
        //将此节点对象从数据库中搜出来
        TbContentCategory node = contentCategoryMapper.selectByPrimaryKey(id);

        //递归删除此节点
        this.recursiveDelete(id);

        //判断是否更新父节点
        this.updateParentNode(node.getParentId());

        return TTMallCommonResult.ok();
    }

    /**
     * 根据父节点Id 查询所有的兄弟节点
     *
     * @param parentId 父节点Id
     * @return 兄弟节点列表
     */
    private List<TbContentCategory> getListByParentId(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return contentCategoryMapper.selectByExample(example);
    }

    /**
     * 递归删除资源树上的节点
     *
     * @param id 要删除的节点id
     */
    public void recursiveDelete(Long id) {
        // 查询此节点下面的所有的子节点
        List<TbContentCategory> list = getListByParentId(id);

        // 若此节点下面没有子节点
        if (list.size() == 0) {
            TbContentCategory deleteNode = contentCategoryMapper.selectByPrimaryKey(id);

            //得到此节点的父节点Id
            Long parentId = deleteNode.getParentId();

            //删除此节点
            contentCategoryMapper.deleteByPrimaryKey(id);

            //删除此节点后，判断此节点的父节点是否为子节点，若是，则更新其父节点为子节点
            this.updateParentNode(parentId);
        } else {
            contentCategoryMapper.deleteByPrimaryKey(id);
            for (TbContentCategory category : list) {
                if (category.getIsParent()) {
                    //递归删除节点
                    this.recursiveDelete(category.getId());
                } else {
                    contentCategoryMapper.deleteByPrimaryKey(category.getId());
                }
            }
        }
    }

    /**
     * 判断此节点是否存在兄弟节点，若不存在，则将其父节点更新成子节点
     *
     * @param parentId 节点Id
     */
    private void updateParentNode(Long parentId) {
        //查询此节点的所有的兄弟节点
        List<TbContentCategory> contentCat = getListByParentId(parentId);

        //若无兄弟节点
        if (contentCat.size() == 0) {
            //更新此节点的父节点为子节点
            TbContentCategory node2 = contentCategoryMapper.selectByPrimaryKey(parentId);
            node2.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKeySelective(node2);
        }
    }

}
