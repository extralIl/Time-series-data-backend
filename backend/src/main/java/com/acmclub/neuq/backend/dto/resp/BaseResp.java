package com.acmclub.neuq.backend.dto.resp;

import com.acmclub.neuq.backend.common.constant.ResultCode;
import com.acmclub.neuq.backend.common.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author extralIl@1141517977
 * @date 2021/8/15 21:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResp {
    private Integer error_code;
    private String errorMsg;
    private Object data;

    public static BaseResp ok(Object data) {
        return BaseResp.builder().error_code(ResultCode.SUCCESS).data(data).build();
    }

    public static BaseResp fail(String errorMsg) {
        return BaseResp.builder().error_code(ResultCode.UNKNOWN_ERROR).errorMsg(errorMsg).build();
    }

    public static BaseResp fail(Integer resultCode, String errorMsg) {
        return BaseResp.builder().error_code(resultCode).errorMsg(errorMsg).build();
    }

    public static BaseResp fail(BaseException e) {
        return BaseResp.builder().error_code(e.getResultCode()).errorMsg(e.getErrorMsg()).build();
    }
}
