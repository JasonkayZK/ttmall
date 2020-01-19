package top.jasonkayzk.ttmall.search.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Item {

    private String id;

    private String title;

    private String sellPoint;

    private long price;

    private String image;

    private String categoryName;

    private String itemDes;

    @JsonIgnore
    public String[] getImages() {
        if (image != null) {
            return image.split(",");
        }
        return null;
    }

}
