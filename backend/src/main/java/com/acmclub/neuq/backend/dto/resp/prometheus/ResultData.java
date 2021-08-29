package com.acmclub.neuq.backend.dto.resp.prometheus;

import lombok.Data;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 20:21
 */
@Data
public class ResultData {

    private Metric metric;
    private String[][] values;

}

