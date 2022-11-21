package com.iflytek.yys.business.constant.enums;

/**
 * Description:
 * 枚举值序列化 标识接口
 *
 * @author createdBy huizhang43.
 * @date createdAt 2021/11/10 14:08
 **/
public interface IEnum<T> {
    T getKey();

    default String getDesc(){
        return "";
    }
}
