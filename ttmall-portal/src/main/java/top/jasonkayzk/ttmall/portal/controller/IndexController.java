package top.jasonkayzk.ttmall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页视图控制器
 *
 * @author zk
 */
@Controller
public class IndexController {

//    @Autowired
//    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
//        String adJson = contentService.getContentList();
//        model.addAttribute("ad1", adJson);

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/httpclient/post", method = RequestMethod.POST)
    public String testPost(String username, String password) {
        String res = "username: " + username + ", password: " + password;
        System.out.println(res);
        return res;
    }

}
