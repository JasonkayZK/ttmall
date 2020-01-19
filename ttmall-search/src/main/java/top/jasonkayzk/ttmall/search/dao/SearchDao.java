package top.jasonkayzk.ttmall.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import top.jasonkayzk.ttmall.search.pojo.SearchResult;

import java.io.IOException;

/**
 * @author zk
 */
public interface SearchDao {


    /**
     * 执行查询
     *
     * @param query 查询参数
     *
     * @return Solr查询结果
     *
     * @throws IOException IOException
     *
     * @throws SolrServerException SolrServerException
     */
    SearchResult search(SolrQuery query) throws IOException, SolrServerException;

}
