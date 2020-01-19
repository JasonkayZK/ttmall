package top.jasonkayzk.ttmall.search.service.impl;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.ExceptionUtil;
import top.jasonkayzk.ttmall.search.mapper.ItemMapper;
import top.jasonkayzk.ttmall.search.pojo.Item;
import top.jasonkayzk.ttmall.search.service.ItemService;

import java.io.IOException;

/**
 * @author zk
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    private final SolrClient solrServer;

    public ItemServiceImpl(ItemMapper itemMapper, SolrClient solrServer) {
        this.itemMapper = itemMapper;
        this.solrServer = solrServer;
    }


    @Override
    public TTMallCommonResult importAllItems() {
        try {
            var list = itemMapper.getItemList();

            for (Item item : list) {
                //创建一个SolrInputDocument对象
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point", item.getSellPoint());
                document.setField("item_price", item.getPrice());
                document.setField("item_image", item.getImage());
                document.setField("item_category_name", item.getCategoryName());
                document.setField("item_desc", item.getItemDes());
                //写入索引库
                solrServer.add(document);
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return TTMallCommonResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TTMallCommonResult.ok();
    }
}
