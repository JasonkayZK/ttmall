package top.jasonkayzk.ttmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.jasonkayzk.ttmall.service.ItemParamItemService;

/**
 * 展示商品规格参数
 *
 * @author zk
 */
@Controller
public class ItemParamItemController {

    private final ItemParamItemService itemParamItemService;

    public ItemParamItemController(ItemParamItemService itemParamItemService) {
        this.itemParamItemService = itemParamItemService;
    }

    @RequestMapping("/showitem/{itemId}")
    public String getItemParamItemById(@PathVariable long itemId, Model model) {
        String string = itemParamItemService.getItemParamByItemId(itemId);
        model.addAttribute("itemParam", string);
        return "item";
    }

}
