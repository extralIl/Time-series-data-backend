package com.acmclub.neuq.backend.domain;

import com.acmclub.neuq.backend.domain.common.Component;

/**
 * @author extralIl@1141517977
 * @date 2021/8/28 19:23
 */
public class Memory extends Component {

    /**
     * 可用内存
     *
     * @return
     */
    public static String getAvailableMemory() {
        return "node_memory_MemFree_bytes+node_memory_Cached_bytes+node_memory_Buffers_bytes";
    }

    /**
     * 内存使用率
     *
     * @return
     */
    public static String getMemoryUsage() {
        return "100-((node_memory_MemFree_bytes+node_memory_Cached_bytes+node_memory_Buffers_bytes)/node_memory_MemTotal_bytes)*100";
    }

}
