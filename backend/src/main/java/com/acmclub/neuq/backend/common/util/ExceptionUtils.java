package com.acmclub.neuq.backend.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 使用代码规范中的打印异常静态类
 */
public class ExceptionUtils {

    public static String getStackTrace(Throwable throwable) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            throwable.printStackTrace(pw);
            return sw.getBuffer().toString();
        } catch (Exception var2) {
            return "";
        }
    }

}