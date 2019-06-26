package com.cqut.wangyu.crm.aop;


import com.cqut.wangyu.crm.utils.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class LogAspect {

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义切面，拦截所有controller方法
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
//    @Pointcut("execution(* com.cqut.wangyu.crm.service.impl.*.*(..))")
//    @Pointcut("execution(* com.cqut.wangyu.crm.controller.*.*(..))")
    public void controllerLog() {
    }

    /**
     * 前置处理，在调用controller方法前，用于日志记录
     *
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void doBeforeController(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        logger.debug("-----------------------------------------------------------------------------------------------------");
        logger.info("时间 = {}", DateUtil.getNowDateTime());
        logger.info("访问url = {}", request.getRequestURI());
        logger.info("请求方法类型 = {}", request.getMethod());
        logger.info("来源ip地址 = {}", request.getRemoteAddr());
        logger.info("调用方法 = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数 = {}", joinPoint.getArgs());
        logger.debug("-----------------------------------------------------------------------------------------------------");
    }

    @Around(value = "controllerLog()")
    public Object doAfterController(ProceedingJoinPoint joinPoint) {
        try {
            long start = System.currentTimeMillis();
            Object object = joinPoint.proceed();
            long end = System.currentTimeMillis();
            long time = end - start;
            logger.debug("执行时间 = {}", time + "ms");
            return object;
        } catch (Throwable throwable) {
            logger.debug("时间 = {}", DateUtil.getNowDateTime());
        }
        return null;
    }

}
