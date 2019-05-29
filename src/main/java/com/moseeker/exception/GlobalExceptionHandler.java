package com.moseeker.exception;

import com.moseeker.dto.Result;
import com.moseeker.enums.CommonExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 *
 */
@ControllerAdvice("com.moseeker.controller")
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Result bizException(BaseException e) {
        Result result = new Result();
        result.setCode(e.getErrorCode());
        result.setMessage(e.getErrorMessage());
        log.error(e.getMessage(), e);
        return result;
    }

    @ExceptionHandler(SQLException.class)
    public Result handleDBException(SQLException e) {
        Result result = new Result();
        result.setCode(CommonExceptionEnum.error10001.getKey());
        result.setMessage(CommonExceptionEnum.error10001.getValue());
        log.error(e.getMessage(), e);
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        Result result = new Result();
        log.error(e.getMessage(), e);
        result.setCode(CommonExceptionEnum.error10001.getKey());
        result.setMessage(CommonExceptionEnum.error10001.getValue());
        return result;
    }
}
