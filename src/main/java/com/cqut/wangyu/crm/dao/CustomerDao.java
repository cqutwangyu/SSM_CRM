package com.cqut.wangyu.crm.dao;

import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {

    Integer insertCustomer(Customer customer);

    List<Customer> selectAllCustomer(PageQueryDTO pageQueryDTO);

    Integer deleteCustomer(Integer cusId);

    Integer updateCustomer(Customer customer);

    List<Customer> selectCustomerByName(String cusName);

    Customer selectCustomerById(Integer cusId);

    Integer insertForeach(@Param("list") List<Customer> customerList);
}
