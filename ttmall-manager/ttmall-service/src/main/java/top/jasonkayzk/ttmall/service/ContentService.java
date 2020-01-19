package top.jasonkayzk.ttmall.service;

import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.pojo.TbContent;

import java.util.List;

/**
 * 商品详情
 *
 * @author zk
 */
public interface ContentService {

    /**
     * 加入商品详情
     *
     * @param content 商品详情
     *
     * @return 处理响应
     */
    TTMallCommonResult insertContent(TbContent content);


    /**
     * 根据categoryId分页查询商品内容分类
     *
     * @param categoryId 内容分类Id
     * @param page 页数
     * @param rows 行数
     *
     * @return 分页数据
     */
    EUDataGridResult getContentList(Long categoryId, Integer page, Integer rows);

}
