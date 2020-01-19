package top.jasonkayzk.ttmall.portal.pojo;

import lombok.Data;

/**
 * @author zk
 */
@Data
public class Item {

    private String id;

    private String title;

    private String sellPoint;

    private long price;

    private String image;

    private String categoryName;

    private String itemDes;

    public String[] getImages() {
        if (image != null) {
            return image.split(",");
        }
        return null;
    }

}
