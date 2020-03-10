package com.cqut.wangyu.crm.system.order.dao;

import com.cqut.wangyu.crm.system.dto.OrderDTO;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.order.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    Integer insertOrder(Order order);

    List<OrderDTO> selectPageOrder(PageQueryDTO pageQueryDTO);

    Integer deleteOrder(Integer ordID);

    Integer updateOrder(Order order);

    List<Order> findOrderByCustomerName(String cusName);

    Order selectOrderByOrdID(Integer ordID);

    Integer insertForeach(@Param("list") List<Order> orderList);

    List<OrderDTO> selectAllOrder();

    List<OrderDTO> selectOrderByCusID(Integer cusID);

    List<Integer> selectMonthlyStatistics();

    List<Integer> selectMonthlyStatisticsAmount();
}
