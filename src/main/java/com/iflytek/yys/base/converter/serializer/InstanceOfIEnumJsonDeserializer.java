package com.iflytek.yys.base.converter.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.iflytek.yys.business.constant.enums.IEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2021/11/10 14:09
 **/
@Slf4j
public class InstanceOfIEnumJsonDeserializer<T extends IEnum> extends JsonDeserializer<IEnum>
        implements Serializable {

    @Override
    public IEnum deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        final String text = parser.getText();
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        JsonStreamContext parsingContext = parser.getParsingContext();
        String currentName = parsingContext.getCurrentName();
        Object currentValue = parsingContext.getCurrentValue();

        Field declaredField = null;
        try {
            declaredField = currentValue.getClass().getDeclaredField(currentName);
        } catch (NoSuchFieldException e) {
            log.error("No such field warning: " + currentName, e);
            return null;
        }
        Class<?> targetType = declaredField.getType();
        if (!IEnum.class.isAssignableFrom(targetType)) {
            return null;
        }
        T[] values = (T[]) targetType.getEnumConstants();
        for (T t : values) {
            if (text.equals(t.getKey().toString())) {
                return t;
            }
        }
        return null;
    }
}