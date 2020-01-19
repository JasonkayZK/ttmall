package top.jasonkayzk.ttmall.rest.service.impl;

import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.mapper.TbItemCatMapper;
import top.jasonkayzk.ttmall.pojo.TbItemCat;
import top.jasonkayzk.ttmall.pojo.TbItemCatExample;
import top.jasonkayzk.ttmall.rest.pojo.CatNode;
import top.jasonkayzk.ttmall.rest.pojo.CatResult;
import top.jasonkayzk.ttmall.rest.service.ItemCatService;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类服务
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
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        //查询分类列表
        catResult.setData(getCatList(0));
        return catResult;
    }

    private List<?> getCatList(long parentId) {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        var criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        //返回list
        var result = new ArrayList<>(32);

        //向list中添加节点
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            if (tbItemCat.getIsParent()) {
                CatNode node = new CatNode();
                if (parentId == 0) {
                    node.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                } else {
                    node.setName(tbItemCat.getName());
                }
                node.setUrl("/products/" + tbItemCat.getId() + ".html");
                node.setItem(getCatList(tbItemCat.getId()));

                result.add(node);
                count++;

                //第一层只取14条记录
                if (parentId == 0 && count >=14) {
                    break;
                }

            } else {
                //如果是叶子节点
                result.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
            }
        }
        return result;
    }

}
