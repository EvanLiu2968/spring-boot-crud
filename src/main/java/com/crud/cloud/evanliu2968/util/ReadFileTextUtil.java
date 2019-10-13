package com.crud.cloud.evanliu2968.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class ReadFileTextUtil {
    //读取Excel2007+的全部内容 xlsx
    public static String getTextFromExcel2007(String filePath) {
        InputStream is = null;
        XSSFWorkbook workBook = null;
        String text="";
        try {
            is = new FileInputStream(filePath);
            workBook = new XSSFWorkbook(is);
            XSSFExcelExtractor extractor=new XSSFExcelExtractor(workBook);
            text=extractor.getText();
            //extractor.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定路径"+filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("getTextFromExcel2007 IO错误");
            e.printStackTrace();
        }
        return text;
    }

    //直接读取Excel97-2003的全部内容xls
    public static String getTextFromExcel(String filePath){
        InputStream is = null;
        HSSFWorkbook wb = null;
        String text="";
        try {
            is = new FileInputStream(filePath);
            wb = new HSSFWorkbook(new POIFSFileSystem(is));
            ExcelExtractor extractor=new ExcelExtractor(wb);
            extractor.setFormulasNotResults(false);
            extractor.setIncludeSheetNames(true);
            text=extractor.getText();
            //extractor.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定路径"+filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("getTextFromExcel IO错误");
            e.printStackTrace();
        }
        return text;
    }

    //docx
    public static void getTextFromDocx(String path) throws IOException {
        File file = new File(path);
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            String doc1 = extractor.getText();
            System.out.println(doc1);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //doc
    public static void getTextFromDoc(String path) throws IOException {
        File file = new File(path);
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            HWPFDocument doc = new HWPFDocument(fis);
            String doc1 = doc.getDocumentText();
            System.out.println(doc1);
            StringBuilder doc2 = doc.getText();
            System.out.println(doc2);
            Range rang = doc.getRange();
            String doc3 = rang.text();
            System.out.println(doc3);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //pdf
    public static String getTextFromPdf(String path) throws Exception {
        File pdfFile=new File(path);
        PDDocument document = null;
        String content = "";
        try {
            document=PDDocument.load(pdfFile);
            int pages = document.getNumberOfPages();
            // 读文本内容
            PDFTextStripper stripper=new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);
            stripper.setEndPage(pages);
            content = stripper.getText(document);
            System.out.println(content);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return content;
    }

    //ppt
    public static String getTextFromPPT(String filePath) {
        InputStream is = null;
        PowerPointExtractor extractor = null;
        String text="";
        try {
            is = new FileInputStream(filePath);
            extractor = new PowerPointExtractor(is);
            text=extractor.getText();
            //extractor.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定路径"+filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("getTextFromPPT IO错误");
            e.printStackTrace();
        }
        return text;
    }

    //抽取幻灯片2007+全部内容 pptx
    public static String getTextFromPPT2007(String filePath){
        InputStream is = null;
        XMLSlideShow slide = null;
        String text="";
        try {
            is = new FileInputStream(filePath);
            slide = new XMLSlideShow(is);
            XSLFPowerPointExtractor extractor=new XSLFPowerPointExtractor(slide);
            text=extractor.getText();
            //extractor.close();
        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定路径"+filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("getTextFromPPT2007 IO错误");
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 获取文件格式
     * @param path
     * @return
     */
    public static String getFileEncode(String path) {
        String charset ="asci";
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null;
        try {
            boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(path));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "Unicode";//UTF-16LE
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "Unicode";//UTF-16BE
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int len = 0;
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) //单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF)
                            //双字节 (0xC0 - 0xDF) (0x80 - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    }
                    else if (0xE0 <= read && read <= 0xEF) { //也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        }
                        else
                            break;
                    }
                }
                //TextLogger.getLogger().info(loc + " " + Integer.toHexString(read));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException ex) {
                }
            }
        }
        return charset;
    }

    /**
     * 通过路径获取文件的内容，这个方法因为用到了字符串作为载体，为了正确读取文件（不乱码），只能读取文本文件，安全方法
     * @param path
     * @return txt
     */
    public static String getTextFromTxtEX(String path){
        String data = null;
        // 判断文件是否存在
        File file = new File(path);
        if(!file.exists()){
            return data;
        }
        // 获取文件编码格式
        String code = getFileEncode(path);
        InputStreamReader isr = null;
        try{
            // 根据编码格式解析文件
            if("asci".equals(code)){
                // 这里采用GBK编码，而不用环境编码格式，因为环境默认编码不等于操作系统编码
                // code = System.getProperty("file.encoding");
                code = "GBK";
            }
            isr = new InputStreamReader(new FileInputStream(file),code);
            // 读取文件内容
            int length = -1 ;
            char[] buffer = new char[1024];
            StringBuffer sb = new StringBuffer();
            while((length = isr.read(buffer, 0, 1024) ) != -1){
                sb.append(buffer,0,length);
            }
            data = new String(sb);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(isr != null){
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**
     *处理txt
     *@parampath
     *@return
     *@throwsIOException
     */
    public static String getTextFromTxt(String path)throws IOException {
        StringBuffer sb=new StringBuffer("");
        InputStream is=new FileInputStream(path);
        //必须设置成GBK，否则将出现乱码
        BufferedReader reader=new BufferedReader(new InputStreamReader(is,"GBK"));
        try{
            String line="";
            while((line=reader.readLine())!=null){
                sb.append(line+"\r");
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return sb.toString().trim();
    }

    /**
     * html
     * @param urlString
     * @return
     */
    public static String getTextFromHtml(String urlString) {

        StringBuffer content = new StringBuffer("");
        File file = new File(urlString);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            // 读取页面
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    fis,"utf-8"));//这里的字符编码要注意，要对上html头文件的一致，否则会出乱码

            String line = null;

            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contentString = content.toString();
        return contentString;
    }

    public static  void main(String[] args) throws Exception{
        String filePath="f:/发文审批-关于XXXX相关人员职务任命的通知.doc";
        ReadFileTextUtil.getTextFromDoc(filePath);
    }

}
