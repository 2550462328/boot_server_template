package com.iflytek.yys.base.exception.enums;

import com.iflytek.yys.base.exception.assertion.SystemExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/8/11 14:24
 **/
@Getter
@AllArgsConstructor
public enum  SystemResponseEnum implements SystemExceptionAssert {
    /**
     * 服务器繁忙，请稍后重试
     */
    SERVER_BUSY("5001", "服务器繁忙"),
    /**
     * 服务器异常，无法识别的异常，尽可能对通过判断减少未定义异常抛出
     */
    SERVER_ERROR("5002", "网络异常"),

    /**
     * 会话连接超时，常用于token过期的场景
     */
    SESSION_TIME_OUT("5003","会话连接超时");

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
}
