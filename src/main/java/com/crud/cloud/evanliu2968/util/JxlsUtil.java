package com.crud.cloud.evanliu2968.util;

import com.crud.cloud.evanliu2968.util.jslxfunctions.ColorCellValue;
import com.crud.cloud.evanliu2968.util.jslxfunctions.DropdownCellValue;
import com.crud.cloud.evanliu2968.util.jslxfunctions.EachCommand;
import com.crud.cloud.evanliu2968.util.jslxfunctions.MergeCellValue;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.transform.poi.WritableCellValue;
import org.jxls.transform.poi.WritableHyperlink;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class JxlsUtil {
    static {
        //添加自定义指令（可覆盖jxls原指令）
        XlsCommentAreaBuilder.addCommandMapping("each", EachCommand.class);
    }

    private static void write(InputStream inputStream, OutputStream outputStream, Map<String, Object> data, String sheetCellAt) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);

        Context context = PoiTransformer.createInitialContext();

        for (Iterator<String> iterator = data.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            context.putVar(key, data.get(key));
        }

        JxlsHelper.getInstance().processTemplateAtCell(inputStream, bos, context, sheetCellAt);
    }

    public static void write(HttpServletRequest request, InputStream inputStream, HttpServletResponse response, Map<String, Object> data, String fileName, String sheetCellAt) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            JxlsUtil.setResponse(response, fileName);
            response.setCharacterEncoding("UTF-8");
            write(inputStream, outputStream, data, sheetCellAt);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void write(InputStream inputStream, String filePath, Map<String, Object> data, String sheetCellAt) throws IOException {
        OutputStream outputStream = new FileOutputStream(filePath);
        try {
            write(inputStream, outputStream, data, sheetCellAt);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 替换下载的文件名称中的特殊字符串
     *
     * @param response
     * @param fileName
     * @throws UnsupportedEncodingException
     */
    public static void setResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        fileName = replaceSymbol(fileName);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment; filename*=UTF-8''" + fileName);
    }

    public static String replaceSymbol(String fileName) {
        return fileName.replace("/", "_").replace("\\", "_").replace("?", "_").replace("*", "_").replace("|", "_").replace("\"", "_").replace(":", "_").replace("<", "_").replace(">", "_").replace(" ", "_");
    }

    /**
     * 带下拉框的Excel模板导出
     *
     * @param inputStream 模板文件输入流
     * @param response    http返回
     * @param data        输出数据载体，支持多个下拉框，英文逗号隔开的value是下拉框待选项，key是模板中对应下拉框的splitItem名称
     * @param fileName    输出的文件名称
     * @param sheetCellAt 工作表的名称即可，如sheet1
     * @throws IOException
     */
    public static void writeWithDropDown(InputStream inputStream, HttpServletResponse response, Map<String, Object> data, String fileName, String sheetCellAt) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        try {
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            JxlsUtil.setResponse(response, fileName);
            response.setCharacterEncoding("UTF-8");
            Context context = PoiTransformer.createInitialContext();
            //处理数据信息
            for (Iterator<String> iterator = data.keySet().iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                context.putVar(key, data.get(key));
            }
            //引入自定义函数
            Map<String, Object> myFunction = new HashMap<String, Object>();
            myFunction.put("my", new JxlsUtil());
            myFunction.put("jx", new JxlsUtil());    //添加自定义功能
            myFunction.put("mg", new JxlsUtil());
            Transformer trans = TransformerFactory.createTransformer(inputStream, bos);
            JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) trans.getTransformationConfig().getExpressionEvaluator();
            evaluator.getJexlEngine().setFunctions(myFunction);
            //载入模板、处理导出
            AreaBuilder areaBuilder = new XlsCommentAreaBuilder(trans);
            List<Area> areaList = areaBuilder.build();
            areaList.get(0).applyAt(new CellRef(sheetCellAt + "!A1"), context);
            trans.write();
            //释放资源
            bos.flush();
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void writeWithDropDown(InputStream inputStream, BufferedOutputStream bos, Map<String, Object> data, String sheetCellAt) throws IOException {
        Context context = PoiTransformer.createInitialContext();
        //处理数据信息
        for (Iterator<String> iterator = data.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            context.putVar(key, data.get(key));
        }
        //引入自定义函数
        Map<String, Object> myFunction = new HashMap<String, Object>();
        myFunction.put("my", new JxlsUtil());
        myFunction.put("jx", new JxlsUtil());    //添加自定义功能
        myFunction.put("mg", new JxlsUtil());
        Transformer trans = TransformerFactory.createTransformer(inputStream, bos);
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) trans.getTransformationConfig().getExpressionEvaluator();
        evaluator.getJexlEngine().setFunctions(myFunction);
        //载入模板、处理导出
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(trans);
        List<Area> areaList = areaBuilder.build();
        areaList.get(0).applyAt(new CellRef(sheetCellAt + "!A1"), context);
        trans.write();
        //释放资源
        bos.flush();
        if (bos != null) {
            bos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    //返回大的数
    public Integer max(Integer x, Integer y) {
        return x > y ? x : y;
    }

    //给金额前面显示个货币符号
    public Object formatMoney(Object a) {
        Object result = null;
        if (a != null) {
            return "￥" + a;
        }
        return result;
    }

    //超链接
    public WritableCellValue myHyperlink(String address, String title) {
        return new WritableHyperlink(address, title);
    }

    //按值显示背景色
    public WritableCellValue showColor(Integer value) {
        return new ColorCellValue(value);
    }

    //生成下拉菜单
    public WritableCellValue downlist(String splitItem, String value) {
        return new DropdownCellValue(splitItem, value);
    }
    //单元格合并
    public WritableCellValue mergeCell(String value , Integer mergerRows) {
        return new MergeCellValue(value , mergerRows);
    }
//    public static void write(File file, OutputStream outputStream, Map<String, Object> data, String sheetCellAt) throws IOException {
//        InputStream inputStream = new FileInputStream(file);
//        write(inputStream, outputStream, data, sheetCellAt);
//    }
//    public static void write(HttpServletRequest request, File file, HttpServletResponse response, Map<String, Object> data, String filename, String sheetCellAt) throws IOException {
//        InputStream inputStream = new FileInputStream(file);
//        write(request,inputStream, response, data, filename, sheetCellAt);
//    }

}
