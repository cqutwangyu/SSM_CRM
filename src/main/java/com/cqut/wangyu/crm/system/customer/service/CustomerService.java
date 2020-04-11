package com.cqut.wangyu.crm.system.customer.service;

import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.customer.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface CustomerService {
    Object addCustomer(Customer customer);

    Object findPageCustomer(PageQueryDTO pageQueryDTO);

    /**
     * 删除客户
     * @param cusId 客户Id
     * @return
     */
    Object deleteCustomer(Integer cusId);

    /**
     * 修改客户信息
     * @param customer
     * @return
     */
    Object updateCustomer(Customer customer);

    /**
     * 根据客户名查询客户
     * @param cusName
     * @return
     */
    Object findCustomerByName(String cusName);

    /**
     * 根据客户ID查询客户
     * @param cusId
     * @return
     */
    Object findCustomerById(Integer cusId);

    /**
     * 上传excel文件并导入数据到数据库
     * @param file
     * @param request
     * @return
     */
    Object importCustomerFromExcel(MultipartFile file, HttpServletRequest request);

    /**
     * 不分页的情况下查询所有客户
     * @return
     */
    Object getAllCustomer();

    /**
     * 不分页的情况下查询所有客户的地址
     * @return
     */
    Object getAllCustomerAddress(PageQueryDTO pageQueryDTO);
}
