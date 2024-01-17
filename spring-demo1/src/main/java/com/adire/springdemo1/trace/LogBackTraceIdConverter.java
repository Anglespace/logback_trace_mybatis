package com.adire.springdemo1.trace;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * TraceId转换器，从本地线程获取
 *
 * @author Adire
 * @create 2024-01-16 15:06
 */
public class LogBackTraceIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        TraceSpan traceSpan = TraceContext.getLast();
        if (traceSpan == null) {
            return "";
        } else {
            String hexString = Long.toHexString(traceSpan.getTraceId());
            int left0 = 16 - hexString.length();
            if (left0 == 0) {
                return hexString;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < left0; i++) {
                    stringBuilder.append(0);
                }
                return stringBuilder.append(hexString).toString();
            }
        }
    }
}
