package top.jasonkayzk.ttmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jasonkayzk.ttmall.common.pojo.EUTreeNode;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.service.ContentCategoryService;

import java.util.List;

/**
 * 内容分类管理
 *
 * @author zk
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    private final ContentCategoryService contentCategoryService;

    public ContentCategoryController(ContentCategoryService contentCategoryService) {
        this.contentCategoryService = contentCategoryService;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
        return contentCategoryService.getCategoryList(parentId);
    }

    @RequestMapping("/create")
    @ResponseBody
    public TTMallCommonResult createContentCategory(Long parentId, String name) {
        return contentCategoryService.insertContentCategory(parentId, name);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public TTMallCommonResult deleteContentCategory(Long id) {
        return contentCategoryService.deleteContentCategory(id);
    }

}
