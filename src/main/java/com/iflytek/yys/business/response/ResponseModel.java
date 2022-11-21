package com.iflytek.yys.business.response;

import cn.hutool.core.util.StrUtil;
import com.iflytek.yys.base.exception.assertion.common.IResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 服务器返回数据模型
 * <p>
 *
 * @author huizhang43
 */
@ApiModel(value = "ResponseModel", description = "返回模型")
@AllArgsConstructor
@Getter
@Setter
public class ResponseModel<T> implements Serializable {
    private static final String SUCCESS_CODE = "1";
    private static final String FAILURE_CODE = "0";

    @ApiModelProperty("返回状态码：0=失败、1=成功")
    private String s;

    @ApiModelProperty("友好提示信息")
    private String m;

    @ApiModelProperty("成功数据")
    private T r;

    @ApiModelProperty("异常状态码")
    private String ec;

    @ApiModelProperty("异常状态信息")
    private String em;

    private ResponseModel() {
    }

    public static ResponseModel success() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setS(SUCCESS_CODE);
        responseModel.setM("success");

        return responseModel;
    }

    public static <T> ResponseModel success(T obj) {
        ResponseModel<T> responseModel = new ResponseModel<>();
        responseModel.setS(SUCCESS_CODE);
        responseModel.setM("success");
        responseModel.setR(obj);

        return responseModel;
    }

    public static ResponseModel failure(IResponseEnum responseEnum) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setS(FAILURE_CODE);
        responseModel.setEc(responseEnum.getCode());
        responseModel.setEm(responseEnum.getMessage());

        return responseModel;
    }

    public static ResponseModel failure(String code, String message) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setS(FAILURE_CODE);
        responseModel.setEc(code);
        responseModel.setEm(message);

        return responseModel;
    }

    public boolean checkSuccess() {
        return StrUtil.equals(SUCCESS_CODE, s);
    }
}