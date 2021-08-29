package com.acmclub.neuq.backend.domain.common;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 19:17
 */
public class Minute extends TimeRange {

    public static String getRangeTimeFromNow(Integer time) {
        return String.valueOf(DateUtils.addMinutes(new Date(), -1 * time).getTime());
    }

}
