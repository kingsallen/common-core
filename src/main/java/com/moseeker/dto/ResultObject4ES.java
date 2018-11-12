package com.moseeker.dto;

import com.moseeker.enums.CommonExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * 功能描述: 与alphadog CleanJsonResponseWithParse对象对应的VO
 * @auther: JackYang
 * @param:  
 * @return: 
 * @date: 2018/11/12 9:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultObject4ES<T> {

    private static final long serialVersionUID = -149505877407172493L;
    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
    public static <T> ResultObject4ES<T> getSuccessResultObject(T object) {
        return new ResultObject4ES<T>(Integer.valueOf(CommonExceptionEnum.success.getKey()), CommonExceptionEnum.success.getValue(), object);
    }
}
