package com.cqut.wangyu.crm.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MethodTimerInterceptors
 * @Description 请求方法执行时间拦截器
 * @Author ChongqingWangYu
 * @DateTime 2019/6/5 15:54
 * @GitHub https://github.com/ChongqingWangYu
 */
public class MethodTimerInterceptors implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(MethodTimerInterceptors.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        logger.info(request.getRequestURI()+"请求到达");
        //返回true才会去下一个拦截器，如果没有下一个拦截器，则去controller
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long start = (long) request.getAttribute("start");
        long end = System.currentTimeMillis();
        long spendTime = end - start;
        if (spendTime>=1000){
            logger.warn("方法耗时严重，请及时处理，耗时："+spendTime);
        }else {
            logger.info("方法耗时正常，耗时："+spendTime+"毫秒");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
