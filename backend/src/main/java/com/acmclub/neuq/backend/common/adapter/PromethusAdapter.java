package com.acmclub.neuq.backend.common.adapter;

import com.acmclub.neuq.backend.common.exception.HttpException;
import com.acmclub.neuq.backend.common.util.ExceptionUtils;
import com.acmclub.neuq.backend.common.util.GsonUtils;
import com.acmclub.neuq.backend.common.util.HttpUtil;
import com.acmclub.neuq.backend.dto.resp.prometheus.PrometheusResp;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * @author extralIl@1141517977
 * @date 2021/8/19 11:09
 */
@Slf4j
public class PromethusAdapter {
    private static final String DEFAULT_URI = "http://111.28.140.222:1035/api/v1/query_range";
    private static final String DEFAULT_INSTANT_URI = "http://111.28.140.222:1035/api/v1/query";
    private static final String DEFAULT_QUERY = "rate(node_network_receive_bytes_total[1m])";
    private static final String DEFAULT_START = "1629089124.794";
    private static final String DEFAULT_END = "1629092724.794";
    private static final String DEFAULT_STEP = "15";
    private static final String SPLITTER_CHARACTER = ".";

    public static PrometheusResp queryRangeData(String query, String start, String end, String step) {
        List<NameValuePair> params = Lists.newArrayList(
                new BasicNameValuePair("query", query),
                new BasicNameValuePair("start", toSpecTimeStr(start)),
                new BasicNameValuePair("end", toSpecTimeStr(end)),
                new BasicNameValuePair("step", step)
        );
        try {
            log.info("queryRangeData req:{}", JSON.toJSONString(params));
            String resEntity = HttpUtil.get(DEFAULT_URI, params);
            log.info("queryRangeData resp:{}", resEntity);
            //将返回值变为dto对象
            return GsonUtils.fromJson(resEntity, PrometheusResp.class);
        } catch (Exception e) {
            throw new HttpException("调用get方法时出现异常[RANGE]" + ExceptionUtils.getStackTrace(e));
        }
    }

    public static String queryInstantData(String query, String time) {
        List<NameValuePair> params = Lists.newArrayList(
                new BasicNameValuePair("query", query),
                new BasicNameValuePair("time", time)
        );
        try {
            log.info("queryInstantData req:{}", JSON.toJSONString(params));
            String resEntity = HttpUtil.get(DEFAULT_INSTANT_URI, params);
            log.info("queryInstantData resp:{}", resEntity);
            return resEntity;
        } catch (Exception e) {
            throw new HttpException("调用get方法时出现异常[INSTANT]" + ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * 1630224030140 -> 1630224030.140
     *
     * @param time
     * @return
     */
    private static String toSpecTimeStr(String time) {
        return time.substring(0, time.length() - 3) + SPLITTER_CHARACTER.concat(time.substring(time.length() - 3));
    }

}
