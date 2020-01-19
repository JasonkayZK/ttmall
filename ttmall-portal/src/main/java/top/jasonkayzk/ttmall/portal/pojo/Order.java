package top.jasonkayzk.ttmall.portal.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.jasonkayzk.ttmall.pojo.TbOrder;
import top.jasonkayzk.ttmall.pojo.TbOrderItem;
import top.jasonkayzk.ttmall.pojo.TbOrderShipping;

import java.util.List;

/**
 * @author zk
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class Order extends TbOrder {

    private List<TbOrderItem> orderItems;

    private TbOrderShipping orderShipping;

}
