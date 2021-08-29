package com.acmclub.neuq.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author extralIl@1141517977
 * @date 2021/8/23 23:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesDataItem {

    //时间
    private String timea;
    //请求到的数据
    private Integer numa1;
    //异常种类,0为正常
    private Integer abnormal;

}
