package top.jasonkayzk.ttmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.jasonkayzk.ttmall.common.utils.JsonUtils;
import top.jasonkayzk.ttmall.service.PictureService;

/**
 * 上传图片处理
 *
 * @author zk
 */
@Controller
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @ResponseBody
    @RequestMapping("/pic/upload")
    public String uploadPicture(MultipartFile uploadFile) {
        var result = pictureService.uploadPicture(uploadFile);

        //为了保证功能的兼容性，需要把Result转换成json格式的字符串。
        return JsonUtils.objectToJson(result);
    }

}
