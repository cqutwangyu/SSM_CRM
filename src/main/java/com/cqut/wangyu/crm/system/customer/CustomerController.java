package com.cqut.wangyu.crm.system.customer;

import com.cqut.wangyu.crm.framework.AbstractController;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.customer.entity.Customer;
import com.cqut.wangyu.crm.system.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
public class CustomerController extends AbstractController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    @ResponseBody
    public Object addCustomer(Customer customer) {
        return succeed(customerService.addCustomer(customer));
    }

    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteCustomer(Integer cusId) {
        return succeed(customerService.deleteCustomer(cusId));
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    @ResponseBody
    public Object updateCustomer(Customer customer) {
        return succeed(customerService.updateCustomer(customer));
    }

    @RequestMapping(value = "/findCustomerByName", method = RequestMethod.POST)
    @ResponseBody
    public Object findCustomerByName(String cusName) {
        return succeed(customerService.findCustomerByName(cusName));
    }

    @RequestMapping(value = "/findCustomerById", method = RequestMethod.POST)
    @ResponseBody
    public Object findCustomerById(Integer cusId) {
        return succeed(customerService.findCustomerById(cusId));
    }

    @RequestMapping(value = "/findPageCustomer", method = RequestMethod.POST)
    @ResponseBody
    public Object findPageCustomer(PageQueryDTO pageQueryDTO) {
        return succeed(customerService.findPageCustomer(pageQueryDTO));
    }

    @RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllCustomer() {
        return succeed(customerService.getAllCustomer());
    }

    @RequestMapping(value = "/getAllCustomerAddress", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllCustomerAddress(PageQueryDTO pageQueryDTO) {
        return succeed(customerService.getAllCustomerAddress(pageQueryDTO));
    }

    @RequestMapping(value = "/importCustomerFromExcel", method = RequestMethod.POST)
    @ResponseBody
    public Object importCustomerFromExcel(MultipartFile file) {
        return succeed(customerService.importCustomerFromExcel(file, request));
    }
}
