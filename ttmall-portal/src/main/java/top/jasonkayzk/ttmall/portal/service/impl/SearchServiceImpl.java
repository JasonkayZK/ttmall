package top.jasonkayzk.ttmall.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.HttpClientUtil;
import top.jasonkayzk.ttmall.portal.pojo.SearchResult;
import top.jasonkayzk.ttmall.portal.service.SearchService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 商品搜索Service
 *
 * @author zk
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {
        // 调用ttmall-search的服务
        // 查询参数
        Map<String, String> param = new HashMap<>(8);
        param.put("q", queryString);
        param.put("page", page + "");

        try {
            //调用服务
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
            //把字符串转换成java对象
            TTMallCommonResult commonResult = TTMallCommonResult.formatToPojo(json, SearchResult.class);
            if (Objects.requireNonNull(commonResult).getStatus() == 200) {
                return (SearchResult) commonResult.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
