package com.example.demo.common.LogAspect;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Model.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(public * com.example.demo.Controller..*.*(..))")
    public void webLog(){

    }
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attributes.getRequest();
        log.info("URL："+request.getRequestURL());
        log.info("method:"+request.getMethod());
        log.info("params:"+ JSONObject.toJSONString(request.getParameterMap()));
        log.info("Header:"+JSONObject.toJSONString(request.getHeaderNames()));
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

    }
    @AfterReturning(returning = "responseInfo",pointcut = "webLog()")
    public void doAfterReturning(ResponseInfo responseInfo){
        log.info("code:"+responseInfo.getCode());
        log.info("response:"+responseInfo.getData());
    }
}
