package top.jasonkayzk.ttmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jasonkayzk.ttmall.common.pojo.EUTreeNode;
import top.jasonkayzk.ttmall.service.ItemCatService;

import java.util.List;

/**
 * 商品分类管理controller
 *
 * @author zk
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    private final ItemCatService itemCatService;

    public ItemCatController(ItemCatService itemCatService) {
        this.itemCatService = itemCatService;
    }

    @RequestMapping("/list")
    @ResponseBody
    private List<EUTreeNode> getCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return itemCatService.getCatList(parentId);
    }

}
