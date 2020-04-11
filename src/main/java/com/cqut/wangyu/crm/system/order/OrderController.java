package com.cqut.wangyu.crm.system.order;

import com.cqut.wangyu.crm.framework.AbstractController;
import com.cqut.wangyu.crm.system.dto.PageQueryDTO;
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
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object addOrder(Order order) {
        return succeed(orderService.addOrder(order));
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteOrder(Integer orderID) {
        return succeed(orderService.deleteOrder(orderID));
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrder(Order order) {
        return succeed(orderService.updateOrder(order));
    }

    @RequestMapping(value = "/findOrderByCustomerName", method = RequestMethod.POST)
    @ResponseBody
    public Object findOrderByCustomerName(String cusName) {
        return succeed(orderService.findOrderByCustomerName(cusName));
    }

    @RequestMapping(value = "/findOrderById", method = RequestMethod.POST)
    @ResponseBody
    public Object findOrderById(Integer cusId) {
        return succeed(orderService.findOrderById(cusId));
    }


    @RequestMapping(value = "/findOrderByCusID", method = RequestMethod.POST)
    @ResponseBody
    public Object findOrderByCusID(Integer cusID) {
        return succeed(orderService.findOrderByCusID(cusID));
    }

    @RequestMapping(value = "/findPageOrder", method = RequestMethod.POST)
    @ResponseBody
    public Object findPageOrder(PageQueryDTO pageQueryDTO) {
        return succeed(orderService.findPageOrder(pageQueryDTO));
    }

    @RequestMapping(value = "/getAllOrder", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllOrder() {
        return succeed(orderService.getAllOrder());
    }

}
