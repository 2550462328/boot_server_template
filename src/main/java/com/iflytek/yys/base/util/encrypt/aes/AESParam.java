package com.iflytek.yys.base.util.encrypt.aes;

import com.iflytek.yys.base.util.encrypt.enums.CharsetNameEnum;
import com.iflytek.yys.base.util.encrypt.enums.ModeEnum;
import com.iflytek.yys.base.util.encrypt.enums.PaddingEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/11/17 16:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AESParam implements Serializable {
    private String algorithmName;
    private String mode;
    private String padding;
    private byte[] keyBytes;
    private byte[] ivBytes;
    private byte[] dataBytes;
    private String keyString;
    private String ivString;
    private String dataString;
    private boolean ecryptOrDecryptByAES;
    private boolean base64;
    private boolean encryptOrDecryptByBase64;
    private String charsetNameToBytes;
    private String charsetNameToString;

    public AESParam(String keyString, byte[] dataBytes, boolean ecryptOrDecryptByAES, boolean base64, boolean encryptOrDecryptByBase64) {
        this.algorithmName = AESEnum.AES.getValue();
        this.mode = ModeEnum.ECB.getValue();
        this.padding = PaddingEnum.PKCS5PADDING.getValue();
        this.charsetNameToBytes = CharsetNameEnum.UTF_8.getValue();
        this.charsetNameToString = CharsetNameEnum.UTF_8.getValue();
        this.keyString = keyString;
        this.dataBytes = dataBytes;
        this.ecryptOrDecryptByAES = ecryptOrDecryptByAES;
        this.base64 = base64;
        this.encryptOrDecryptByBase64 = encryptOrDecryptByBase64;
    }

    public AESParam(String keyString, String dataString, boolean ecryptOrDecryptByAES, boolean base64, boolean encryptOrDecryptByBase64) {
        this.algorithmName = AESEnum.AES.getValue();
        this.mode = ModeEnum.ECB.getValue();
        this.padding = PaddingEnum.PKCS5PADDING.getValue();
        this.charsetNameToBytes = CharsetNameEnum.UTF_8.getValue();
        this.charsetNameToString = CharsetNameEnum.UTF_8.getValue();
        this.keyString = keyString;
        this.dataString = dataString;
        this.ecryptOrDecryptByAES = ecryptOrDecryptByAES;
        this.base64 = base64;
        this.encryptOrDecryptByBase64 = encryptOrDecryptByBase64;
    }
}
