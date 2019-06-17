package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.dao.CustomerDao;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Customer;
import com.cqut.wangyu.crm.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomerServiceImpl
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/6/17 8:36
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public ResponseDTO addCustomer(Customer customer) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = customerDao.insertCustomer(customer);
        responseDTO.setMessage(rows == 1 ? "添加成功" : "添加失败");
        return responseDTO;
    }

    @Override
    public ResponseDTO findAllCustomer(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        ResponseDTO responseDTO = new ResponseDTO();
        List<Customer> customerList = customerDao.selectAllCustomer();
        PageInfo<Customer> pageInfo = new PageInfo(customerList);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", customerList);
        responseDTO.setData(map);
        return responseDTO;
    }

    /**
     * 删除客户
     *
     * @param cusId 客户Id
     * @return
     */
    @Override
    public ResponseDTO deleteCustomer(Integer cusId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = customerDao.deleteCustomer(cusId);
        responseDTO.setMessage(rows == 1 ? "删除成功" : "删除失败");
        return responseDTO;
    }

    /**
     * 修改客户信息
     *
     * @param customer
     * @return
     */
    @Override
    public ResponseDTO updateCustomer(Customer customer) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = customerDao.updateCustomer(customer);
        responseDTO.setMessage(rows == 1 ? "修改成功" : "修改失败");
        return responseDTO;
    }

    /**
     * 根据客户名查询客户
     *
     * @param cusName
     * @return
     */
    @Override
    public ResponseDTO findCustomerByName(String cusName) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Customer> customerList = customerDao.selectCustomerByName(cusName);
        responseDTO.setData(customerList);
        return responseDTO;
    }

    /**
     * 根据客户ID查询客户
     *
     * @param cusId
     * @return
     */
    @Override
    public ResponseDTO findCustomerById(Integer cusId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Customer customer = customerDao.selectCustomerById(cusId);
        responseDTO.setData(customer);
        return responseDTO;
    }
}
