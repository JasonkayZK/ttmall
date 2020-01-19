package top.jasonkayzk.ttmall.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.ExceptionUtil;
import top.jasonkayzk.ttmall.search.pojo.SearchResult;
import top.jasonkayzk.ttmall.search.service.SearchService;

import java.nio.charset.StandardCharsets;

/**
 * 商品查询Controller
 *
 * @author zk
 */
@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public TTMallCommonResult search(@RequestParam("q") String queryString,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "60") Integer rows) {
        //查询条件不能为空
        if (StringUtils.isBlank(queryString)) {
            return TTMallCommonResult.build(400, "查询条件不能为空");
        }
        SearchResult searchResult;
        try {
            queryString = new String(queryString.getBytes("iso8859-1"), StandardCharsets.UTF_8);
            searchResult = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return TTMallCommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TTMallCommonResult.ok(searchResult);
    }

}
