package com.cqut.wangyu.crm.utils;

import com.cqut.wangyu.crm.entity.Contact;
import com.cqut.wangyu.crm.entity.Customer;
import com.cqut.wangyu.crm.entity.Follow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
    public static List<Customer> readCustomerExcel(File file) throws Exception {
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
        forechCustomerDataToList(hssfWorkbook, list);
        return list;
    }

    public static List<Customer> readCustomerExcel(String fileName) throws Exception {
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
        forechCustomerDataToList(hssfWorkbook, list);
        return list;
    }

    /**
     * 将数据读入list
     *
     * @param hssfWorkbook
     * @param list
     */
    private static void forechCustomerDataToList(Workbook hssfWorkbook, List<Customer> list) {
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
                    Cell cusType = hssfRow.getCell(5);
                    Cell cusStatus = hssfRow.getCell(6);
                    Cell cusDate = hssfRow.getCell(7);
                    //这里是自己的逻辑
                    customer.setCustomerID(Double.valueOf(cusID.toString()).intValue());
                    customer.setCustomerName(cusName.toString());
                    customer.setCustomerPhone(cusPhone.toString());
                    customer.setCustomerAddress(cusAddr.toString());
                    customer.setCustomerUrl(cusUrl.toString());
                    customer.setCustomerType(Double.valueOf(cusType.toString()).intValue());
                    customer.setCustomerStatus(Double.valueOf(cusStatus.toString()).intValue());
                    customer.setCustomerDate(DateUtil.formatDate(cusDate.getDateCellValue()));
                    list.add(customer);
                }
            }
        }
    }

    /*contact*/
    public static List<Contact> readContactExcel(File file) throws Exception {
        String fileName = file.getName();
        InputStream is = new FileInputStream(file);
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        } else if (fileName.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
        }
        Contact contact = null;
        List<Contact> list = new ArrayList<Contact>();
        // 循环工作表Sheet
        forechContactDataToList(hssfWorkbook, list);
        return list;
    }

    public static List<Contact> readContactExcel(String fileName) throws Exception {
        InputStream is = new FileInputStream(new File(fileName));
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        } else if (fileName.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003

        }
        Contact contact = null;
        List<Contact> list = new ArrayList<Contact>();
        // 循环工作表Sheet
        forechContactDataToList(hssfWorkbook, list);
        return list;
    }

    /**
     * 将数据读入list
     *
     * @param hssfWorkbook
     * @param list
     */
    private static void forechContactDataToList(Workbook hssfWorkbook, List<Contact> list) {
        Contact contact;
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
                    contact = new Contact();
                    Cell contactID = hssfRow.getCell(0);
                    Cell contactPosition = hssfRow.getCell(1);
//                    解决文本自动转为数字
//                    conPhone.setCellType(CellType.STRING);
                    Cell contactName = hssfRow.getCell(2);
                    Cell contactSex = hssfRow.getCell(3);
                    Cell contactPhone = hssfRow.getCell(4);
                    contactPhone.setCellType(CellType.STRING);
                    Cell contactQQ = hssfRow.getCell(5);
                    contactQQ.setCellType(CellType.STRING);
                    Cell contactEmail = hssfRow.getCell(6);
                    Cell customerID = hssfRow.getCell(7);
                    Cell contactDate = hssfRow.getCell(8);
                    //这里是自己的逻辑
                    contact.setContactID(Double.valueOf(contactID.toString()).intValue());
                    contact.setContactPosition(contactPosition.toString());
                    contact.setContactName(contactName.toString());
                    contact.setContactSex(contactSex.toString().equals("男") ? 1 : 2);
                    contact.setContactPhone(contactPhone.toString());
                    contact.setContactQQ(contactQQ.toString());
                    contact.setContactEmail(contactEmail.toString());
                    contact.setCustomerID(Double.valueOf(customerID.toString()).intValue());
                    contact.setContactDate(DateUtil.formatDate(contactDate.getDateCellValue()));
                    list.add(contact);
                }
            }
        }
    }
    /*follow*/
    public static List<Follow> readFollowExcel(File file) throws Exception {
        String fileName = file.getName();
        InputStream is = new FileInputStream(file);
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        } else if (fileName.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
        }
        Follow follow = null;
        List<Follow> list = new ArrayList<Follow>();
        // 循环工作表Sheet
        forechFollowDataToList(hssfWorkbook, list);
        return list;
    }

    public static List<Follow> readFollowExcel(String fileName) throws Exception {
        InputStream is = new FileInputStream(new File(fileName));
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")) {
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        } else if (fileName.endsWith("xls")) {
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003

        }
        Follow follow = null;
        List<Follow> list = new ArrayList<Follow>();
        // 循环工作表Sheet
        forechFollowDataToList(hssfWorkbook, list);
        return list;
    }

    /**
     * 将数据读入list
     *
     * @param hssfWorkbook
     * @param list
     */
    private static void forechFollowDataToList(Workbook hssfWorkbook, List<Follow> list) {
        Follow follow;
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
                    follow = new Follow();
                    Cell followID = hssfRow.getCell(0);
                    Cell followContent = hssfRow.getCell(1);
                    Cell followDate = hssfRow.getCell(2);
                    Cell followType = hssfRow.getCell(3);
                    Cell customerID = hssfRow.getCell(4);
                    Cell contactID = hssfRow.getCell(5);
                    //这里是自己的逻辑
                    follow.setFollowID(Double.valueOf(followID.toString()).intValue());
                    follow.setFollowContent(followContent.toString());
                    follow.setFollowDate(DateUtil.formatDate(followDate.getDateCellValue()));
                    follow.setFollowType(Double.valueOf(followType.toString()).intValue());
                    follow.setCustomerID(Double.valueOf(customerID.toString()).intValue());
                    follow.setContactID(Double.valueOf(contactID.toString()).intValue());
                    list.add(follow);
                }
            }
        }
    }
}
