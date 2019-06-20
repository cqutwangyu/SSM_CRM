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
        customer.setCusNo("900001");
        customer.setCusName("重庆理工大学");
        customer.setCusPhone("西南");
        customer.setCusAddr("重庆市巴南区");
        customer.setCusUrl("www.cqut.edu.cn");
        customer.setCusLevel("战略合作伙伴");
        customer.setCusCredit("普通客户");
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
        customer.setCusId(2);
        customer.setCusNo("900002");
        customer.setCusName("重庆理工大学");
        customer.setCusPhone("西南");
        customer.setCusAddr("重庆市巴南区");
        customer.setCusUrl("www.cqut.edu.cn");
        customer.setCusLevel("战略合作伙伴");
        customer.setCusCredit("普通客户1");
        customerController.updateCustomer(customer);
    }

    @Test
    public void findAllCustomerTest() {
//        customerController.findPageCustomer(1, 10);
    }

    @Test
    public void addCustomerDataFromExcle() {
        try {
            List<Customer> customerLIst = POIUtil.readExcel("C:\\Users\\Administrator\\Desktop\\customer.xlsx");
            for (int i = 0; i < customerLIst.size(); i++) {
                customerController.addCustomer(customerLIst.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
