package top.jasonkayzk.ttmall.service;

import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.pojo.TbItemParam;

/**
 * 商品规格参数模板管理
 *
 * @author zk
 */
public interface ItemParamService {

    /**
     * 通过产品类型id获取Item, 展示到产品添加中
     *
     * @param cid 产品类型id
     *
     * @return 处理响应
     */
    TTMallCommonResult getItemParamByCid(long cid);


    /**
     * 插入产品参数
     *
     * @param itemParam 产品参数Pojo
     *
     * @return 处理响应
     */
    TTMallCommonResult insertItemParam(TbItemParam itemParam);

    /**
     * 根据商品参数页数, 每页个数返回List商品对象
     *
     * @param page 页数
     * @param rows 每页行数
     * @return List-ItemParam对象
     */
    EUDataGridResult getItemParamList(Integer page, Integer rows);

}
