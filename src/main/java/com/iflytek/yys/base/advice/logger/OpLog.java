package com.iflytek.yys.base.advice.logger;

import java.lang.annotation.*;

/**
 * 日志 存储方案
 *
 * @author huizhang43
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpLog {
    
    /**
     * 操作类型
     *
     * @return
     */
    String value() default "";
    
    /**
     * 请求入参 位于 请求路径中的下标
     *
     * @return
     */
    int paramIndex() default 1;
    
    /**
     * 请求入参是否是JSON
     *
     * @return
     */
    boolean isJson() default false;
}
