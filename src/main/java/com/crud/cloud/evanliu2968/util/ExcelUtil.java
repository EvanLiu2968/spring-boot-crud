package com.crud.cloud.evanliu2968.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.crud.cloud.evanliu2968.dto.response.person.ContactRes;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 操作Excel表格的功能类
 *
 * @author jinpeng.bu
 */
public class ExcelUtil {
    public static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static XSSFRow row;

    private final static int initRowNum = 100;

    private final static short borderColor = HSSFColor.WHITE.index;

    /**
     * 读取Excel表格表头(首行)的内容
     *
     * @param is
     * @return String 表头内容的数组
     */
    public static String[] readExcelTitle(InputStream is) {
        try {
            // fs = new POIFSFileSystem(is);
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            // title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }

    /**
     * 读取Excel数据内容(剔除begin行)
     *
     * @param is
     * @return Map 包含单元格数据内容的Map对象
     */
    public static Map<Integer, String> readExcelContent(InputStream is, String sheetName, Integer begin) {
        Map<Integer, String> content = new HashMap<Integer, String>();
        String str = "";
        try {
            // fs = new POIFSFileSystem(is);
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sheetName != null && sheetName.length() > 0) {
            sheet = wb.getSheet(sheetName);
        } else {
            sheet = wb.getSheetAt(0);
        }
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        System.out.println("总行数：" + rowNum);
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = begin; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            List<String> values = Lists.newArrayList();
            while (j < colNum) {
                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
                // str += getStringCellValue(row.getCell((short) j)).trim() +
                // "-";
                values.add(getCellFormatValue(row.getCell((short) j)).trim());
                // str += getCellFormatValue(row.getCell((short) j)).trim() +
                // " * ";
                j++;
            }
            str = Joiner.on("*").skipNulls().join(values);
            content.put(i, str);
        }
        return content;
    }

    /**
     * 根据HSSFCell类型设置数据
     *
     * @param cell
     * @return
     */
    private static String getCellFormatValue(XSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case XSSFCell.CELL_TYPE_NUMERIC:
                case XSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        // 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();

                        // 方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case XSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

    /**
     * 创建excel
     *
     * @param fileName  http://www.bestwehotel.com/download/innfile/适用酒店导出_20160331_1459353600000.xsl
     * @param sheetName test
     * @param listMap   数据
     */
    public static void create(String fileName, String sheetName, List<Map<String, Object>> listMap) {
        try {
            // 创建工作薄
            XSSFWorkbook xwb = new XSSFWorkbook();
            /**
             * sheetName名称不能超过31个字符，并且不能输入以下字符
             * "0x0000"，"0x0003"，"："，"\"，"*"，"？"，"/"，"["，"]"
             */
            // xwb.setSheetName(0, sheetName);
            // 使用安全方式创建sheetName，无效名称会变为空字符，例如[O'Brien's sales*?]
            // ->" O'Brien's sales   "
            String safeName = WorkbookUtil.createSafeSheetName(sheetName);
            // 创建工作表
            XSSFSheet xs = xwb.createSheet(safeName);

            XSSFRow xr = null;
            for (int i = 0; i < listMap.size(); i++) {
                // 创建行
                xr = xs.createRow(i);
                // 获取每行数据
                Map<String, Object> map = listMap.get(i);
                // 循环放入单元格中
                int j = 0;
                for (Object value : map.values()) {
                    if (value instanceof Integer) {
                        xr.createCell(j).setCellValue((Integer) value);
                    }
                    if (value instanceof String) {
                        xr.createCell(j).setCellValue((String) value);
                    }
                    if (value instanceof List) {

                    }
                    j++;
                }

            }

            // 输出文件
            FileOutputStream fileOut = new FileOutputStream(fileName);
            xwb.write(fileOut);
            // 关闭文件流
            fileOut.close();
            // 关闭工作薄
//			xwb.close();

        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("创建excel", e);
            }
        }
    }

    /**
     * 创建excel流
     *
     * @param sheetName
     * @param listMap
     * @param outputStream
     */
    public static void create(String sheetName, List<Map<String, Object>> listMap, OutputStream outputStream) {
        try {
            // 创建工作薄
            XSSFWorkbook xwb = new XSSFWorkbook();
            String safeName = WorkbookUtil.createSafeSheetName(sheetName);
            // 创建工作表
            XSSFSheet xs = xwb.createSheet(safeName);

            XSSFRow xr = null;
            for (int i = 0; i < listMap.size(); i++) {
                // 创建行
                xr = xs.createRow(i);
                // 获取每行数据
                Map<String, Object> map = listMap.get(i);
                // 循环放入单元格中
                int j = 0;
                for (Object value : map.values()) {
                    if (value instanceof Integer) {
                        xr.createCell(j).setCellValue((Integer) value);
                    }
                    if (value instanceof String) {
                        xr.createCell(j).setCellValue((String) value);
                    }
                    j++;
                }

            }
            xwb.write(outputStream);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("创建excel", e);
            }
        }
    }

    /**
     * 读取Excel 格式：key 行 value 值
     *
     * @param readFile
     * @param sheet
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> readExcelHSSF(FileInputStream readFile, int sheet) throws IOException {

        //返回信息
        List<Map<String, Object>> excelInfoList = new ArrayList<>();

        try {
            //创建文件读取流

            //创建一个WorkBook指定读取到的流
            HSSFWorkbook wb = new HSSFWorkbook(readFile);
            //获得有多少页
//			logger.debug(wb.getNumberOfSheets());
            //获取名字为“测试页”的表
            HSSFSheet st = wb.getSheetAt(sheet);
            //获取行数
//			logger.debug(st.getPhysicalNumberOfRows());
            int totalRows = st.getPhysicalNumberOfRows();
            int totalColumns = st.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < totalRows; i++) {
                //获取第一行
                HSSFRow row = st.getRow(i);
                //获取第一行的列数
//				logger.debug(row.getPhysicalNumberOfCells());
                int totalCells = row.getPhysicalNumberOfCells();
                if (totalCells < totalColumns) {
                    totalCells = totalColumns;
                }
                //每列数据
                Map<String, Object> cellMap = new HashMap<String, Object>();
                for (int j = 0; j < totalCells; j++) {

                    //获取第一个单元格
                    HSSFCell cell = row.getCell(j);
                    if (cell == null) {
                        cellMap.put("" + j, "");
                        continue;
                    }

                    //设置文本
                    cell.setCellType(Cell.CELL_TYPE_STRING);

                    //获取样式
//					HSSFCellStyle cellStyle=cell.getCellStyle();
                    //获得字体颜色
//					logger.debug(cellStyle.getFont(wb).getColor());
//					System.out.println("cell.getStringCellValue()==="+cell.getStringCellValue());
                    //返回字符串
                    HSSFRichTextString rts = cell.getRichStringCellValue();
                    String reStr = rts.getString();
                    cellMap.put("" + j, reStr);

                }
                excelInfoList.add(cellMap);

            }


        } finally {
            if (readFile != null) {
                //关闭流
                readFile.close();
            }
        }
        return excelInfoList;


    }

    public static List<Map<String, Object>> readExcelXSSF(FileInputStream readFile, int sheet) throws IOException {

        //返回信息
        List<Map<String, Object>> excelInfoList = new ArrayList<>();

        try {
            //创建文件读取流

            //创建一个WorkBook指定读取到的流
            XSSFWorkbook wb = new XSSFWorkbook(readFile);
            //获得有多少页
//			logger.debug(wb.getNumberOfSheets());
            //获取名字为“测试页”的表
            XSSFSheet st = wb.getSheetAt(sheet);
            //获取行数
//			logger.debug(st.getPhysicalNumberOfRows());
            int totalRows = st.getPhysicalNumberOfRows();
            int totalColumns = st.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < totalRows; i++) {
                //获取第一行
                XSSFRow row = st.getRow(i);
                //获取第一行的列数
//				logger.debug(row.getPhysicalNumberOfCells());
                int totalCells = row.getPhysicalNumberOfCells();
                if (totalCells < totalColumns) {
                    totalCells = totalColumns;
                }
                //每列数据
                Map<String, Object> cellMap = new HashMap<String, Object>();
                for (int j = 0; j < totalCells; j++) {

                    //获取第一个单元格
                    XSSFCell cell = row.getCell(j);
                    if (cell == null) {
                        cellMap.put("" + j, "");
                        continue;
                    }

                    //设置文本
                    //cell.setCellType(Cell.CELL_TYPE_STRING);

                    //获取样式
//					XSSFCellStyle cellStyle=cell.getCellStyle();
                    //获得字体颜色
//					logger.debug(cellStyle.getFont(wb).getColor());
//					System.out.println("cell.getStringCellValue()==="+cell.getStringCellValue());
                    //返回字符串
                    /*XSSFRichTextString rts = cell.getRichStringCellValue();
                    String reStr = rts.getString();
                    cellMap.put("" + j, reStr);*/


                    String cellValue = PoiUtil.getCellValue(cell);
                    cellMap.put("" + j, cellValue);

                }
                excelInfoList.add(cellMap);

            }


        } finally {
            if (readFile != null) {
                //关闭流
                readFile.close();
            }
        }
        return excelInfoList;


    }


    public static XSSFSheet createSheet(XSSFWorkbook workbook, String sheetName, Map<String, Boolean> headers, Map<Integer, Integer> column, Map<Integer, List<String>> select, Map<Integer, String> cellFomat,String remarks,int size) {

        int initRowCount = 10010;

        XSSFSheet sheet = workbook.createSheet(sheetName);
        /**开始填充单元格**/
        CellRangeAddress region;
        CellStyle headerStyle = getHeaderStyle(workbook,2);
        XSSFRow row;
        XSSFCell cell;
        for (int i = 0; i < initRowCount; i++) {
            row = sheet.createRow(i);
        }

        //CellStyle dataStyle = getDataStyle(workbook);


        XSSFFont fontRed = workbook.createFont();
        fontRed.setBold(true);
        fontRed.setColor(new XSSFColor(new java.awt.Color(255, 0, 0)));

        XSSFFont fontWhite = workbook.createFont();
        fontWhite.setBold(true);
        fontWhite.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
        fontWhite.setFontName("微软雅黑");


        row = sheet.getRow(0);//创建第一行表头
        row.setHeightInPoints(24);
        if (headers != null && headers.size() > 0) {
            int i = 0;
            Iterator headerIt = headers.keySet().iterator();
            while (headerIt.hasNext()) {
                String filedName = (String) headerIt.next();
                Boolean is = headers.get(filedName);//是否必填
                cell = row.createCell(i);
                XSSFRichTextString rt = new XSSFRichTextString("");
                if (is) {
                    rt.append("*", fontRed);
                    rt.append(filedName, fontWhite);
                } else {
                    rt.append(filedName, fontWhite);
                }
                cell.setCellValue(rt);
                cell.setCellStyle(headerStyle);
                i++;
            }
        }

        if (column != null && column.size() > 0) {
            Iterator columnIt = column.keySet().iterator();
            while (columnIt.hasNext()) {
                Integer index = (Integer) columnIt.next();
                sheet.setColumnWidth(index, column.get(index));
            }
        }

        if (select != null && select.size() > 0) {
            Iterator selectIt = select.keySet().iterator();
            while (selectIt.hasNext()) {
                Integer index = (Integer) selectIt.next();
                List<String> selectValue = select.get(index);
                XSSFRow row2 = sheet.getRow(1);//创建第一行表头
                cell = row2.createCell(index);
                cell.setCellValue(selectValue.get(0));
                //cell.setCellStyle(dataStyle);
                XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
                XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(selectValue.toArray(new String[selectValue.size()]));
                CellRangeAddressList addressList = new CellRangeAddressList(1, initRowCount, index, index);
                XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
                //设置下拉框校验提示
                validation.setSuppressDropDownArrow(true);
                validation.setShowErrorBox(true);
                sheet.addValidationData(validation);
            }
        }

        if (cellFomat != null && cellFomat.size() > 0) {
            XSSFRow rowId;
            XSSFCell cell1;
            // 设置为文本格式，防止身份证号变成科学计数法
            /*CellStyle style1 = dataStyle;
            DataFormat format = workbook.createDataFormat();
            style1.setDataFormat(format.getFormat("@"))*/;

            Iterator cellFomatIt = cellFomat.keySet().iterator();
            int a = 0;
            while (cellFomatIt.hasNext()) {
                Integer index = (Integer) cellFomatIt.next();
                String type = cellFomat.get(index);
                if ("INPUT".equals(type)) {
                    for (int j = 1; j < initRowCount; j++) {
                        rowId = sheet.getRow(j);
                        cell1 = rowId.createCell(index);
                        //cell1.setCellStyle(style1);
                    }
                    rowId = sheet.getRow(1);
                    cell1 = rowId.getCell(index);
                    //cell1.setCellStyle(style1);

                } else if ("DATE".equals(type)) {
                    for (int j = 1; j < initRowCount; j++) {
                        rowId = sheet.getRow(j);
                        cell1 = rowId.createCell(index);
                        //cell1.setCellStyle(style1);
                    }
                    rowId = sheet.getRow(1);
                    cell1 = rowId.getCell(index);
                    cell1.setCellValue(TimeUtil.formatDateYYYY_MM_dd(new Date()));
                    //cell1.setCellStyle(style1);
                }
            }
        }

        /**写备注**/
        if(!StringUtil.isEmpty(remarks)){
            row = sheet.createRow(17);
            row.setHeightInPoints(counter(remarks, '\n'));
            cell = row.createCell(0);
            cell.setCellValue(remarks);

            region = new CellRangeAddress(17, 17, 0, 9);

            sheet.addMergedRegion(region);
            addStyle(region, sheet, workbook, getRemarkStyle(workbook));
        }

        return sheet;
    }

    public static CellStyle getHeaderStyle(XSSFWorkbook workbook,Integer colorType) {
        XSSFCellStyle headStyle = workbook.createCellStyle();

        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居左

        setBorderColor(headStyle);

        headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        headStyle.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
        headStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(79, 129, 189)));

        Font font = workbook.createFont();
        if(colorType == 1){
            font.setColor(HSSFColor.YELLOW.index);
        }else{
            font.setColor(HSSFColor.WHITE.index);
        }
        font.setFontHeightInPoints((short) 11);
        font.setFontName("微软雅黑");
        font.setBold(true);
        headStyle.setFont(font);

        return headStyle;
    }


    /**
     * 拿说明的style
     *
     * @param workbook
     * @return
     */
    public static CellStyle getRemarkStyle(XSSFWorkbook workbook) {
        XSSFCellStyle remarkStyle = workbook.createCellStyle();

        remarkStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        remarkStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        remarkStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        remarkStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        remarkStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        setBorderColor(remarkStyle);

//        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        remarkStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 居左
        remarkStyle.setVerticalAlignment(VerticalAlignment.TOP);//顶端对齐

        remarkStyle.setWrapText(true);//先设置为自动换行
        remarkStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        remarkStyle.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
        remarkStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 204)));

        XSSFFont font = workbook.createFont();
        font.setColor(new XSSFColor(new java.awt.Color(192, 0, 0)));
        font.setFontHeightInPoints((short) 10);
        font.setFontName("微软雅黑");
        remarkStyle.setFont(font);

        return remarkStyle;
    }

    public static void addStyle(CellRangeAddress region, Sheet sheet, Workbook workbook, CellStyle cellStyle) {
        int border = 1;
        RegionUtil.setBorderBottom(border, region, sheet, workbook);
        RegionUtil.setBorderLeft(border, region, sheet, workbook);
        RegionUtil.setBorderRight(border, region, sheet, workbook);
        RegionUtil.setBorderTop(border, region, sheet, workbook);

        if (cellStyle != null) {
            for (int rowIndex = region.getFirstRow(); rowIndex <= region.getLastRow(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                for (int columnIndex = region.getFirstColumn(); columnIndex <= region.getLastColumn(); columnIndex++) {
                    row.getCell(columnIndex).setCellStyle(cellStyle);
                }
            }
        }

    }

    public static CellStyle setBorderColor(CellStyle cellStyle) {
        cellStyle.setBottomBorderColor(borderColor);
        cellStyle.setLeftBorderColor(borderColor);
        cellStyle.setRightBorderColor(borderColor);
        cellStyle.setTopBorderColor(borderColor);

        return cellStyle;
    }

    /**
     * 计算备注所在行的高度
     *
     * @param s
     * @param c
     * @return
     */
    public static int counter(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        int row = count * 19;
        return row;
    }


    public static void setCellValue(List<ContactRes> contactList, XSSFSheet sheet) {
        List<List<String>> cellDatas = new ArrayList<>();
        if(CollectionsUtil.isNotEmpty(contactList)){
            for (ContactRes contactRes : contactList) {
                cellDatas.add(Arrays.asList(contactRes.getPersonId().toString(),contactRes.getName(),contactRes.getEmail()));
            }
            for(int i = 1 ; i < contactList.size() + 1 ; i++){
                XSSFRow row = sheet.getRow(i);

                List<String> rowData = cellDatas.get(i - 1);
                for(int n = 0; n < 3 ; n++){
                    XSSFCell cell = row.createCell(n);
                    cell.setCellValue(rowData.get(n));
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            //创建测试数据
            createXSSFTest();
            // 对读取Excel表格标题测试
            InputStream is = new FileInputStream("D:/test.xlsx");
            ExcelUtil excelReader = new ExcelUtil();
            String[] title = excelReader.readExcelTitle(is);
            System.out.println("获得Excel表格的标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }

            // 对读取Excel表格内容测试
            InputStream is2 = new FileInputStream("D:/test.xlsx");
            Map<Integer, String> map = excelReader.readExcelContent(is2, null, 1);
            System.out.println("获得Excel表格的内容:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(map.get(i));
            }

        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }
    }

    /**
     * 创建测试数据
     */
    private static void createXSSFTest() {
        String fileName = "D:/test.xlsx";
        String sheetName = "test";
        List<Map<String, Object>> listMap = Lists.newArrayList();
        Map<String, Object> map = Maps.newLinkedHashMap();
        map.put("编号", "编号");
        map.put("英文", "英文");
        map.put("中文", "中文");
        map.put("映射编号", "映射编号");
        map.put("映射名称", "映射名称");

        Map<String, Object> map1 = Maps.newLinkedHashMap();
        map1.put("code", "1");
        map1.put("nameEn", "abc");
        map1.put("name", "龙林");
        map1.put("codeMapping", "01");
        map1.put("nameMapping", "龙林01");
        listMap.add(map);
        listMap.add(map1);

        ExcelUtil.create(fileName, sheetName, listMap);
    }

}