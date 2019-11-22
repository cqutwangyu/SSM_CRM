package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.entity.Customer;
import com.cqut.wangyu.crm.utils.POIUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName CustomerControllerTest
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/6/4 12:36
 * @GitHub https://github.com/ChongqingWangYu
 */
public class CustomerControllerTest extends BaseTest {
    @Autowired
    private CustomerController customerController;

    @Test
    public void addCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName("重庆理工大学");
        customer.setCustomerPhone("13883688888");
        customer.setCustomerAddress("重庆市巴南区");
        customer.setCustomerUrl("www.cqut.edu.cn");
        customer.setCustomerType(1);
        customer.setCustomerStatus(2);
        customerController.addCustomer(customer);
    }

    @Test
    public void deleteCustomerTest() {
        customerController.deleteCustomer(1);
    }

    @Test
    public void findCustomerByNameTest() {
        customerController.findCustomerByName("重庆理工大学");
    }

    @Test
    public void findCustomerByIdTest() {
        customerController.findCustomerById(2);
    }

    @Test
    public void updateTest() {
        Customer customer = new Customer();
        customer.setCustomerID(2);
        customer.setCustomerName("重庆理工大学1");
        customer.setCustomerPhone("13883688889");
        customer.setCustomerAddress("重庆市巴南区1");
        customer.setCustomerUrl("www.cqut.edu.com");
        customer.setCustomerType(2);
        customer.setCustomerStatus(3);
        customerController.updateCustomer(customer);
    }

    @Test
    public void findAllCustomerTest() {
//        customerController.findPageCustomer(1, 10);
    }

    @Test
    public void addCustomerDataFromExcle() {
        try {
            String fileName="G:\\ProjectBackups\\SQLdata\\CRM_contact.xlsx";
            List<Customer> customerLIst = POIUtil.readExcel(fileName);
            for (int i = 0; i < customerLIst.size(); i++) {
                customerController.addCustomer(customerLIst.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
