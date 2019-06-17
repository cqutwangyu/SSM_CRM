package com.cqut.wangyu.crm.dao;

import com.cqut.wangyu.crm.entity.Customer;

import java.util.List;

public interface CustomerDao {

    Integer insertCustomer(Customer customer);

    List<Customer> selectAllCustomer();

    Integer deleteCustomer(Integer cusId);

    Integer updateCustomer(Customer customer);

    List<Customer> selectCustomerByName(String cusName);

    Customer selectCustomerById(Integer cusId);
}
