package top.jasonkayzk.ttmall.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.jasonkayzk.ttmall.common.utils.JsonUtils;
import top.jasonkayzk.ttmall.rest.pojo.CatResult;
import top.jasonkayzk.ttmall.rest.service.ItemCatService;

/**
 * 商品分类控制器
 *
 * @author zk
 */
@RestController
public class ItemCatController {

    private final ItemCatService itemCatService;

    public ItemCatController(ItemCatService itemCatService) {
        this.itemCatService = itemCatService;
    }

    /**
     * JSONP调用返回方法一
     *
     * @param callback 前端js回调函数
     * @return 一段js包括的数据
     */
    @ResponseBody
    @RequestMapping(value = "/itemcat/list",
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public String getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        //把pojo转换成字符串
        String json = JsonUtils.objectToJson(catResult);

        //拼装返回值
        return callback + "(" + json + ");";
    }

// Spring5.x 找不到方法
//    @RequestMapping("/itemcat/list")
//    public Object getItemCatList(String callback) {
//        CatResult catResult = itemCatService.getItemCatList();
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
//        mappingJacksonValue.setJsonpFunction(callback);
//        return mappingJacksonValue;
//    }

}
