package com.acmclub.neuq.backend.common.exception;

import com.acmclub.neuq.backend.common.constant.ResultCode;
import lombok.Getter;

/**
 * @author extralIl@1141517977
 * @date 2021/8/15 21:18
 */
@Getter
public class BaseException extends RuntimeException {

    private Integer resultCode = ResultCode.UNKNOWN_ERROR;
    private String errorMsg = "服务器发生未知异常";

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseException(Integer resultCode, String errorMsg) {
        this.resultCode = resultCode;
        this.errorMsg = errorMsg;
    }

}
