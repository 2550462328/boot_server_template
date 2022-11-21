package com.iflytek.yys.base.util.encrypt.aes;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.iflytek.yys.base.util.encrypt.aes.AESParam;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 16:03
 **/
public abstract class AESUtil {

    public static String excuteToString(AESParam aesParam) {
        try {
            return new String(excuteToBytes(aesParam), aesParam.getCharsetNameToString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] excuteToBytes(AESParam aesParam) {
        try {
            byte[] keyBytes = aesParam.getKeyBytes();
            if (ObjectUtil.isEmpty(keyBytes)) {
                keyBytes = aesParam.getKeyString().getBytes(aesParam.getCharsetNameToBytes());
            }

            byte[] ivBytes = aesParam.getIvBytes();
            if (ObjectUtil.isEmpty(ivBytes) && StrUtil.isNotEmpty(aesParam.getIvString())) {
                ivBytes = aesParam.getIvString().getBytes(aesParam.getCharsetNameToBytes());
            }

            byte[] dataBytes = aesParam.getDataBytes();
            if (ObjectUtil.isEmpty(dataBytes)) {
                dataBytes = aesParam.getDataString().getBytes(aesParam.getCharsetNameToBytes());
            }

            if (aesParam.isBase64() && !aesParam.isEncryptOrDecryptByBase64()) {
                dataBytes = Base64.decodeBase64(dataBytes);
            }

            Cipher cipher = Cipher.getInstance(aesParam.getAlgorithmName() + "/" + aesParam.getMode() + "/" + aesParam.getPadding());
            SecretKey secretKey = new SecretKeySpec(keyBytes, aesParam.getAlgorithmName());
            int edMode = aesParam.isEcryptOrDecryptByAES() ? 1 : 2;
            if (ObjectUtil.isEmpty(ivBytes)) {
                cipher.init(edMode, secretKey);
            } else {
                cipher.init(edMode, secretKey, new IvParameterSpec(ivBytes));
            }

            byte[] result = cipher.doFinal(dataBytes);
            if (aesParam.isBase64() && aesParam.isEncryptOrDecryptByBase64()) {
                result = Base64.encodeBase64(result);
            }

            return result;
        } catch (Exception var8) {
            throw new RuntimeException("AES Ecrypt Or Decrypt To Bytes Cause Exception.",var8);
        }
    }
}

