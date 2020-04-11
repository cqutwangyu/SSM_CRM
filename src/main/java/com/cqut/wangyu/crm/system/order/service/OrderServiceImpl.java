package com.cqut.wangyu.crm.system.order.service;

import com.cqut.wangyu.crm.system.dto.OrderDTO;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.order.dao.OrderDao;
import com.cqut.wangyu.crm.system.order.entity.Order;
import com.cqut.wangyu.crm.utils.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderServiceImpl
 * @Description 跟进服务实现
 * @Author ChongqingWangYu
 * @DateTime 2019/11/26 9:15
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public String addOrder(Order order) {
        return orderDao.insertOrder(order) == 1 ? Constant.INSERT_SUCCEED : Constant.INSERT_FAILURE;
    }

    @Override
    public Map<String, Object> findPageOrder(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getLimit());
        List<OrderDTO> orderList = orderDao.selectPageOrder(pageQueryDTO);
        PageInfo<OrderDTO> pageInfo = new PageInfo(orderList);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", orderList);
        return map;
    }

    /**
     * 删除跟进
     *
     * @param orderID 跟进Id
     * @return
     */
    @Override
    public String deleteOrder(Integer orderID) {
        return orderDao.deleteOrder(orderID) == 1 ? Constant.DELETE_SUCCEED : Constant.DELETE_FAILURE;
    }

    /**
     * 修改跟进信息
     *
     * @param order
     * @return
     */
    @Override
    public String updateOrder(Order order) {
        return orderDao.updateOrder(order) == 1 ? Constant.UPDATE_SUCCEED : Constant.UPDATE_FAILURE;
    }

    /**
     * 根据跟进姓名查询跟进
     *
     * @param cusName
     * @return
     */
    @Override
    public List<Order> findOrderByCustomerName(String cusName) {
        return orderDao.findOrderByCustomerName(cusName);
    }

    /**
     * 根据客户ID查询跟进
     *
     * @param conId
     * @return
     */
    @Override
    public Order findOrderById(Integer conId) {
        return orderDao.selectOrderByOrdID(conId);
    }

    /**
     * 不分页的情况下查询所有跟进
     *
     * @return
     */
    @Override
    public List<OrderDTO> getAllOrder() {
        return orderDao.selectAllOrder();
    }

    @Override
    public List<OrderDTO> findOrderByCusID(Integer cusID) {
        return orderDao.selectOrderByCusID(cusID);
    }
}
