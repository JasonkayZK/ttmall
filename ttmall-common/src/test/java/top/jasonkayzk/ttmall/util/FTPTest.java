package top.jasonkayzk.ttmall.util;

import org.apache.commons.net.ftp.FTP;
import java.io.File;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Ignore;
import org.junit.Test;
import top.jasonkayzk.ttmall.common.utils.FtpUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Ignore
public class FTPTest {

    @Test
    public void testFtpClient() throws IOException {
        FTPClient ftpClient = null;

        try {
            //创建一个FtpClient对象
            ftpClient = new FTPClient();

            //创建ftp连接。默认是21端口
            ftpClient.connect("172.17.0.3", 21);

            //登录ftp服务器，使用用户名和密码
            ftpClient.login("zk", "zk137818");

            //上传文件
            //读取本地文件
            var stream = new FileInputStream(new File("/home/zk/Pictures/avatar.jpeg"));

            //设置上传的路径
            ftpClient.changeWorkingDirectory("/home/zk/www/images");

            //修改上传文件的格式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //第一个参数：服务器端文档名
            //第二个参数：上传文档的inputStream
            ftpClient.storeFile("hello1.jpeg", stream);
        } finally {
            //关闭连接
            if (ftpClient != null) {
                ftpClient.logout();
            }
        }
    }

    @Test
    public void testFtpUtil() throws FileNotFoundException {
        FileInputStream in = new FileInputStream(new File("/home/zk/Pictures/avatar.jpeg"));
        boolean flag = FtpUtil.uploadFile("172.17.0.3", 21, "zk", "zk137818", "/home/vsftpd/zk/", "2020/01/13", "hello2.jpg", in);
        System.out.println(flag);
    }

}
