package com.iflytek.yys.base.exception.enums;

import com.iflytek.yys.base.exception.assertion.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回结果
 *
 * @author sprainkle
 * @date 2021/4/26
 */
@Getter
@AllArgsConstructor
public enum BusinessResponseEnum implements BusinessExceptionAssert {

    ;
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
}
