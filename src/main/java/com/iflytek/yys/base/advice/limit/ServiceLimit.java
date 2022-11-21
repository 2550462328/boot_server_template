package com.iflytek.yys.base.advice.limit;

import java.lang.annotation.*;

/**
 * 用于限流，可以放在方法上
 * @author ZhangHui
 * @date 2020/3/18
 * @param null
 * @return
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Documented
public @interface ServiceLimit {
    String desc() default "";

    String key() default "";

    LimitType limitType() default LimitType.IP;
}
