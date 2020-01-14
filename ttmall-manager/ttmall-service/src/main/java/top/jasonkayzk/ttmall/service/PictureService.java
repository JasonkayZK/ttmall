package top.jasonkayzk.ttmall.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片Service
 *
 * @author zk
 */
public interface PictureService {

    /**
     * 上传图片
     *
     * @param uploadFile 图片
     * @return 使用Map返回操作响应
     */
    Map<Object, Object> uploadPicture(MultipartFile uploadFile);

}
