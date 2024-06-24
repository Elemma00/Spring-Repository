package com.emma.curso.springboot.app.aop.springboot_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointcuts {
    
    @Pointcut("execution(String com.emma.curso.springboot.app.aop.springboot_aop.services.*.*(..))")
    public void greetingLogggerPointCut(){}

    @Pointcut("execution(String com.emma.curso.springboot.app.aop.springboot_aop.services.*.*(..))")
    public void greetingFooLoggerPointCut(){}
}
