package com.acmclub.neuq.backend.dto.resp.prometheus;

import lombok.Data;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 20:19
 */
@Data
public class PrometheusResp {

    private String status;
    private ResData data;

    public String[][] getDataEntity() {
        return data.getResult()[0].getValues();
    }
}
