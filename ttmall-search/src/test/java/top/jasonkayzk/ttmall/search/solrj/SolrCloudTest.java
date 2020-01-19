package top.jasonkayzk.ttmall.search.solrj;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudHttp2SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

@Ignore
public class SolrCloudTest {

    @Test
    public void testAddDocument() throws Exception {
        //创建一个和solr集群的连接
        //参数就是zookeeper的地址列表，使用逗号分隔
        String zkHost = "192.168.25.154:2181,192.168.25.154:2182,192.168.25.154:2183";
        var client = new CloudHttp2SolrClient.Builder(Collections.singletonList(zkHost)).build();

        //设置默认的collection
        client.setDefaultCollection("collection2");

        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();

        //向文档中添加域
        document.addField("id", "test001");
        document.addField("item_title", "测试商品");

        //把文档添加到索引库
        client.add(document);

        //提交
        client.commit();
    }

    @Test
    public void deleteDocument() throws SolrServerException, IOException {
        //创建一个和solr集群的连接
        //参数就是zookeeper的地址列表，使用逗号分隔
        String zkHost = "192.168.25.154:2181,192.168.25.154:2182,192.168.25.154:2183";
        var client = new CloudHttp2SolrClient.Builder(Collections.singletonList(zkHost)).build();

        //设置默认的collection
        client.setDefaultCollection("collection2");

        client.deleteByQuery("*:*");

        client.commit();
    }

}
