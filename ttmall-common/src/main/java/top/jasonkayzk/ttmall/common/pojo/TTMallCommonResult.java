package top.jasonkayzk.ttmall.common.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

/**
 * 天天商城自定义响应结构
 *
 * @author zk
 */
@Data
public class TTMallCommonResult {

    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private Object data;

    public static TTMallCommonResult build(Integer status, String msg, Object data) {
        return new TTMallCommonResult(status, msg, data);
    }

    public static TTMallCommonResult ok(Object data) {
        return new TTMallCommonResult(data);
    }

    public static TTMallCommonResult ok() {
        return new TTMallCommonResult(null);
    }

    public TTMallCommonResult() {}

    public static TTMallCommonResult build(Integer status, String msg) {
        return new TTMallCommonResult(status, msg, null);
    }

    public TTMallCommonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TTMallCommonResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    /**
     * 将json结果集转化为TTMallCommonResult对象
     *
     * @param jsonData json数据
     * @param clazz TTMallCommonResult中的object类型
     * @return TTMallCommonResult对象
     */
    public static TTMallCommonResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, TTMallCommonResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = MAPPER.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = MAPPER.readValue(data.asText(), clazz);
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json json字符串
     * @return TTMallCommonResult对象
     */
    public static TTMallCommonResult format(String json) {
        try {
            return MAPPER.readValue(json, TTMallCommonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return TTMallCommonResult对象
     */
    public static TTMallCommonResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
