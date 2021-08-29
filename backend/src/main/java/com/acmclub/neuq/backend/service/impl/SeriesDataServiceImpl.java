package com.acmclub.neuq.backend.service.impl;

import com.acmclub.neuq.backend.common.adapter.PromethusAdapter;
import com.acmclub.neuq.backend.common.constant.Constants;
import com.acmclub.neuq.backend.dto.resp.prometheus.PrometheusResp;
import com.acmclub.neuq.backend.service.SeriesDataService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * @author extralIl@1141517977
 * @date 2021/8/24 0:02
 */
@Service
public class SeriesDataServiceImpl implements SeriesDataService {
    @Override
    public String getNowData() {
        String timeStamp = String.valueOf(Instant.now().getEpochSecond());
        String queryString = "rate(node_network_receive_bytes_total[1m])";
        return PromethusAdapter.queryInstantData(queryString, timeStamp);
    }

    @Override
    public String[][] getDataFromNow(String query, String rangeTimeFromNow) {
        PrometheusResp prometheusResp = PromethusAdapter.queryRangeData(query, rangeTimeFromNow, String.valueOf(new Date().getTime()), Constants.step);
        return prometheusResp.getDataEntity();
    }
}
