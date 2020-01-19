package top.jasonkayzk.ttmall.portal.pojo;

import top.jasonkayzk.ttmall.pojo.TbItem;

/**
 * @author zk
 */
public class ItemInfo extends TbItem {

    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            return image.split(",");
        }
        return null;
    }

}
