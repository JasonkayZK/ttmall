package top.jasonkayzk.ttmall.search.dao.impl;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;
import top.jasonkayzk.ttmall.search.dao.SearchDao;
import top.jasonkayzk.ttmall.search.pojo.Item;
import top.jasonkayzk.ttmall.search.pojo.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品搜索Dao
 *
 * @author zk
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    private final SolrClient solrClient;

    public SearchDaoImpl(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @Override
    public SearchResult search(SolrQuery query) throws IOException, SolrServerException {
        //返回值对象
        SearchResult result = new SearchResult();

        //根据查询条件查询索引库
        QueryResponse queryResponse = solrClient.query(query, SolrRequest.METHOD.POST);

        //取查询结果
        SolrDocumentList solrDocumentList = queryResponse.getResults();

        //取查询结果总数量
        result.setRecordCount(solrDocumentList.getNumFound());

        //商品列表
        List<Item> itemList = new ArrayList<>();

        //取高亮显示
        var highlighting = queryResponse.getHighlighting();

        //取商品列表
        for (SolrDocument solrDocument : solrDocumentList) {
            //创建一商品对象
            Item item = new Item();
            Object id = solrDocument.get("id");
            item.setId((String) id);

            //取高亮显示的结果
            List<String> list = highlighting.get(id).get("item_title");
            String title;
            if (list != null && list.size()>0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }

            item.setTitle(title);
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSellPoint((String) solrDocument.get("item_sell_point"));
            item.setCategoryName((String) solrDocument.get("item_category_name"));

            //添加的商品列表
            itemList.add(item);
        }
        result.setItemList(itemList);
        return result;
    }

}
