package com.iflytek.yys.business.constant.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iflytek.yys.base.converter.serializer.InstanceOfIEnumJsonDeserializer;
import com.iflytek.yys.base.converter.serializer.InstanceOfIEnumJsonSerializer;

/**
 * Description:
 * 就诊合理用药 业务选项枚举
 *
 * @author createdBy huizhang43.
 * @date createdAt 2021/11/10 14:08
 **/
@JsonSerialize(using = InstanceOfIEnumJsonSerializer.class)
@JsonDeserialize(using = InstanceOfIEnumJsonDeserializer.class)
public enum DateTypeEnum implements IEnum<String> {
    
    /**
     * 日维度
     */
    DAY("DAY", 1),
    /**
     * 月维度
     */
    MONTH("MONTH", 2),
    /**
     * 季维度
     */
    SEASON("SEASON", 3),
    /**
     * 年维度
     */
    YEAR("YEAR", 4);
    
    private final String key;
    
    private final Integer value;
    
    DateTypeEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
    
    public static DateTypeEnum getType(Integer value) {
        for (DateTypeEnum dateTypeEnum : DateTypeEnum.values()) {
            if (dateTypeEnum.getValue().equals(value)) {
                return dateTypeEnum;
            }
        }
        return DAY;
    }
    
    @Override
    public String getKey() {
        return this.key;
    }
    
    public Integer getValue() {
        return this.value;
    }
}
