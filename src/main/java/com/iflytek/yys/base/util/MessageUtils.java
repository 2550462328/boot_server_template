package com.iflytek.yys.base.util;

import com.iflytek.yys.base.converter.enums.LanguageEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;

/**
 * IL8N 工具类
 *
 * @author huizhang43
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageUtils {
    
    private final MessageSource messageSource;
    
    /***
     * 错误码信息获取
     * @param language
     * @param code
     * @return
     */
    public String getMessage(String language, String code) {
        //默认没有就是请求地区的语言
        Locale locale = null;
        if (language == null) {
            locale = Locale.ENGLISH;
        } else if (Objects.equals(LanguageEnum.EN.getLang(), language)) {
            locale = Locale.ENGLISH;
        } else if (Objects.equals(LanguageEnum.ZH_CN.getLang(), language)) {
            locale = Locale.CHINA;
        }
        return messageSource.getMessage(code, null, locale);
    }
    public String getMessage(String code) {
        return getMessage(LanguageEnum.ZH_CN.getLang(), code);
    }

}
