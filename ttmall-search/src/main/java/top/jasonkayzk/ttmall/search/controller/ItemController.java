package top.jasonkayzk.ttmall.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.search.service.ItemService;

/**
 * 索引库维护
 * @author zk
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 导入商品数据到索引库
     */
    @RequestMapping("/importall")
    @ResponseBody
    public TTMallCommonResult importAllItems() {
        return itemService.importAllItems();
    }
}