package top.jasonkayzk.ttmall.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品分类树型节点pojo
 *
 * @author zk
 */
@Data
public class CatNode {

    @JsonProperty(value = "n")
    private String name;

    @JsonProperty(value = "u")
    private String url;

    @JsonProperty(value = "i")
    private List<?> item;

}
