package com.cqut.wangyu.crm.service.impl;

import com.cqut.wangyu.crm.dao.ContactDao;
import com.cqut.wangyu.crm.dao.CustomerDao;
import com.cqut.wangyu.crm.dao.FollowDao;
import com.cqut.wangyu.crm.dao.OrderDao;
import com.cqut.wangyu.crm.dto.ResponseDTO;
import com.cqut.wangyu.crm.service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChartsServiceImpl
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/12/5 15:22
 * @GitHub https://github.com/ChongqingWangYu
 */
@Service
public class ChartsServiceImpl implements ChartsService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ContactDao contactDao;
    @Autowired
    private FollowDao followDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 获取本年的所有数据，根据月份统计
     *
     * @return
     */
    @Override
    public ResponseDTO getCustomerAllDataMonthlyStatistics() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<List<Integer>> list = new ArrayList();
        List cus = customerDao.selectMonthlyStatistics();
        List con = contactDao.selectMonthlyStatistics();
        List fol = followDao.selectMonthlyStatistics();
        List ord = orderDao.selectMonthlyStatistics();
        list.add(cus);
        list.add(con);
        list.add(fol);
        list.add(ord);
        responseDTO.setData(list);
        responseDTO.setMessage("共" + list.size() + "条数据");
        return responseDTO;
    }
}
