package top.jasonkayzk.ttmall.portal.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.jasonkayzk.ttmall.common.utils.CookieUtils;
import top.jasonkayzk.ttmall.pojo.TbUser;
import top.jasonkayzk.ttmall.portal.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录拦截器
 *
 * @author zk
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //在Handler执行之前处理
        //判断用户是否登录
        //从cookie中取token
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");

        //根据token换取用户信息，调用sso系统的接口。
        TbUser user = userService.getUserByToken(token);

        //取不到用户信息
        if (null == user) {
            //跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            response.sendRedirect(userService.SSO_DOMAIN_BASE_URL + userService.SSO_PAGE_LOGIN
                    + "?redirect=" + request.getRequestURL());
            //返回false
            return false;
        }
        //取到用户信息，放行
        //把用户信息放入Request
        request.setAttribute("user", user);
        //返回值决定handler是否执行。true：执行，false：不执行。
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // handler执行之后，返回ModelAndView之前

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 返回ModelAndView之后
        //响应用户之后

    }


}
