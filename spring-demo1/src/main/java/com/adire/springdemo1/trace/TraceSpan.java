package com.adire.springdemo1.trace;

/**
 * @author Adire
 * @create 2024-01-16 13:46
 */
public class TraceSpan {

    private Long traceId;

    private Long spanId;

    private String spanName;

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public Long getSpanId() {
        return spanId;
    }

    public void setSpanId(Long spanId) {
        this.spanId = spanId;
    }

    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }
}
