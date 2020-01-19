package top.jasonkayzk.ttmall.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.rest.service.ItemService;

/**
 * 商品信息Controller
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

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public TTMallCommonResult getItemBaseInfo(@PathVariable Long itemId) {
        return itemService.getItemBaseInfo(itemId);
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TTMallCommonResult getItemDesc(@PathVariable Long itemId) {
        return itemService.getItemDesc(itemId);
    }

    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TTMallCommonResult getItemParam(@PathVariable Long itemId) {
        return itemService.getItemParam(itemId);
    }
}