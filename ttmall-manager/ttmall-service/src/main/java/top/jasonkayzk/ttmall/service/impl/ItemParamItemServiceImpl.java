package top.jasonkayzk.ttmall.service.impl;

import org.springframework.stereotype.Service;
import top.jasonkayzk.ttmall.common.utils.JsonUtils;
import top.jasonkayzk.ttmall.mapper.TbItemParamItemMapper;
import top.jasonkayzk.ttmall.pojo.TbItemParamItem;
import top.jasonkayzk.ttmall.pojo.TbItemParamItemExample;
import top.jasonkayzk.ttmall.service.ItemParamItemService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 展示商品规格参数
 *
 * @author zk
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    private final TbItemParamItemMapper itemParamItemMapper;

    public ItemParamItemServiceImpl(TbItemParamItemMapper itemParamItemMapper) {
        this.itemParamItemMapper = itemParamItemMapper;
    }

    @Override
    public String getItemParamByItemId(Long itemId) {
        //根据商品id查询规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        var criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);

        //执行查询
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list == null || list.size() == 0) {
            return "";
        }

        TbItemParamItem itemParamItem = list.get(0);
        String paramData = itemParamItem.getParamData();
        //生成html
        // 把规格参数json数据转换成java对象
        var jsonList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuilder sb = new StringBuilder();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for(var m1: Objects.requireNonNull(jsonList)) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">").append(m1.get("group")).append("</th>\n");
            sb.append("        </tr>\n");
            var list2 = (List<Map>) m1.get("params");
            for(var m2:list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">").append(m2.get("k")).append("</td>\n");
                sb.append("            <td>").append(m2.get("v")).append("</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

}
