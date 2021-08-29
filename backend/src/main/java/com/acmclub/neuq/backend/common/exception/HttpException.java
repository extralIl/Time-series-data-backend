package com.acmclub.neuq.backend.common.exception;

/**
 * @author extralIl@1141517977
 * @date 2021/8/19 12:04
 */
public class HttpException extends BaseException {
    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpException(Integer resultCode, String errorMsg) {
        super(resultCode, errorMsg);
    }
}
