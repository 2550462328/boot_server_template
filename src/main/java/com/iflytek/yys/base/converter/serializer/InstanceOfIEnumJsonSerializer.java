package com.iflytek.yys.base.converter.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iflytek.yys.business.constant.enums.IEnum;

import java.io.IOException;
import java.io.Serializable;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2021/11/10 14:13
 **/
public class InstanceOfIEnumJsonSerializer<T extends IEnum> extends JsonSerializer<IEnum>
        implements Serializable {

    @Override
    public void serialize(IEnum value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        gen.writeString(value == null ? null : value.getKey().toString());
    }
}
