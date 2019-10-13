package com.crud.cloud.evanliu2968.util;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class DownloadFileUtil {

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, InputStream inputStream, String fileName) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        try {
            response.reset();
            //设置下载文件名
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            setResponse(response,fileName);
            response.setCharacterEncoding("UTF-8");
            byte[] bytes = new byte[4096];
            int readLength;
            while ((readLength = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, readLength);
            }
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    public static void downloadExcel(HttpServletRequest request, HttpServletResponse response, XSSFWorkbook xssfWorkbook, String fileName)throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xssfWorkbook.write(byteArrayOutputStream);
        xssfWorkbook.close();
        byteArrayOutputStream.close();
        download(request, response, byteArrayOutputStream.toByteArray(), fileName);
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @param data
     * @param fileName
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, byte[] data, String fileName) {
        OutputStream os = null;
        try {
            String userAgent = request.getHeader("User-Agent");
            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.reset();
            response.setContentType("application/force-download;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            response.setCharacterEncoding("UTF-8");

            os = response.getOutputStream();
            IOUtils.write(data, os);
        } catch (Exception e) {
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     *  替换下载的文件名称中的特殊字符串
     * @param response
     * @param fileName
     * @throws UnsupportedEncodingException
     */
    public static void setResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        fileName = replaceSymbol(fileName);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment; filename*=UTF-8''" + fileName);
    }

    public static String replaceSymbol(String fileName){
        return fileName.replace("/","_").replace("\\","_").replace("?","_").replace("*","_").replace("|","_").replace("\"","_").replace(":","_").replace("<","_").replace(">","_").replace(" ","_");
    }

}
