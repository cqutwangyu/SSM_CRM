package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.dao.OrderDao;
import com.cqut.wangyu.crm.dto.OrderDTO;
import com.cqut.wangyu.crm.dto.PageQueryDTO;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.entity.Order;
import com.cqut.wangyu.crm.service.OrderService;
import com.cqut.wangyu.crm.utils.MyFileUtil;
import com.cqut.wangyu.crm.utils.POIUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    public ResponseDTO addOrder(Order order) {
        ResponseDTO responseDTO = new ResponseDTO();
        Order cName = orderDao.selectOrderByOrdID(order.getOrderID());
        if (null==cName) {
            Integer rows = orderDao.insertOrder(order);
            responseDTO.setMessage(rows == 1 ? "添加成功" : "添加失败");
            responseDTO.setData("succeed");
        } else {
            responseDTO.setMessage("ID已存在");
            responseDTO.setData("error");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO findPageOrder(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getLimit());
        ResponseDTO responseDTO = new ResponseDTO();
        List<OrderDTO> orderList = orderDao.selectPageOrder(pageQueryDTO);
        PageInfo<OrderDTO> pageInfo = new PageInfo(orderList);

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", orderList);
        responseDTO.setData(map);
        return responseDTO;
    }

    /**
     * 删除跟进
     *
     * @param orderID 跟进Id
     * @return
     */
    @Override
    public ResponseDTO deleteOrder(Integer orderID) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = orderDao.deleteOrder(orderID);
        responseDTO.setMessage(rows == 1 ? "删除成功" : "删除失败");
        return responseDTO;
    }

    /**
     * 修改跟进信息
     *
     * @param order
     * @return
     */
    @Override
    public ResponseDTO updateOrder(Order order) {
        ResponseDTO responseDTO = new ResponseDTO();
        Integer rows = orderDao.updateOrder(order);
        responseDTO.setMessage(rows == 1 ? "修改成功" : "修改失败");
        return responseDTO;
    }

    /**
     * 根据跟进姓名查询跟进
     *
     * @param cusName
     * @return
     */
    @Override
    public ResponseDTO findOrderByCustomerName(String cusName) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Order> customerList = orderDao.findOrderByCustomerName(cusName);
        responseDTO.setData(customerList);
        return responseDTO;
    }

    /**
     * 根据客户ID查询跟进
     *
     * @param conId
     * @return
     */
    @Override
    public ResponseDTO findOrderById(Integer conId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Order customer = orderDao.selectOrderByOrdID(conId);
        responseDTO.setData(customer);
        return responseDTO;
    }

    /**
     * 不分页的情况下查询所有跟进
     *
     * @return
     */
    @Override
    public ResponseDTO getAllOrder() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<OrderDTO> orderList = orderDao.selectAllOrder();
        responseDTO.setData(orderList);
        responseDTO.setMessage("共" + orderList.size() + "条数据");
        return responseDTO;
    }

    @Override
    public ResponseDTO findOrderByCusID(Integer cusID) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<OrderDTO> customer = orderDao.selectOrderByCusID(cusID);
        responseDTO.setData(customer);
        return responseDTO;
    }
}
