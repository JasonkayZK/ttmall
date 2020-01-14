package top.jasonkayzk.ttmall.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理工具类
 *
 * @author zk
 */
public class ExceptionUtil {

    /**
     * 获取异常的堆栈信息
     *
     * @param t 异常
     *
     * @return 异常信息
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();

        try (PrintWriter pw = new PrintWriter(sw)) {
            t.printStackTrace(pw);
            return sw.toString();
        }
    }

}
