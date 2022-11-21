package com.iflytek.yys.base.util;

import cn.hutool.core.io.IoUtil;
import com.iflytek.yys.base.util.encrypt.aes.AESParam;
import com.iflytek.yys.base.util.encrypt.aes.AESUtil;
import com.iflytek.yys.base.util.encrypt.sms.SM4Param;
import com.iflytek.yys.base.util.encrypt.sms.SM4Util;
import com.iflytek.yys.business.constant.BusinessConstant;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * http的相关请求
 */
@Slf4j
public class HttpHelper {
    

    /**
     * 获取解密后的 请求报文
     *
     * @param request
     *
     * @return
     */
    public static String getBodyString(HttpServletRequest request) throws IOException {
        String xEncrypt = request.getHeader("X-Encrypt");
        log.debug("X-Encrypt：" + xEncrypt);
        if ("v1".equals(xEncrypt)) { // AES 解密
            return AESUtil.excuteToString(new AESParam(BusinessConstant.SECRET_KEY, IoUtil.readBytes(request.getInputStream()), false, true, false));
        } else if ("v2".equals(xEncrypt)) { // SM4 解密
            return SM4Util.excuteToString(new SM4Param(BusinessConstant.SECRET_KEY, IoUtil.readBytes(request.getInputStream()), false, true, false));
        } else { // 空 || other
            return new String(IoUtil.readBytes(request.getInputStream()));
        }
    }
}