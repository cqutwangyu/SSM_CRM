package com.cqut.wangyu.crm.controller;

import com.cqut.wangyu.crm.logger.LogAspect;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @ClassName BaseTest
 * @Description
 * @Author ChongqingWangYu
 * @DateTime 2019/6/4 12:23
 * @GitHub https://github.com/ChongqingWangYu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources")
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class BaseTest {
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);
}
