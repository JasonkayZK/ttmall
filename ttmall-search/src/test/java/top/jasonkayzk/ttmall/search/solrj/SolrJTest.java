package top.jasonkayzk.ttmall.search.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

@Ignore
public class SolrJTest {

    @Test
    public void addDocument() throws Exception {
        //创建一连接
        var client = new HttpSolrClient.Builder("http://localhost:8983/solr/Ik_core").build();

        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "test001");
        document.addField("item_title", "测试商品2");
        document.addField("item_price", 54321);

        //把文档对象写入索引库
        client.add(document);

        //提交
        client.commit();
    }

    @Test
    public void deleteDocument() throws Exception {
        //创建一连接
        var client = new HttpSolrClient.Builder("http://localhost:8983/solr/Ik_core").build();

        client.deleteById("test001");
        client.deleteByQuery("*:*");

        client.commit();
    }

    @Test
    public void queryDocument() throws IOException, SolrServerException {
        var client = new HttpSolrClient.Builder("http://localhost:8983/solr/Ik_core").build();

        //创建一个查询对象
        SolrQuery query = new SolrQuery();

        //设置查询条件
        query.setQuery("*:*");
        query.setStart(20);
        query.setRows(50);

        //执行查询
        QueryResponse response = client.query(query);

        //取查询结果
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("共查询到记录：" + solrDocumentList.getNumFound());

        solrDocumentList.forEach(x -> {
            System.out.println(x.get("id"));
            System.out.println(x.get("item_title"));
            System.out.println(x.get("item_price"));
            System.out.println(x.get("item_image"));
        });
    }

}
