package top.jasonkayzk.ttmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.pojo.TbItem;
import top.jasonkayzk.ttmall.service.ItemService;

/**
 * 商品管理Controller
 *
 * @author zk
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 设置相应的内容为json数据
     *  @param page 页数
     * @param rows 行数
     *
     * @return 列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "30")Integer rows) {
        //查询商品列表
        return itemService.getItemList(page, rows);
    }

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping(value="/item/save", method= RequestMethod.POST)
    @ResponseBody
    private TTMallCommonResult createItem(TbItem item, String desc, String itemParams) throws Exception {
        return itemService.createItem(item, desc, itemParams);
    }

}
