package com.jsamuel.study.aspect;

import com.jsamuel.study.annotation.MetricTime;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // 抽取公共的切入点表达式
    // 切入点表达式，指定在哪个地方进行切入
    @Pointcut("execution(public * com.jsamuel.study.WriterService.*(..))")
    public void pointCut() {
    }

    // @Before，前置通知，在目标方法之前切入，在方法执行之前执行的通知
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取方法的参数
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("[Before] start log, method: " + methodName + ", args: " + args);
    }

    // @After，后置通知，在目标方法之后切入，在方法执行之后执行的通知，无论是否抛出异常
    // 后置通知中不能访问目标方法执行后返回的结果
    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("[After] end log ");
    }

    // @Around，环绕通知，在目标方法的前后执行，可以控制是否执行连接点方法
    // 环绕通知，连接点的参数类型必须是ProceedingJoinPoint类型，它是JointPoint的子接口，允许控制什么时候执行，是否执行连接点
    // 环绕通知必须有返回值，返回值即为目标方法的返回值
    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[Around] start log ");

        // 执行目标对象方法
        Object retValue = null;
        try {
            retValue = pjp.proceed();
        } catch (Exception e) {
            System.out.println("[Around] log exception: " + e);
            throw new RuntimeException(e);
        }

        System.out.println("[Around] end log ");

        return retValue;
    }

    // @AfterReturning，返回通知，在目标方法正常执行返回结果之后执行
    // 返回通知可以获取目标方法的返回值
    // returning属性，用于访问连接点的返回值，该属性的值即为用来传入返回值的参数名称
    @AfterReturning(value = "pointCut()", returning = "obj")
    public void logReturn(JoinPoint joinPoint, Object obj) {
        System.out.println("[After Returning] end log, return value: " + obj);
    }

    // @AfterThrowing，异常通知，在目标方法抛出异常之后执行
    // 异常通知可以访问到异常对象
    // throwing属性，用于访问连接点抛出的异常，也可以指定某种特定的异常类型
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("[After Throwing] end log, exception: " + exception);
    }
}
