package top.jasonkayzk.ttmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.IDUtils;
import top.jasonkayzk.ttmall.mapper.TbItemDescMapper;
import top.jasonkayzk.ttmall.mapper.TbItemMapper;
import top.jasonkayzk.ttmall.mapper.TbItemParamItemMapper;
import top.jasonkayzk.ttmall.pojo.TbItem;
import top.jasonkayzk.ttmall.pojo.TbItemDesc;
import top.jasonkayzk.ttmall.pojo.TbItemExample;
import top.jasonkayzk.ttmall.pojo.TbItemParamItem;
import top.jasonkayzk.ttmall.service.ItemService;

import java.util.Date;
import java.util.List;


/**
 * 商品管理Service
 *
 * @author zk
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final TbItemMapper itemMapper;

    private final TbItemDescMapper itemDescMapper;

    private final TbItemParamItemMapper itemParamItemMapper;

    public ItemServiceImpl(TbItemMapper itemMapper, TbItemDescMapper itemDescMapper, TbItemParamItemMapper itemParamItemMapper) {
        this.itemMapper = itemMapper;
        this.itemDescMapper = itemDescMapper;
        this.itemParamItemMapper = itemParamItemMapper;
    }

    @Override
    public TbItem getItemById(long itemId) {
        // return itemMapper.selectByPrimaryKey(itemId);
        // 添加查询条件
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        var example = new TbItemExample();

        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);

        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();

        return new EUDataGridResult(total, list);
    }

    @Override
    public TTMallCommonResult createItem(TbItem item, String desc, String itemParam) throws Exception {
        //item补全

        //生成商品ID
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);

        // 商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        var now = new Date();
        item.setCreated(now);
        item.setUpdated(now);

        //插入到数据库
        itemMapper.insert(item);

        //添加商品描述信息
        TTMallCommonResult result = insertItemDesc(itemId, desc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }

        //添加规格参数
        result = insertItemParamItem(itemId, itemParam);
        if (result.getStatus() != 200) {
            throw new Exception();
        }

        return TTMallCommonResult.ok();
    }

    /**
     * 添加商品描述
     *
     * @param desc 商品描述
     */
    private TTMallCommonResult insertItemDesc(Long itemId, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();

        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);

        var now = new Date();

        itemDesc.setCreated(now);
        itemDesc.setUpdated(now);
        itemDescMapper.insert(itemDesc);

        return TTMallCommonResult.ok();
    }

    /**
     * 添加规格参数
     *
     * @param itemId 商品ID
     * @param itemParam 商品参数
     *
     * @return 响应
     */
    private TTMallCommonResult insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();

        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);

        var now = new Date();

        itemParamItem.setCreated(now);
        itemParamItem.setUpdated(now);
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);

        return TTMallCommonResult.ok();
    }

}
