package com.acmclub.neuq.backend.service;

/**
 * @author extralIl@1141517977
 * @date 2021/8/24 0:01
 */
public interface SeriesDataService {
    String getNowData();

    String[][] getDataFromNow(String usageQuery, String rangeTimeFromNow);
}
