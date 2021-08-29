package com.acmclub.neuq.backend.dto.resp.prometheus;

import lombok.Data;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 20:20
 */
@Data
public class ResData {
    private String resultType;
    private ResultData[] result;
}
