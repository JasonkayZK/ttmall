package top.jasonkayzk.ttmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.mapper.TbItemParamMapper;
import top.jasonkayzk.ttmall.pojo.TbItemParam;
import top.jasonkayzk.ttmall.pojo.TbItemParamExample;
import top.jasonkayzk.ttmall.service.ItemParamService;

import java.util.Date;
import java.util.List;

/**
 * 商品规格参数模板管理
 *
 * @author zk
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    final
    TbItemParamMapper itemParamMapper;

    public ItemParamServiceImpl(TbItemParamMapper itemParamMapper) {
        this.itemParamMapper = itemParamMapper;
    }

    @Override
    public TTMallCommonResult getItemParamByCid(long cid) {
        var example = new TbItemParamExample();
        var criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        // selectByExampleWithBLOBs才能获取大文本列!!!
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

        //判断是否查询到结果
        if (list != null && list.size() > 0) {
            return TTMallCommonResult.ok(list.get(0));
        }
        return TTMallCommonResult.ok();
    }

    @Override
    public TTMallCommonResult insertItemParam(TbItemParam itemParam) {
        var now = new Date();

        //补全pojo
        itemParam.setCreated(now);
        itemParam.setUpdated(now);

        //插入到规格参数模板表
        itemParamMapper.insert(itemParam);
        return TTMallCommonResult.ok();
    }

    @Override
    public EUDataGridResult getItemParamList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());

        PageInfo<TbItemParam> info = new PageInfo<>(list);

        return new EUDataGridResult(info.getTotal(), list);
    }

}
