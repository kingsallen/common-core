package com.moseeker.dto;

import com.moseeker.enums.CommonExceptionEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("返回结果Obj")
public class ResultObject<T> {

    @ApiModelProperty(value = "状态码", example = "0")
    private String code;
    @ApiModelProperty(value = "状态信息", example = "操作成功")
    private String message;
    @ApiModelProperty(value = "返回结果集")
    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResultObject<T> getSuccessResultObject(T object) {
        return new ResultObject<T>(CommonExceptionEnum.success.getKey(), CommonExceptionEnum.success.getValue(), object);
    }

    public static <T> ResultObject<T> getErrorResultObject(String key, String value, T object) {
        return new ResultObject<T>(key, value, object);
    }
}
