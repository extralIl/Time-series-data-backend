package com.acmclub.neuq.backend.domain;

import com.acmclub.neuq.backend.domain.common.Component;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 19:24
 */
public class Disk extends Component {

    public static String getWrite() {
        return "sum(rate(node_disk_written_bytes_total[1m]))";
    }

    public static String getRead() {
        return "sum(rate(node_disk_read_bytes_total[1m]))";
    }

    public static String getAvailable() {
        return "100-node_filesystem_free_bytes/node_filesystem_size_bytes*100";
    }

}
