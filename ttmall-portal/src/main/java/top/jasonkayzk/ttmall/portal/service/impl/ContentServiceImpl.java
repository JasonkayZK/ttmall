package top.jasonkayzk.ttmall.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.HttpClientUtil;
import top.jasonkayzk.ttmall.common.utils.JsonUtils;
import top.jasonkayzk.ttmall.pojo.TbContent;
import top.jasonkayzk.ttmall.portal.service.ContentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用服务层服务，查询内容列表
 *
 * @author zk
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    @Override
    public String getContentList() {
        //调用服务层的服务
        String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
        //把字符串转换成TTMallCommonResult
        try {
            TTMallCommonResult commonResult = TTMallCommonResult.formatToList(result, TbContent.class);

            //取内容列表
            List<TbContent> list = (List<TbContent>) commonResult.getData();
            List<Map<Object, Object>> resultList = new ArrayList<>();

            //创建一个jsp页码要求的pojo列表
            for (TbContent tbContent : list) {
                Map<Object, Object> map = new HashMap<>(32);
                map.put("src", tbContent.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", tbContent.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href", tbContent.getUrl());
                map.put("alt", tbContent.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
