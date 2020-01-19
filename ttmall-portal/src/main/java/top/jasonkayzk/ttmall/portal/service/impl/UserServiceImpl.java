package top.jasonkayzk.ttmall.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.HttpClientUtil;
import top.jasonkayzk.ttmall.pojo.TbUser;
import top.jasonkayzk.ttmall.portal.service.UserService;

import java.util.Objects;

/**
 * 用户管理Service
 *
 * @author zk
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;

    @Value("${SSO_DOMAIN_BASE_URL}")
    public String SSO_DOMAIN_BASE_URL;

    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;

    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;

    @Override
    public TbUser getUserByToken(String token) {
        // 调用sso系统的服务，根据token取用户信息
        String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);

        // 把json转换成TTMallCommonResult
        TTMallCommonResult result = TTMallCommonResult.formatToPojo(json, TbUser.class);
        if (Objects.requireNonNull(result).getStatus() == 200) {
            return (TbUser) result.getData();
        }
        return null;
    }

}
