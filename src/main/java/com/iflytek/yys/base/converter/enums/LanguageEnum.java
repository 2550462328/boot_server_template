package com.iflytek.yys.base.converter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * I18语言本地化枚举
 *
 * @author huizhang43
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum LanguageEnum {
    /***
     *
     */
    EN("en", "英语"),
    ZH_CN("zh", "中文");
    
    /***
     * 语言国际编码
     */
    private String lang;
    
    /***
     * 语言描述
     */
    private String desc;
}
