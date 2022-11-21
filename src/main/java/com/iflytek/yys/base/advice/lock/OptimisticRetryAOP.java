package com.iflytek.yys.base.advice.lock;

import cn.hutool.core.util.ArrayUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 乐观锁的重试
 **/
@Aspect
@Component
@Order(9999)
@Slf4j
public class OptimisticRetryAOP {
    
    @Pointcut("@annotation(com.iflytek.yys.base.advice.lock.OptimisticRetry)|| @within(com.iflytek.yys.base.advice.lock.OptimisticRetry)")
    public void optimisticRetryPointcut() {
    
    }
    
    @Around("optimisticRetryPointcut()")
    public Object doConcurrentOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 代理目标对象Class
        Class targetClazz = joinPoint.getTarget().getClass();
        Method method = targetClazz.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        Optional<OptimisticRetry> optimisticRetryOption = getOptimisticRetry(method.getAnnotations());
        if (!optimisticRetryOption.isPresent()) {
            
            log.warn("no OptimisticRetry Annotation to execute!");
            return joinPoint.proceed();
        }
        OptimisticRetryConfig optimisticRetryConfig = obtainOptimisticRetryConfig(optimisticRetryOption.get());
        int numAttempts = 0;
        OptimisticLockingFailureException lockFailureException;
        long startTime = System.currentTimeMillis();
        do {
            numAttempts++;
            try {
                return joinPoint.proceed();
            } catch (OptimisticLockingFailureException ex) {
                
                lockFailureException = ex;
                long executeTime = System.currentTimeMillis() - startTime;
                long maxExecuteTime = optimisticRetryConfig.getMaxExecuteTime();
                if (isLargerThanMaxExecuteTime(executeTime, maxExecuteTime)) {
                    
                    log.warn("throw optimistic locking failure exception!num attempts [{}],start time [{}]," +
                                    "actual execute time [{}] ms, max execute time [{}] ms",
                            numAttempts, startTime, executeTime, maxExecuteTime);
                    throw lockFailureException;
                }
            }
        }
        while (numAttempts <= optimisticRetryConfig.getMaxTryCount());
        
        log.warn("throw optimistic locking failure exception!num attempts {} ", numAttempts);
        throw lockFailureException;
    }
    
    /**
     * 大于限制的重试执行时间
     *
     * @param executeTime
     *
     * @return
     */
    private boolean isLargerThanMaxExecuteTime(long executeTime, long maxExecuteTime) {
        
        if (executeTime <= 0) {
            return false;
        }
        if (maxExecuteTime > executeTime) {
            return true;
        }
        return false;
    }
    
    private OptimisticRetryConfig obtainOptimisticRetryConfig(OptimisticRetry optimisticRetry) {
        
        // 从注解中获取配的值
        OptimisticRetryConfig optimisticRetryConfig = new OptimisticRetryConfig();
        optimisticRetryConfig.setMaxTryCount(optimisticRetry.value());
        optimisticRetryConfig.setMaxExecuteTime(optimisticRetry.maxExecuteTime());
        return optimisticRetryConfig;
    }
    
    private Optional<OptimisticRetry> getOptimisticRetry(Annotation[] annotations) {
        
        if (ArrayUtil.isEmpty(annotations)) {
            return Optional.empty();
        }
        for (Annotation anno : annotations) {
            if (anno.annotationType().getName().equals(OptimisticRetry.class.getName())) {
                return Optional.of((OptimisticRetry) anno);
            }
        }
        return Optional.empty();
    }
    
    /**
     * 重试配置
     */
    @Data
    private static class OptimisticRetryConfig implements Serializable {
        
        private static final long serialVersionUID = -182211651320526367L;
        
        /**
         * 最大重试次数
         */
        private int maxTryCount;
        
        /**
         * 最大执行时间
         */
        private long maxExecuteTime;
    }
}
