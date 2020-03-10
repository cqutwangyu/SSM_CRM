package com.cqut.wangyu.crm.system.customer.dao;

import com.cqut.wangyu.crm.system.customer.entity.Point;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.customer.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {

    Integer insertCustomer(Customer customer);

    List<Customer> selectPageCustomer(PageQueryDTO pageQueryDTO);

    Integer deleteCustomer(Integer cusId);

    Integer updateCustomer(Customer customer);

    List<Customer> selectCustomerByName(String cusName);

    Customer selectCustomerById(Integer cusId);

    Integer insertForeach(@Param("list") List<Customer> customerList);

    List<Customer> selectAllCustomer();

    List<Customer> selectAllCustomerAddress(PageQueryDTO pageQueryDTO);

    Customer selectCustomerByNo(String cusNo);

    List<Integer> selectMonthlyStatistics();
}
