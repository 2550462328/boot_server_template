package com.iflytek.yys.base.advice.lock;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/7/5 9:38
 **/

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 乐观锁的重试
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OptimisticRetry {
    
    /**
     * 最大的重试次数
     */
    int value() default 200;
    
    /**
     * 最大的执行时间
     */
    int maxExecuteTime() default 2 * 60 * 60 * 1000;
}
