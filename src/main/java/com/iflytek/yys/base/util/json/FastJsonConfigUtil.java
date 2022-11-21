package com.iflytek.yys.base.util.json;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.iflytek.yys.base.converter.enums.DateFormatEnum;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 17:23
 **/
public class FastJsonConfigUtil {
    private static volatile FastJsonConfig fastJsonConfig;

    public static FastJsonConfig getFastJsonConfig() {
        if (ObjectUtil.isNull(fastJsonConfig)) {
            Class var0 = FastJsonConfigUtil.class;
            synchronized(FastJsonConfigUtil.class) {
                if (ObjectUtil.isNull(fastJsonConfig)) {
                    fastJsonConfig = new FastJsonConfig();
                    fastJsonConfig.setDateFormat(DateFormatEnum.DATE_FORMAT_DATETIMESTAMP.getValue());
                    fastJsonConfig.setSerializerFeatures(new SerializerFeature[]{SerializerFeature.QuoteFieldNames, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.SkipTransientField, SerializerFeature.SortField, SerializerFeature.WriteDateUseDateFormat});
                    fastJsonConfig.setFeatures(new Feature[]{Feature.AllowUnQuotedFieldNames, Feature.AllowSingleQuotes, Feature.IgnoreNotMatch});
                }
            }
        }

        return fastJsonConfig;
    }
}