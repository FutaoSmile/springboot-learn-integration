package com.futao.springboot.learn.enhance.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 需要标记为一个Bean才有效@Component
 *
 * @author futao
 * Created on 2019-06-28.
 */
@Component
@Slf4j
@Aspect
public class ServiceAop {

    /**
     * 第一个* 任何返回值
     * 第二个* 该包下的所有类
     * 第三个* 类下的所有方法
     * (..)任何参数
     */
    @Pointcut("execution(* com.futao.springboot.learn.enhance.service.*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint point) {
        log.info("[AOP-before] {},args:{}",
                point.getSignature().getName(),
                JSON.toJSONString(point.getArgs()));
    }


    @After(value = "pointCut()")
    public void after(JoinPoint point) {
        log.info("[AOP-after] {}", point.getSignature().getName());
    }


    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint point, Object result) {
        log.info("[AOP-afterReturning] {},returnValue:{}", point.getSignature().getName(), JSON.toJSONString(result));
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        log.error("[AOP-afterThrowing] {}", point.getSignature().getName(), e);
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("[AOP-around-before] {},args:{}", point.getSignature().getName(), point.getArgs());
        Object result = point.proceed();
        log.info("[AOP-around-after] {},args:{}", point.getSignature().getName(), point.getArgs());
        log.info("[AOP-around-after] {},result:{}", point.getSignature().getName(), result);
        //这个地方需要将返回值返回，否则该被拦截的方法没有返回值，且@AfterReturning中无法拦截到返回值
        return result;
    }
}
