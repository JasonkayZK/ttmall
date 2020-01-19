package top.jasonkayzk.ttmall.portal.pojo;

import lombok.Data;

/**
 * @author zk
 */
@Data
public class CartItem {

    private long id;

    private String title;

    private Integer num;

    private long price;

    private String image;
}
