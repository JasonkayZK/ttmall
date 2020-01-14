package top.jasonkayzk.ttmall.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * EasyUI表格数据的POJO
 *
 * @author zk
 */
@Data
public class EUDataGridResult {

    private long total;

    private List<?> rows;

    public EUDataGridResult(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EUDataGridResult(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

}
