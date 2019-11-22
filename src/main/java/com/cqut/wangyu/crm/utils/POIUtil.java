package com.cqut.wangyu.crm.utils;

import com.cqut.wangyu.crm.entity.Customer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName POIUtil
 * @Description excel工具类
 * @Author ChongqingWangYu
 * @DateTime 2019/6/17 15:44
 * @GitHub https://github.com/ChongqingWangYu
 */
public class POIUtil {
    public static List<Customer> readExcel(File file) throws Exception {
        String fileName = file.getName();
        InputStream is = new FileInputStream(file);
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        } else if (fileName.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
        }
        Customer customer = null;
        List<Customer> list = new ArrayList<Customer>();
        // 循环工作表Sheet
        forechDataToList(hssfWorkbook, list);
        return list;
    }

    public static List<Customer> readExcel(String fileName) throws Exception {
        InputStream is = new FileInputStream(new File(fileName));
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        } else if (fileName.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003

        }
        Customer customer = null;
        List<Customer> list = new ArrayList<Customer>();
        // 循环工作表Sheet
        forechDataToList(hssfWorkbook, list);
        return list;
    }

    /**
     * 将数据读入list
     *
     * @param hssfWorkbook
     * @param list
     */
    private static void forechDataToList(Workbook hssfWorkbook, List<Customer> list) {
        Customer customer;
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    customer = new Customer();
                    Cell cusID = hssfRow.getCell(0);
                    Cell cusName = hssfRow.getCell(1);
                    Cell cusPhone = hssfRow.getCell(2);
//                    解决文本自动转为数字
//                    cusPhone.setCellType(CellType.STRING);
                    Cell cusAddr = hssfRow.getCell(3);
                    Cell cusUrl = hssfRow.getCell(4);
                    Cell cusType= hssfRow.getCell(5);
                    Cell cusStatus= hssfRow.getCell(6);
                    //这里是自己的逻辑
                    customer.setCustomerID(Double.valueOf(cusID.toString()).intValue());
                    customer.setCustomerName(cusName.toString());
                    customer.setCustomerPhone(cusPhone.toString());
                    customer.setCustomerAddress(cusAddr.toString());
                    customer.setCustomerUrl(cusUrl.toString());
                    customer.setCustomerType(Double.valueOf(cusType.toString()).intValue());
                    customer.setCustomerStatus(Double.valueOf(cusStatus.toString()).intValue());
                    list.add(customer);
                }
            }
        }
    }
}
