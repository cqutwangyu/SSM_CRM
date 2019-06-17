package com.cqut.wangyu.crm.service;

import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Customer;

public interface CustomerService {
    ResponseDTO addCustomer(Customer customer);

    ResponseDTO findAllCustomer(Integer page, Integer limit);

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
}
