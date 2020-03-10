package com.cqut.wangyu.crm.system.customer.service;

import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.dto.ResponseDTO;
import com.cqut.wangyu.crm.system.customer.entity.Customer;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface CustomerService {
    ResponseDTO addCustomer(Customer customer);

    ResponseDTO findPageCustomer(PageQueryDTO pageQueryDTO);

    /**
     * 删除客户
     * @param cusId 客户Id
     * @return
     */
    ResponseDTO deleteCustomer(Integer cusId);

    /**
     * 修改客户信息
     * @param customer
     * @return
     */
    ResponseDTO updateCustomer(Customer customer);

    /**
     * 根据客户名查询客户
     * @param cusName
     * @return
     */
    ResponseDTO findCustomerByName(String cusName);

    /**
     * 根据客户ID查询客户
     * @param cusId
     * @return
     */
    ResponseDTO findCustomerById(Integer cusId);

    /**
     * 上传excel文件并导入数据到数据库
     * @param file
     * @param request
     * @return
     */
    ResponseDTO importCustomerFromExcel(MultipartFile file, HttpServletRequest request);

    /**
     * 不分页的情况下查询所有客户
     * @return
     */
    ResponseDTO getAllCustomer();

    /**
     * 不分页的情况下查询所有客户的地址
     * @return
     */
    ResponseDTO getAllCustomerAddress(PageQueryDTO pageQueryDTO);
}
