package com.zzm.aop.annotion;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopTest {
    //注解方法在执行TestService里面所有方法前先执行此切面里面的方法
    @Pointcut("execution(public * com.zzm.service.testService.TestService.*(..))")
    public void myMethod(){}

    @Before("myMethod()")
    public void before() {
        System.out.println("method start-----------");
    }
    @After("myMethod()")
    public void after() {
        System.out.println("method after-----------");
    }
    @AfterReturning("execution(public * com.zzm.service.testService.TestService.*(..))")
    public void AfterReturning() {
        System.out.println("method AfterReturning-----------");
    }
    @AfterThrowing("execution(public * com.zzm.service.testService.TestService.*(..))")
    public void AfterThrowing() {
        System.out.println("method AfterThrowing-----------");
    }
}
