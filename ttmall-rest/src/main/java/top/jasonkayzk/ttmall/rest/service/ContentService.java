package top.jasonkayzk.ttmall.rest.service;

import top.jasonkayzk.ttmall.pojo.TbContent;

import java.util.List;

/**
 * 内容界面
 *
 * @author zk
 */
public interface ContentService {

    /**
     * 获取内容列表
     *
     * @param contentCid 内容分类id(Cid)
     *
     * @return 内容表
     *
     */
    List<TbContent> getContentList(long contentCid);

}
