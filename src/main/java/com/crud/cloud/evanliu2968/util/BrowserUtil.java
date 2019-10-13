package com.crud.cloud.evanliu2968.util;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class BrowserUtil {

    // 以下为服务器端判断客户端浏览器类型的方法
    public static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (UserAgent != null) {
            if (UserAgent.indexOf("msie") >= 0)
                return "IE";
            if (UserAgent.indexOf("firefox") >= 0)
                return "FF";
            if (UserAgent.indexOf("safari") >= 0)
                return "SF";
        }
        return null;
    }




    public static void workbookWrite(HttpServletRequest request, HttpServletResponse response,String fileName,XSSFWorkbook workbook,OutputStream output) throws IOException {
        try {
            response.reset();
            if ("FF".equals(getBrowser(request))) {
                response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            } else {
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            }
            response.setContentType("application/octet-stream; charset=utf-8");
            workbook.write(output);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

}
