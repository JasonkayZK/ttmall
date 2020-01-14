package top.jasonkayzk.ttmall.service.impl;

import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.EUTreeNode;
import top.jasonkayzk.ttmall.mapper.TbItemCatMapper;
import top.jasonkayzk.ttmall.pojo.TbItemCat;
import top.jasonkayzk.ttmall.pojo.TbItemCatExample;
import top.jasonkayzk.ttmall.service.ItemCatService;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 *
 * @author zk
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    private final TbItemCatMapper itemCatMapper;

    public ItemCatServiceImpl(TbItemCatMapper itemCatMapper) {
        this.itemCatMapper = itemCatMapper;
    }

    @Override
    public List<EUTreeNode> getCatList(long parentId) {

        //创建查询条件
        var example = new TbItemCatExample();
        var criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //根据条件查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<EUTreeNode> nodeList = new ArrayList<>(list.size());

        //把列表转换成treeNodeList
        list.forEach(x -> nodeList.add(new EUTreeNode(x.getId(), x.getName(), x.getIsParent() ? "closed" : "open")));

        return nodeList;
    }

}
