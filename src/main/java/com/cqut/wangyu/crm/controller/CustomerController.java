package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Customer;
import com.cqut.wangyu.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CustomerController
 * @Description 客户控制
 * @Author ChongqingWangYu
 * @DateTime 2019/6/17 8:35
 * @GitHub https://github.com/ChongqingWangYu
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addCustomer(Customer customer) {
       return customerService.addCustomer(customer);
    }

    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteCustomer(Integer cusId) {
        return customerService.deleteCustomer(cusId);
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO updateCustomer(Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @RequestMapping(value = "/findCustomerByName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findCustomerByName(String cusName) {
        return customerService.findCustomerByName(cusName);
    }

    @RequestMapping(value = "/findCustomerById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findCustomerById(Integer cusId) {
        return customerService.findCustomerById(cusId);
    }

    @RequestMapping(value = "/findAllCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO findAllCustomer() {
        return customerService.findAllCustomer();
    }
}
