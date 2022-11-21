package com.iflytek.yys.base.advice;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.iflytek.yys.base.util.SpringUtil;
import com.iflytek.yys.base.util.encrypt.aes.AESParam;
import com.iflytek.yys.base.util.encrypt.aes.AESUtil;
import com.iflytek.yys.base.util.encrypt.sms.SM4Param;
import com.iflytek.yys.base.util.encrypt.sms.SM4Util;
import com.iflytek.yys.business.constant.BusinessConstant;
import com.iflytek.yys.base.util.json.JacksonUtils;
import com.iflytek.yys.business.response.ResponseModel;
import com.iflytek.yys.base.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class SystemResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private MessageUtils messageUtils;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object result, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (result instanceof ResponseModel) {
            ResponseModel responseModel = (ResponseModel) result;
            if(!responseModel.checkSuccess()){
                if(messageUtils == null){
                    messageUtils = SpringUtil.getBean(MessageUtils.class);
                }
                String tipMessage = messageUtils.getMessage(responseModel.getEc());
                if (StrUtil.isEmpty(tipMessage)) {
                    tipMessage = messageUtils.getMessage("5001");
                }
                ((ResponseModel) result).setM(tipMessage);
            }
        }
        List<String> xEncryptList = request.getHeaders().get("X-Encrypt");
        if (CollUtil.isNotEmpty(xEncryptList)) {
            log.debug("X-Encrypt：" + JacksonUtils.objToStr(xEncryptList));
            // AES 加密
            if (xEncryptList.contains("v1")) {
                return AESUtil.excuteToString(new AESParam(BusinessConstant.SECRET_KEY, JSONUtil.toJsonStr(result), true, true, true));
                // SM4 加密
            } else if (xEncryptList.contains("v2")) {
                return SM4Util.excuteToString(new SM4Param(BusinessConstant.SECRET_KEY, JSONUtil.toJsonStr(result), true, true, true));
            } else {
                return result;
            }
        } else {
            return result;
        }
    }
}