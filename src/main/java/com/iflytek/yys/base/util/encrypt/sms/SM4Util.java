package com.iflytek.yys.base.util.encrypt.sms;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Security;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 16:35
 **/
public abstract class SM4Util {
    private static final Logger log = LoggerFactory.getLogger(SM4Util.class);

    public static String excuteToString(SM4Param sm4Param) {
        try {
            return new String(excuteToBytes(sm4Param), sm4Param.getCharsetNameToString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] excuteToBytes(SM4Param sm4Param) {
        try {
            byte[] keyBytes = sm4Param.getKeyBytes();
            if (ObjectUtil.isEmpty(keyBytes)) {
                keyBytes = sm4Param.getKeyString().getBytes(sm4Param.getCharsetNameToBytes());
            }

            byte[] ivBytes = sm4Param.getIvBytes();
            if (ObjectUtil.isEmpty(ivBytes) && StrUtil.isNotEmpty(sm4Param.getIvString())) {
                ivBytes = sm4Param.getIvString().getBytes(sm4Param.getCharsetNameToBytes());
            }

            byte[] dataBytes = sm4Param.getDataBytes();
            if (ObjectUtil.isEmpty(dataBytes)) {
                dataBytes = sm4Param.getDataString().getBytes(sm4Param.getCharsetNameToBytes());
            }

            if (sm4Param.isBase64() && !sm4Param.isEncryptOrDecryptByBase64()) {
                dataBytes = Base64.decodeBase64(dataBytes);
            }

            Cipher cipher = Cipher.getInstance(sm4Param.getAlgorithmName() + "/" + sm4Param.getMode() + "/" + sm4Param.getPadding(), "BC");
            SecretKey secretKey = new SecretKeySpec(keyBytes, sm4Param.getAlgorithmName());
            int edMode = sm4Param.isEcryptOrDecryptBySM4() ? 1 : 2;
            if (ObjectUtil.isEmpty(ivBytes)) {
                cipher.init(edMode, secretKey);
            } else {
                cipher.init(edMode, secretKey, new IvParameterSpec(ivBytes));
            }

            byte[] result = cipher.doFinal(dataBytes);
            if (sm4Param.isBase64() && sm4Param.isEncryptOrDecryptByBase64()) {
                result = Base64.encodeBase64(result);
            }

            return result;
        } catch (Exception var8) {
            throw new RuntimeException("SM4 Ecrypt Or Decrypt To Bytes Cause Exception.",var8);
        }
    }

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
        } catch (Exception var1) {
            var1.printStackTrace();
            log.error("Add Provider: BC");
        }

    }
}