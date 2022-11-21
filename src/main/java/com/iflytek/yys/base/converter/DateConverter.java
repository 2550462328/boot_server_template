package com.iflytek.yys.base.converter;

import cn.hutool.core.util.StrUtil;
import com.iflytek.yys.base.converter.enums.DateFormatEnum;
import com.iflytek.yys.base.converter.enums.DateFormatRegexEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期转换器
 *
 * Author XiongZhi.Wu 2019/4/24
 */
@Slf4j
@Component
public class DateConverter implements Converter<String, Date> {
    
    @Override
    public Date convert(String source) {
        if (StrUtil.isEmpty(source)) {
            return null;
        } else {
            try {
                return createSimpleDateFormat(source).parse(source);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static SimpleDateFormat createSimpleDateFormat(String source) {
        Pattern datetimestampPattern = Pattern.compile(DateFormatRegexEnum.DATE_FORMAT_REGEX_DATETIMESTAMP.getValue());
        Matcher datetimestampMatcher = datetimestampPattern.matcher(source);
        Pattern datetimePattern = Pattern.compile(DateFormatRegexEnum.DATE_FORMAT_REGEX_DATETIME.getValue());
        Matcher datetimeMatcher = datetimePattern.matcher(source);
        Pattern datePattern = Pattern.compile(DateFormatRegexEnum.DATE_FORMAT_REGEX_DATE.getValue());
        Matcher dateMatcher = datePattern.matcher(source);
        Pattern timestampPattern = Pattern.compile(DateFormatRegexEnum.DATE_FORMAT_REGEX_TIMESTAMP.getValue());
        Matcher timestampMatcher = timestampPattern.matcher(source);
        Pattern timePattern = Pattern.compile(DateFormatRegexEnum.DATE_FORMAT_REGEX_TIME.getValue());
        Matcher timeMatcher = timePattern.matcher(source);
        SimpleDateFormat simpleDateFormat;
        if (datetimestampMatcher.matches()) {
            simpleDateFormat = initSimpleDateFormat(DateFormatEnum.DATE_FORMAT_DATETIMESTAMP.getValue());
        } else if (datetimeMatcher.matches()) {
            simpleDateFormat = initSimpleDateFormat(DateFormatEnum.DATE_FORMAT_DATETIME.getValue());
        } else if (dateMatcher.matches()) {
            simpleDateFormat = initSimpleDateFormat(DateFormatEnum.DATE_FORMAT_DATE.getValue());
        } else if (timestampMatcher.matches()) {
            simpleDateFormat = initSimpleDateFormat(DateFormatEnum.DATE_FORMAT_TIMESTAMP.getValue());
        } else {
            if (!timeMatcher.matches()) {
                throw new RuntimeException(("No Such Date Format Regex Cause Exception"));
            }

            simpleDateFormat = initSimpleDateFormat(DateFormatEnum.DATE_FORMAT_TIME.getValue());
        }

        return simpleDateFormat;
    }

    private static SimpleDateFormat initSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }
}