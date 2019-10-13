package com.crud.cloud.evanliu2968.util;

import com.crud.cloud.evanliu2968.constant.DateStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by evanliu2968 on 2019-10-14.
 */
public class PoiUtil {

    public static void main(String args[]) throws Exception {

        List<Map<Integer, String>> list2 = readExcel("E:/test1.xls");
//            List<Map<Integer, String>> list = readExcel("E:/test1.xlsx");
    }

    public static List<List<String>> readExcelForList(InputStream fis, String extension) throws Exception {
        Workbook wb = null;
        List<List<String>> list = null;
        try {
            wb = WorkbookFactory.create(fis);
            list = readWorkbookForList(wb);

            return list;
        } finally {
            if (null != wb) {
                wb.close();
            }

            if (null != fis) {
                fis.close();
            }
        }
    }

    public static List<List<String>> readSheetForList(InputStream fis,int sheetNum) throws Exception {
        Workbook wb = null;
        List<List<String>> list = null;
        try {
            wb = WorkbookFactory.create(fis);
            list = readSheetForList(wb,sheetNum);

            return list;
        } finally {
            if (null != wb) {
                wb.close();
            }

            if (null != fis) {
                fis.close();
            }
        }
    }

    protected static List<List<String>> readSheetForList(Workbook wb,int sheetNum) throws Exception {
        List<List<String>> list = new LinkedList<List<String>>();

        int total = wb.getNumberOfSheets();

        if (sheetNum <= total){
            Sheet sheet = wb.getSheetAt(sheetNum);
            rowToList(sheet, list);
        }

        return list;
    }

    public static Map<String, List<List<String>>> readExcelForSheet(InputStream fis, String extension) throws Exception {
        Workbook wb = null;
        Map<String, List<List<String>>> map = null;
        try {
            wb = WorkbookFactory.create(fis);
            map = readWorkbookForSheet(wb);

            return map;
        } finally {
            if (null != wb) {
                wb.close();
            }

            if (null != fis) {
                fis.close();
            }
        }

    }

    /**
     * 把所有的list都统一成第一个list的大小，增加的都是空字符串
     *
     * @param stringListMap
     * @return
     */

    public static Map<String, List<List<String>>> uniformListSize(Map<String, List<List<String>>> stringListMap) {
        for (Iterator<String> iterator = stringListMap.keySet().iterator(); iterator.hasNext(); ) {
            String s = iterator.next();
            List<List<String>> rowStrs = stringListMap.get(s);
            if (rowStrs != null && rowStrs.size() > 0) {
                int firstColSize = rowStrs.get(0).size();
                for (List<String> stringList : rowStrs) {
                    for (int x = stringList.size(); x < firstColSize; x++) {
                        stringList.add("");
                    }
                }
            }
            stringListMap.put(s, rowStrs);
        }
        return stringListMap;
    }


    /**
     * 读取excel文件（同时支持2003和2007格式）
     *
     * @param fileName 文件名，绝对路径
     * @return list中的map的key是列的序号
     * @throws Exception io异常等
     */
    public static List<Map<Integer, String>> readExcel(String fileName) throws Exception {
        FileInputStream fis = null;
        Workbook wb = null;
        List<Map<Integer, String>> list = null;
        try {
//            String extension = FilenameUtils.getExtension(fileName);

            fis = new FileInputStream(fileName);
            wb = WorkbookFactory.create(fis);
            list = readWorkbook(wb);

            return list;

        } finally {
            if (null != wb) {
                wb.close();
            }

            if (null != fis) {
                fis.close();
            }
        }

    }

    /**
     * Excel导入时读取excel文件（同时支持2003和2007格式）
     *
     * @param fis 文件输入流
     * @return list中的map的key是列的序号
     * @throws Exception
     */
    public static List<Map<Integer, String>> readExcel(FileInputStream fis) throws Exception {
        Workbook wb = null;
        List<Map<Integer, String>> list = null;
        try {

            wb = WorkbookFactory.create(fis);
            list = readWorkbook(wb);

            return list;

        } finally {
            if (null != wb) {
                wb.close();
            }

            if (null != fis) {
                fis.close();
            }
        }

    }

    /**
     * 读取excel文件（同时支持2003和2007格式）
     *
     * @param fis       文件输入流
     * @param extension 文件名扩展名: xls 或 xlsx 不区分大小写
     * @return list中的map的key是列的序号
     * @throws Exception io异常等
     */
    public static Workbook read(InputStream fis, String extension) throws Exception {

        Workbook wb = null;
        List<Map<Integer, String>> list = null;
        try {

            if ("xls".equalsIgnoreCase(extension)) {
                wb = new HSSFWorkbook(fis);
            } else if ("xlsx".equalsIgnoreCase(extension)) {
                wb = new XSSFWorkbook(fis);
            } else {
//                throw new WrappedException(BizEnums.NotExcel);
            }

            return wb;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != wb) {
                wb.close();
            }
        }

    }

    protected static List<Map<Integer, String>> readWorkbook(Workbook wb) throws Exception {
        List<Map<Integer, String>> list = new LinkedList<Map<Integer, String>>();

        for (int k = 0; k < wb.getNumberOfSheets(); k++) {
            Sheet sheet = wb.getSheetAt(k);
            int rows = sheet.getLastRowNum();

            for (int r = 0; r <= rows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                Map<Integer, String> map = new HashMap<Integer, String>();
                int cells = row.getPhysicalNumberOfCells();

                int idx = 0;
                int physicalCells = 0;
                while (physicalCells < cells) {
                    Cell cell = row.getCell(idx++);
                    if (cell == null) {
                        map.put(idx, "");
                        continue;
                    }
                    String value = getCellValue(cell);
                    map.put(idx, value);
                    physicalCells++;
                }

                list.add(map);
            }

        }

        return list;
    }

    protected static List<List<String>> readWorkbookForList(Workbook wb) throws Exception {
        List<List<String>> list = new LinkedList<List<String>>();

        for (int k = 0; k < wb.getNumberOfSheets(); k++) {
            Sheet sheet = wb.getSheetAt(k);
            rowToList(sheet, list);
        }

        return list;
    }

    protected static Map<String, List<List<String>>> readWorkbookForSheet(Workbook wb) throws Exception {
        Map<String, List<List<String>>> map = new LinkedHashMap<>();

        for (int k = 0; k < wb.getNumberOfSheets(); k++) {
            Sheet sheet = wb.getSheetAt(k);
            String sheetName = sheet.getSheetName();
            List<List<String>> rowStrs = new ArrayList<>();

            map.put(sheetName, rowStrs);
            rowToList(sheet, rowStrs);
        }

        return map;
    }

    private static List<List<String>> rowToList(Sheet sheet, List<List<String>> rowStrs) {

        if (rowStrs == null) {
            rowStrs = new ArrayList<>();
        }

        int rows = sheet.getLastRowNum();

        for (int r = 0; r <= rows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> str = new ArrayList<>();
            int cells = row.getPhysicalNumberOfCells();

            int idx = 0;
            int physicalCells = 0;
            while (physicalCells < cells) {
                Cell cell = row.getCell(idx++);
                if (cell == null) {
                    str.add("");
                    continue;
                }
                String value = getCellValue(cell);
                if (value != null) {
                    //去前后空格
                    value = value.trim();
                }
                str.add(value);
                physicalCells++;
            }
            //如果所有列数据不为空才添加返回
            boolean flag = false;
            if (str.size() > 0) {
                for (String s : str) {
                    if (s != null && !"".equals(s.trim())) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                rowStrs.add(str);
            }
        }

        return rowStrs;
    }


    protected static String getCellValue(Cell cell) {
        String value = null;

        switch (cell.getCellType()) {
            // 公式
            case Cell.CELL_TYPE_FORMULA:
                // 数字
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    value = formatDate(date, "yyyy-MM-dd");
                } else {
                    // 单元格值
                    Object inputValue = null;
                    Long longVal = Math.round(cell.getNumericCellValue());
                    Double doubleVal = cell.getNumericCellValue();
                    //判断是否含有小数位.0
                    if (Double.parseDouble(longVal + ".0") == doubleVal) {
                        inputValue = longVal;
                    } else {
                        inputValue = doubleVal;
                    }
                    value = String.valueOf(inputValue);
                }
//                    if (format == 14 || format == 31 || format == 57 || format == 58 || (format >= 176 && format <= 183)) {
//                        // 日期
//                        Date date = DateUtil.getJavaDate(doubleVal);
//                        value = formatDate(date, "yyyy-MM-dd");
//                    } else if (format == 20 || format == 32 || (format >= 184 && format <= 187)) {
//                        // 时间
//                        Date date = DateUtil.getJavaDate(doubleVal);
//                        value = formatDate(date, "HH:mm");
//                    } else {
//                        value = String.valueOf(doubleVal);
//                    }

                break;
            // 字符串
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();

                break;
            // 空白
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            // Boolean
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            // Error，返回错误码
            case Cell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                value = "";
                break;
        }
        return value;
    }

    @SuppressWarnings("deprecation")
    private static String formatDate(Date d, String sdf) {
        String value = null;
        if (d.getSeconds() == 0 && d.getMinutes() == 0 && d.getHours() == 0) {
            value = com.crud.cloud.evanliu2968.util.DateUtil.timeMillisToDate(d.getTime());
        } else {
            value = DateUtil.DateToString(d, DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
        }

        return value;
    }
}