package top.jasonkayzk.ttmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.pojo.TbContent;
import top.jasonkayzk.ttmall.service.ContentService;

/**
 * @author zk
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping("/save")
    @ResponseBody
    public TTMallCommonResult insertContent(TbContent content) {
        return contentService.insertContent(content);
    }

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult getContentListByParentId(@RequestParam(defaultValue = "0") Long categoryId,
                                                     @RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "30") Integer rows) {
        return contentService.getContentList(categoryId, page, rows);
    }

}
