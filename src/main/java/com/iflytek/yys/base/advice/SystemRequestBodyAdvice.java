package com.iflytek.yys.base.advice;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.nacos.common.utils.IoUtils;
import com.iflytek.yys.base.util.encrypt.aes.AESUtil;
import com.iflytek.yys.base.util.encrypt.aes.AESParam;
import com.iflytek.yys.base.util.encrypt.sms.SM4Param;
import com.iflytek.yys.base.util.encrypt.sms.SM4Util;
import com.iflytek.yys.business.constant.BusinessConstant;
import com.iflytek.yys.base.util.json.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 全局请求拦截
 */
@Slf4j
@RestControllerAdvice
public class SystemRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        // 这里做了一个 传输报文加解密的处理
        List<String> xEncryptList = httpInputMessage.getHeaders().get("X-Encrypt");
        if (CollUtil.isNotEmpty(xEncryptList)) {
            log.debug("X-Encrypt：" + JacksonUtils.objToStr(xEncryptList));
            // AES || SM4 解密
            if (xEncryptList.contains("v1") || xEncryptList.contains("v2")) {
                return new HttpInputMessage() {
                    @Override
                    public InputStream getBody() throws IOException {
                        return new ByteArrayInputStream(
                                xEncryptList.contains("v1")
                                        ?
                                        AESUtil.excuteToBytes(new AESParam(BusinessConstant.SECRET_KEY, IoUtil.readBytes(httpInputMessage.getBody()), false, true, false))
                                        :
                                        SM4Util.excuteToBytes(new SM4Param(BusinessConstant.SECRET_KEY, IoUtil.readBytes(httpInputMessage.getBody()), false, true, false))
                        );
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        return httpInputMessage.getHeaders();
                    }
                };
            } else {
                return httpInputMessage;
            }
        } else {
            return httpInputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }
}