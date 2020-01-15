package top.jasonkayzk.ttmall.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.pojo.TbItemParam;
import top.jasonkayzk.ttmall.service.ItemParamService;

import java.util.Date;

/**
 * 商品规格参数模板管理Controller
 *
 * @author zk
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    private final ItemParamService itemParamService;

    public ItemParamController(ItemParamService itemParamService) {
        this.itemParamService = itemParamService;
    }

    @ResponseBody
    @RequestMapping("list")
    public EUDataGridResult getItemParamList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
        return itemParamService.getItemParamList(page, rows);
    }

    @ResponseBody
    @RequestMapping("/query/itemcatid/{itemCatId}")
    public TTMallCommonResult getItemParamByCid(@PathVariable long itemCatId) {
        return itemParamService.getItemParamByCid(itemCatId);
    }

    @ResponseBody
    @RequestMapping("/save/{cid}")
    public TTMallCommonResult insertItemParam(@PathVariable long cid, String paramData) {
        //创建pojo对象
        var itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);

        var now = new Date();
        itemParam.setCreated(now);
        itemParam.setUpdated(now);

        return itemParamService.insertItemParam(itemParam);
    }

}
