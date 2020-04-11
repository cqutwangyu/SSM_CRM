package com.cqut.wangyu.crm.system.charts;

import com.cqut.wangyu.crm.framework.AbstractController;
import com.cqut.wangyu.crm.system.charts.service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MonthlyStatisticsController
 * @Description 统计图表控制类
 * @Author ChongqingWangYu
 * @DateTime 2019/12/5 15:17
 * @GitHub https://github.com/ChongqingWangYu
 */
@RestController
@RequestMapping("/charts")
class ChartsController extends AbstractController {

    @Autowired
    private ChartsService chartsService;

    @RequestMapping(value = "/getCustomerAllDataMonthlyStatistics", method = RequestMethod.GET)
    @ResponseBody

    public Object getCustomerAllDataMonthlyStatistics() {
        return succeed(chartsService.getCustomerAllDataMonthlyStatistics());
    }
}
