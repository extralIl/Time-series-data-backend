package com.acmclub.neuq.backend.common.enums;

/**
 * @author extralIl@1141517977
 * @date 2021/8/23 23:39
 */
public enum SeriesExceptionEnum {
    NORMAL(0),
    EXCEPTION(100);


    private Integer value;

    SeriesExceptionEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

