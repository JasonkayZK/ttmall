package top.jasonkayzk.ttmall.portal.service;

import top.jasonkayzk.ttmall.pojo.TbUser;

public interface UserService {

    TbUser getUserByToken(String token);

}
