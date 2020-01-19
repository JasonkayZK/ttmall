package top.jasonkayzk.ttmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.EUDataGridResult;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.mapper.TbContentMapper;
import top.jasonkayzk.ttmall.pojo.*;
import top.jasonkayzk.ttmall.service.ContentService;

import java.util.Date;
import java.util.List;

/**
 * 内容管理(CMS)
 *
 * @author zk
 */
@Service
public class ContentServiceImpl implements ContentService {

    private final TbContentMapper contentMapper;

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    public ContentServiceImpl(TbContentMapper contentMapper) {
        this.contentMapper = contentMapper;
    }

    @Override
    public TTMallCommonResult insertContent(TbContent content) {
        // 补全pojo内容
        var now = new Date();
        content.setCreated(now);
        content.setUpdated(now);

        contentMapper.insert(content);
        // 添加缓存同步逻辑
//        try {
//            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return TTMallCommonResult.ok();
    }

    @Override
    public EUDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
        var example = new TbContentExample();
        var criteria = example.createCriteria();

        criteria.andCategoryIdEqualTo(categoryId);

        PageHelper.startPage(page, rows);

        List<TbContent> list = contentMapper.selectByExample(example);

        PageInfo<TbContent> info = new PageInfo<>(list);

        return new EUDataGridResult(info.getTotal(), list);
    }

}
