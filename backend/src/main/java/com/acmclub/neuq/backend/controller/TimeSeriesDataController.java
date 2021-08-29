package com.acmclub.neuq.backend.controller;

import com.acmclub.neuq.backend.common.enums.SeriesExceptionEnum;
import com.acmclub.neuq.backend.domain.CpuUsage;
import com.acmclub.neuq.backend.domain.Disk;
import com.acmclub.neuq.backend.domain.Memory;
import com.acmclub.neuq.backend.domain.NetworkFlow;
import com.acmclub.neuq.backend.domain.common.Minute;
import com.acmclub.neuq.backend.dto.SeriesDataItem;
import com.acmclub.neuq.backend.dto.resp.BaseResp;
import com.acmclub.neuq.backend.service.SeriesDataService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author extralIl@1141517977
 * @date 2021/8/23 23:29
 */
@RequestMapping("/series")
@RestController
public class TimeSeriesDataController {

    @Autowired
    private SeriesDataService seriesDataService;

    @ResponseBody
    @GetMapping("/data")
    public String getSeriesData() {
        seriesDataService.getNowData();
        return JSON.toJSONString(BaseResp.ok(new SeriesDataItem("123", 12, SeriesExceptionEnum.NORMAL.getValue())));
    }

    /**
     * CPU每分钟使用率
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/cpu/usage/perMin")
    public String getCpuPerMinUsage() {
        String[][] data = seriesDataService.getDataFromNow(CpuUsage.getUsageQuery(), Minute.getRangeTimeFromNow(1));
        return JSON.toJSONString(BaseResp.ok(data));
    }

    /**
     * 可用内存 每分钟
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/memory/available")
    public String getAvailableMemory() {
        String[][] data = seriesDataService.getDataFromNow(Memory.getAvailableMemory(), Minute.getRangeTimeFromNow(1));
        return JSON.toJSONString(BaseResp.ok(data));
    }

    /**
     * 内存使用率 每分钟
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/memory/usage")
    public String getMemoryUsage() {
        String[][] data = seriesDataService.getDataFromNow(Memory.getMemoryUsage(), Minute.getRangeTimeFromNow(1));
        return JSON.toJSONString(BaseResp.ok(data));
    }

    /**
     * 磁盘写 byte/分钟
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/disk/write")
    public String getDiskWrite() {
        String[][] data = seriesDataService.getDataFromNow(Disk.getWrite(), Minute.getRangeTimeFromNow(1));
        return JSON.toJSONString(BaseResp.ok(data));
    }

    /**
     * 磁盘读 byte/分钟
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/disk/read")
    public String getDiskRead() {
        String[][] data = seriesDataService.getDataFromNow(Disk.getRead(), Minute.getRangeTimeFromNow(1));
        return JSON.toJSONString(BaseResp.ok(data));
    }

    /**
     * 剩余磁盘空间 byte
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/disk/available")
    public String getDiskAvailable() {
        String[][] data = seriesDataService.getDataFromNow(Disk.getAvailable(), Minute.getRangeTimeFromNow(1));
        return JSON.toJSONString(BaseResp.ok(data));
    }

    /**
     * 入口流量 byte/5分钟
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/network/input")
    public String getInputFlow() {
        String[][] data = seriesDataService.getDataFromNow(NetworkFlow.getInput(), Minute.getRangeTimeFromNow(5));
        return JSON.toJSONString(BaseResp.ok(data));
    }

    /**
     * 出口流量 byte/5分钟
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/network/output")
    public String getOutputFlow() {
        String[][] data = seriesDataService.getDataFromNow(NetworkFlow.getOutput(), Minute.getRangeTimeFromNow(5));
        return JSON.toJSONString(BaseResp.ok(data));
    }


}
