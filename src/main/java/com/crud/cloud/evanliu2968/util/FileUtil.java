package com.crud.cloud.evanliu2968.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件处理工具类
 *
 * @author jinpeng.bu
 */
@Log4j2
public class FileUtil {

    private static long ONE_HOUR = 3600000L;

    //获取图片的字节码，临时使用
    public static byte[] getImageBytes(String imagePath) throws IOException {
        byte imageBytes[] = null;
        File file = new File(imagePath);
        if (file.exists()&&file.isFile()) {
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte bytes[] = new byte[1024];
            int lens = -1;
            try {
                while ((lens = is.read(bytes)) != -1) {
                    baos.write(bytes, 0, lens);
                }
                imageBytes = baos.toByteArray();
            } catch (IOException e) {
                throw new IOException(e);
            } finally {
                baos.flush();
                baos.close();
                is.close();
            }
        }
        return imageBytes;
    }
    public static byte[] getImageBytes(File imageFile) throws IOException {
        byte imageBytes[] = null;
        InputStream is = new FileInputStream(imageFile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte bytes[] = new byte[1024];
        int lens = -1;
        try {
            while ((lens = is.read(bytes)) != -1) {
                baos.write(bytes, 0, lens);
            }
            imageBytes = baos.toByteArray();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            baos.flush();
            baos.close();
            is.close();
        }
        return imageBytes;
    }
    /**
     * 上传时获取MultipartFile的流暂存到服务器本地
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            log.catching(e);
        }
    }

    /**
     * 目录下文件复制到另一目录
     *
     * @param oldPath 源目录
     * @param newPath 目标目录
     */
    public static void moveFiles(String oldPath, String newPath) throws IOException {
        String[] filePaths = new File(oldPath).list();
        if (filePaths != null && filePaths.length > 0) {
            if (!new File(newPath).exists()) {
                new File(newPath).mkdirs();
            }
            for (int i = 0; i < filePaths.length; i++) {
                if (new File((oldPath + File.separator + filePaths[i])).isDirectory()) {
                    moveFiles((oldPath + File.separator + filePaths[i]), (newPath + File.separator + filePaths[i]));
                } else if (new File((oldPath + File.separator + filePaths[i])).isFile()) {
                    //复制文件到另一个目录
                    copyFile((oldPath + File.separator + filePaths[i]), (newPath + File.separator + filePaths[i]));
                    //移动文件至另一个目录
//                    new File((oldPath + File.separator + filePaths[i])).renameTo(new File((newPath + File.separator + filePaths[i])));
                }
            }
        }
    }

    /**
     * 文件复制
     *
     * @param oldPath 源文件
     * @param newPath 目标文件
     * @throws IOException
     */
    public static void copyFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[2097152];
        while ((in.read(buffer)) != -1) {
            out.write(buffer);
        }
        out.close();
        in.close();
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param filePath 将要删除的文件目录路径
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(String filePath) {
        File dir = new File(filePath);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(filePath + File.separator + children[i]);
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    private static String ARCHPACKFILE = "archpackfile";
    /**
     * 递归删除临时文件
     * @param filePath 要删除文件所属目录
     * @param current 请求删除时的时间
     * @param delSelf 是否需要删除目录本身
     */
    public static void deleteDirFileBeforeOneHour(String filePath, long current, boolean delSelf) {
        File dir = new File(filePath);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            String childPath = null;
            File child = null;
            for (int i = 0; i < children.length; i++) {
                childPath = filePath + File.separator + children[i];
                child = new File(childPath);
                /** 打包文件模板目录不删除 **/
                if (child.getName().contains(ARCHPACKFILE)) {
                    continue;
                }
                if (child.isDirectory()) {
                    deleteDirFileBeforeOneHour(childPath, current, true);
                } else {
                    long lastModified = child.lastModified();
                    if ((current - lastModified) > ONE_HOUR) {
                        child.delete();
                    }
                }
            }
        }
        if (delSelf) {
            dir.delete();
        }
    }

    /**
     * 两张图片互换name
     */
    public static boolean updatePicName(String fileOnePath,String fileTwoPath){
        try {
            String folderPath = fileOnePath.substring(0, fileOnePath.lastIndexOf("/"));
            File fileOne = new File(fileOnePath);
            String oneName = fileOne.getName();
            File fileTwo = new File(fileTwoPath);
            String twoName = fileTwo.getName();
            String toOneFileName = folderPath + "/evanliu2968" + oneName;
            String toTwoFileName = folderPath + "/evanliu2968" + twoName;
            File toOneFile = new File(toOneFileName);
            File toTwoFile = new File(toTwoFileName);
            //复制第一张图片
            try {
                copyFile(fileOnePath,toOneFileName);
                copyFile(fileTwoPath,toTwoFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileOne.delete();
            fileTwo.delete();
            toOneFile.renameTo(fileTwo);
            toTwoFile.renameTo(fileOne);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 根据xml模板用freeMarker生成word文档
     * @param filePath
     * @param templateName
     * @param eList
     * @return
     */
    public static File createDoc(String filePath, String templateName, List<?> eList){
        // 创建数据
        Map<String,Object> dataMap = new HashMap<String, Object>();
        dataMap.put("eList", eList);
        return createDoc(filePath, templateName, dataMap);
    }
    public static File createDoc(String filePath, String templateName, Map<String,Object> dataMap){
        // 获取模板
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(FileUtil.class, "/tpl");
        Template t = null;
        File file = new File(filePath);
        try {
            file.createNewFile();
            t = configuration.getTemplate(templateName);
            t.setEncoding("UTF-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
            t.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            log.error("根据xml模板用freeMarker生成word文档IO异常", e);
        } catch (TemplateException e) {
            log.error("根据xml模板用freeMarker生成word文档模板异常", e);
        }
        return file;
    }
}
