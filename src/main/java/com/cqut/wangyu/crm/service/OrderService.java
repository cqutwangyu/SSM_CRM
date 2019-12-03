package com.cqut.wangyu.crm.service;

import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Order;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {

    ResponseDTO addOrder(Order order);

    ResponseDTO findPageOrder(PageQueryDTO pageQueryDTO);

    /**
     * 删除联系人
     * @param orderID 联系人Id
     * @return
     */
    ResponseDTO deleteOrder(Integer orderID);

    /**
     * 修改联系人信息
     * @param order
     * @return
     */
    ResponseDTO updateOrder(Order order);

    /**
     * 根据联系人姓名查询联系人
     * @param cusName
     * @return
     */
    ResponseDTO findOrderByCustomerName(String cusName);

    /**
     * 根据客户ID查询联系人
     * @param conId
     * @return
     */
    ResponseDTO findOrderById(Integer conId);
    /**
     * 不分页的情况下查询所有联系人
     * @return
     */
    ResponseDTO getAllOrder();

    ResponseDTO findOrderByCusID(Integer cusID);
}
