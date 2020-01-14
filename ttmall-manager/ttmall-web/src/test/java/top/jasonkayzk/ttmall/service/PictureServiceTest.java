package top.jasonkayzk.ttmall.service;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import top.jasonkayzk.ttmall.service.impl.PictureServiceImpl;

import java.io.*;

@Ignore
public class PictureServiceTest {

    @Test
    public void uploadTest() throws IOException {
        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");

        var pictureService = applicationContext.getBean(PictureService.class);

        // 将File转为MultipartFile
        var file = new File("/home/zk/Pictures/idea_bg.jpeg");
        MultipartFile uploadFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(new FileInputStream(file)));
        var res = pictureService.uploadPicture(uploadFile);

        System.out.println(res);
    }

}
