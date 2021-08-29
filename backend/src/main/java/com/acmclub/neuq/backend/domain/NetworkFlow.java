package com.acmclub.neuq.backend.domain;

import com.acmclub.neuq.backend.domain.common.Component;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 19:25
 */
public class NetworkFlow extends Component {

    public static String getInput() {
        return "sum(rate(node_network_receive_bytes_total{device!=\"lo\"}[5m]))";
    }

    public static String getOutput() {
        return "sum(rate(node_network_transmit_bytes_total{device!=\"lo\"}[5m]))";
    }

}
