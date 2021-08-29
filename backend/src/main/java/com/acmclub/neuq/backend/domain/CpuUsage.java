package com.acmclub.neuq.backend.domain;

import com.acmclub.neuq.backend.domain.common.Component;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 19:16
 */
public class CpuUsage extends Component {

    public static String getUsageQuery() {
        return "1-sum(increase(node_cpu_seconds_total{mode=\"idle\"}[5m]))by(instance)/sum(increase(node_cpu_seconds_total[1m]))by(instance)";
    }

}
