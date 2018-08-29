package com.zzm.aop.xml;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

public class AopTestXML {
    //注解方法在执行TestService里面所有方法前先执行此切面里面的方法

    public void before() {
        System.out.println("method start  ************");
    }

    public void after() {
        System.out.println("method after  ***********");
    }

    public void AfterReturning() {
        System.out.println("method AfterReturning  ************");
    }

    public void AfterThrowing() {
        System.out.println("method AfterThrowing  ************");
    }
}
