package top.jasonkayzk.ttmall.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.utils.JsonUtils;
import top.jasonkayzk.ttmall.mapper.TbContentMapper;
import top.jasonkayzk.ttmall.pojo.TbContent;
import top.jasonkayzk.ttmall.pojo.TbContentExample;
import top.jasonkayzk.ttmall.rest.dao.JedisClient;
import top.jasonkayzk.ttmall.rest.service.ContentService;

import java.util.List;

/**
 * 内容管理
 *
 * @author zk
 */
@Service
public class ContentServiceImpl implements ContentService {

    private final TbContentMapper contentMapper;

    private final JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    public ContentServiceImpl(TbContentMapper contentMapper, JedisClient jedisClient) {
        this.contentMapper = contentMapper;
        this.jedisClient = jedisClient;
    }

    @Override
    public List<TbContent> getContentList(long contentCid) {
        //从缓存中取内容
        try {
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
            if (!StringUtils.isBlank(result)) {
                //把字符串转换成list
                return JsonUtils.jsonToList(result, TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);

        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);

        //向缓存中添加内容
        try {
            //把list转换成字符串
            String cacheString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
