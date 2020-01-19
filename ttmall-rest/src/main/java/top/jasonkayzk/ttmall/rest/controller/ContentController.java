package top.jasonkayzk.ttmall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.ExceptionUtil;
import top.jasonkayzk.ttmall.pojo.TbContent;
import top.jasonkayzk.ttmall.rest.service.ContentService;

import java.util.List;

/**
 * 内容管理Controller
 *
 * @author zk
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public TTMallCommonResult getContentList(@PathVariable Long contentCategoryId) {
        try {
            List<TbContent> list = contentService.getContentList(contentCategoryId);
            return TTMallCommonResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return TTMallCommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

}
