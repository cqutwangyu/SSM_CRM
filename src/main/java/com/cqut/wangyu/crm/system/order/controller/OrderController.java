package com.cqut.wangyu.crm.system.order.controller;

import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
import com.cqut.wangyu.crm.system.dto.ResponseDTO;
import com.cqut.wangyu.crm.system.order.entity.Order;
import com.cqut.wangyu.crm.system.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName OrderController
 * @Description 跟进信息控制类
 * @Author ChongqingWangYu
 * @DateTime 2019/11/29 8:35
 * @GitHub https://github.com/ChongqingWangYu
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addOrder(Order order) {
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deleteOrder(Integer orderID) {
        return orderService.deleteOrder(orderID);
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO updateOrder(Order order) {
        return orderService.updateOrder(order);
    }

    @RequestMapping(value = "/findOrderByCustomerName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findOrderByCustomerName(String cusName) {
        return orderService.findOrderByCustomerName(cusName);
    }

    @RequestMapping(value = "/findOrderById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findOrderById(Integer cusId) {
        return orderService.findOrderById(cusId);
    }


    @RequestMapping(value = "/findOrderByCusID", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findOrderByCusID(Integer cusID) {
        return orderService.findOrderByCusID(cusID);
    }

    @RequestMapping(value = "/findPageOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findPageOrder(PageQueryDTO pageQueryDTO) {
        return orderService.findPageOrder(pageQueryDTO);
    }

    @RequestMapping(value = "/getAllOrder", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO getAllOrder() {
        return orderService.getAllOrder();
    }

}
