package com.cqut.wangyu.crm.service;

import com.cqut.wangyu.crm.dto.ResponseDTO;

/**
 * @ClassName ChartsService
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/12/5 15:19
 * @GitHub https://github.com/ChongqingWangYu
 */
public interface ChartsService {
    /**
     * 获取本年的所有数据，根据月份统计
     * @return
     */
    ResponseDTO getCustomerAllDataMonthlyStatistics();
}
