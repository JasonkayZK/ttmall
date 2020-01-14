package top.jasonkayzk.ttmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 *
 * @author zk
 */
@Controller
public class PageController {

    /**
     * 打开首页
     *
     * @return 首页视图
     */
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * 展示其他页面
     *
     * @param page 请求页面
     *
     * @return 请求页面视图
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

}
