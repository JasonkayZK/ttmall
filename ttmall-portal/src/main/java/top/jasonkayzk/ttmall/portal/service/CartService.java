package top.jasonkayzk.ttmall.portal.service;

import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车Service
 *
 * @author zk
 */
public interface CartService {

    TTMallCommonResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

    TTMallCommonResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);

}
