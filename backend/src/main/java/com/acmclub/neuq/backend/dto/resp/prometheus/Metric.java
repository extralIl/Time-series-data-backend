package com.acmclub.neuq.backend.dto.resp.prometheus;

import lombok.Data;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 20:24
 */
@Data
public class Metric {
    private String device;
    private String fstype;
    private String instance;
    private String job;
    private String mountpoint;
}
