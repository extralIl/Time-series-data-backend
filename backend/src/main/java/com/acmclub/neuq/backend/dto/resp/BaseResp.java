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
    private Integer resultCode;
    private String errorMsg;
    private Object data;

    public static BaseResp ok(Object data) {
        return BaseResp.builder().resultCode(ResultCode.SUCCESS).data(data).build();
    }

    public static BaseResp fail(String errorMsg) {
        return BaseResp.builder().resultCode(ResultCode.UNKNOWN_ERROR).errorMsg(errorMsg).build();
    }

    public static BaseResp fail(Integer resultCode, String errorMsg) {
        return BaseResp.builder().resultCode(resultCode).errorMsg(errorMsg).build();
    }

    public static BaseResp fail(BaseException e) {
        return BaseResp.builder().resultCode(e.getResultCode()).errorMsg(e.getErrorMsg()).build();
    }
}
