package com.adire.springdemo1.util;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;

/**
 * @author Adire
 * @create 2024-01-15 14:37
 */
public class MDCTraceUtil {

    public static final String KEY_TRACE_ID = "traceId";

    public static final String TRACE_ID_HEADER = "x-traceId-header";

    public static void addTrace() {
        String traceId = createTrace();
        MDC.put(KEY_TRACE_ID, traceId);
    }

    public static void putTrace(String traceId) {
        MDC.put(KEY_TRACE_ID, traceId);
    }

    public static String getTrace() {
        return MDC.get(KEY_TRACE_ID);
    }

    public static void removeTrace() {
        MDC.remove(KEY_TRACE_ID);
    }


    public static String createTrace() {
        return IdUtil.getSnowflake().nextIdStr();
    }

}
