package top.jasonkayzk.ttmall.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.jasonkayzk.ttmall.common.utils.FtpUtil;
import top.jasonkayzk.ttmall.common.utils.IDUtils;
import top.jasonkayzk.ttmall.service.PictureService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 文件上传
 *
 * @author zk
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;

    @Value("${FTP_PORT}")
    private Integer FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;

    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Map<Object, Object> uploadPicture(MultipartFile uploadFile) {
        var resultMap = new HashMap<>(8);

        try {
            String oldName = uploadFile.getOriginalFilename();

            String newName = IDUtils.genImageName();

            newName = newName + Objects.requireNonNull(oldName).substring(oldName.lastIndexOf("."));

            String imagePath = new DateTime().toString("/yyyy/MM/dd");

            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
                    FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
            } else {
                resultMap.put("error", 0);
                resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
            }
            return resultMap;
        } catch (IOException e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }
}
