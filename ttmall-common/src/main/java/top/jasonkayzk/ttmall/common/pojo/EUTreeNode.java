package top.jasonkayzk.ttmall.common.pojo;

import lombok.Data;

/**
 * easyUI树形控件节点格式
 *
 * @author zk
 */
@Data
public class EUTreeNode {

    private long id;

    private String text;

    private String state;

    public EUTreeNode() {}

    public EUTreeNode(long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

}
