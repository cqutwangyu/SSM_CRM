package com.cqut.wangyu.crm.utils;

import com.cqut.wangyu.crm.entity.Customer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
//        String[] level={"一级","二级","三级","四级","五级"};
//        String[] credit={"青铜","白银","黄金","铂金","钻石"};
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
//                    Cell cusId = hssfRow.getCell(0);
                    Cell cusNo = hssfRow.getCell(1);
                    Cell cusName = hssfRow.getCell(2);
                    Cell cusRegion = hssfRow.getCell(3);
                    Cell cusAddr = hssfRow.getCell(4);
                    Cell cusUrl = hssfRow.getCell(5);
                    Cell cusLevel = hssfRow.getCell(6);
                    Cell cusCredit = hssfRow.getCell(7);
                    //这里是自己的逻辑
//                    customer.setCusId(Integer.valueOf(cusId.toString()));
                    customer.setCusNo(cusNo.toString());
                    customer.setCusName(cusName.toString());
                    customer.setCusPhone(cusRegion.toString());
                    customer.setCusAddr(cusAddr.toString());
                    customer.setCusUrl(cusUrl.toString());
                    customer.setCusLevel(cusLevel.toString());
                    customer.setCusCredit(cusCredit.toString());
                    list.add(customer);
                }
            }
        }
    }
}
