package top.jasonkayzk.ttmall.rest.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.pojo.TTMallCommonResult;
import top.jasonkayzk.ttmall.common.utils.JsonUtils;
import top.jasonkayzk.ttmall.mapper.TbItemDescMapper;
import top.jasonkayzk.ttmall.mapper.TbItemMapper;
import top.jasonkayzk.ttmall.mapper.TbItemParamItemMapper;
import top.jasonkayzk.ttmall.pojo.TbItem;
import top.jasonkayzk.ttmall.pojo.TbItemDesc;
import top.jasonkayzk.ttmall.pojo.TbItemParamItem;
import top.jasonkayzk.ttmall.pojo.TbItemParamItemExample;
import top.jasonkayzk.ttmall.rest.dao.JedisClient;
import top.jasonkayzk.ttmall.rest.service.ItemService;

import java.util.List;

/**
 * 商品信息管理Service
 *
 * @author zk
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final TbItemMapper itemMapper;

    private final TbItemDescMapper itemDescMapper;

    private final TbItemParamItemMapper itemParamItemMapper;

    private final JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    public ItemServiceImpl(TbItemMapper itemMapper, TbItemDescMapper itemDescMapper, TbItemParamItemMapper itemParamItemMapper, JedisClient jedisClient) {
        this.itemMapper = itemMapper;
        this.itemDescMapper = itemDescMapper;
        this.itemParamItemMapper = itemParamItemMapper;
        this.jedisClient = jedisClient;
    }

    @Override
    public TTMallCommonResult getItemBaseInfo(long itemId) {
        try {
            //添加缓存逻辑
            //从缓存中取商品信息，商品id对应的信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            //判断是否有值
            if (!StringUtils.isBlank(json)) {
                //把json转换成java对象
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return TTMallCommonResult.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据商品id查询商品信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //使用TTMallCommonResult包装一下
        try {
            //把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
            //设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TTMallCommonResult.ok(item);
    }

    @Override
    public TTMallCommonResult getItemDesc(long itemId) {
        //添加缓存
        try {
            //添加缓存逻辑
            //从缓存中取商品信息，商品id对应的信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            //判断是否有值
            if (!StringUtils.isBlank(json)) {
                //把json转换成java对象
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return TTMallCommonResult.ok(itemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建查询条件
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

        try {
            //把商品信息写入缓存
            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
            //设置key的有效期
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return TTMallCommonResult.ok(itemDesc);
    }

    @Override
    public TTMallCommonResult getItemParam(long itemId) {
        //添加缓存
        try {
            //添加缓存逻辑
            //从缓存中取商品信息，商品id对应的信息
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            //判断是否有值
            if (!StringUtils.isBlank(json)) {
                //把json转换成java对象
                TbItemParamItem paramItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return TTMallCommonResult.ok(paramItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //根据商品id查询规格参数
        //设置查询条件
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        //执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size()>0) {
            TbItemParamItem paramItem = list.get(0);
            try {
                //把商品信息写入缓存
                jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(paramItem));
                //设置key的有效期
                jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXPIRE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return TTMallCommonResult.ok(paramItem);
        }
        return TTMallCommonResult.build(400, "无此商品规格");
    }

}
