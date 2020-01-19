package top.jasonkayzk.ttmall.search.service;

import org.apache.solr.client.solrj.SolrServerException;
import top.jasonkayzk.ttmall.search.pojo.SearchResult;

import java.io.IOException;

/**
 * @author zk
 */
public interface SearchService {

    /**
     * 查询item
     *
     * @param queryString 查询string
     *
     * @param page 页数
     *
     * @param rows 行数
     *
     * @return 返回查询结果
     */
    SearchResult search(String queryString, int page, int rows) throws IOException, SolrServerException;

}
