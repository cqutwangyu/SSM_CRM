package com.cqut.wangyu.crm.system.order.service;

import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.order.entity.Order;

public interface OrderService {

    Object addOrder(Order order);

    Object findPageOrder(PageQueryDTO pageQueryDTO);

    /**
     * 删除联系人
     * @param orderID 联系人Id
     * @return
     */
    Object deleteOrder(Integer orderID);

    /**
     * 修改联系人信息
     * @param order
     * @return
     */
    Object updateOrder(Order order);

    /**
     * 根据联系人姓名查询联系人
     * @param cusName
     * @return
     */
    Object findOrderByCustomerName(String cusName);

    /**
     * 根据客户ID查询联系人
     * @param conId
     * @return
     */
    Object findOrderById(Integer conId);
    /**
     * 不分页的情况下查询所有联系人
     * @return
     */
    Object getAllOrder();

    Object findOrderByCusID(Integer cusID);
}
