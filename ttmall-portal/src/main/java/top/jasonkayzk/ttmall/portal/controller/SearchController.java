package top.jasonkayzk.ttmall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.jasonkayzk.ttmall.portal.pojo.SearchResult;
import top.jasonkayzk.ttmall.portal.service.SearchService;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 商品搜索Controller
 *
 * @author zk
 */
@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model) {
        if (queryString != null) {
            try {
                queryString = new String(queryString.getBytes("iso8859-1"), StandardCharsets.UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SearchResult searchResult = searchService.search(queryString, page);

        //向页面传递参数
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);

        return "search";
    }


}
