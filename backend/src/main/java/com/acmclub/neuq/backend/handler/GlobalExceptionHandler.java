package com.acmclub.neuq.backend.handler;

import com.acmclub.neuq.backend.common.constant.ResultCode;
import com.acmclub.neuq.backend.common.exception.BaseException;
import com.acmclub.neuq.backend.common.util.ExceptionUtils;
import com.acmclub.neuq.backend.dto.resp.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author extralIl@1141517977
 * @date 2021/8/15 21:37
 */
// https://www.cnblogs.com/lenve/p/10748453.html 关于advice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public BaseResp unexpectedException(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return BaseResp.fail(ResultCode.UNKNOWN_ERROR, "不应被抛出的异常" + ExceptionUtils.getStackTrace(e));
    }

    @ExceptionHandler(BaseException.class)
    public BaseResp unexpectedException(BaseException e) {
        return BaseResp.fail(e);
    }

}
