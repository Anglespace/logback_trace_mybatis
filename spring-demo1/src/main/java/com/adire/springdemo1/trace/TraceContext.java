package com.adire.springdemo1.trace;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Adire
 * @create 2024-01-16 13:47
 */
public class TraceContext {

    private static final ThreadLocal<List<TraceSpan>> context = new ThreadLocal<>();
    private static final Random r = new SecureRandom();

    /**
     * 在这段代码中，put(Long traceId, String spanName) 方法返回了 TraceContext::remove 方法的引用，该引用被隐式地转换为实现了 TraceCloser 接口的对象。
     * 这意味着调用这个 put 方法时，它会创建并初始化一个 TraceSpan 对象，并将其添加到某个存储结构（可能是集合、堆栈或其他跟踪上下文管理结构）中，
     * 然后返回一个实现了 TraceCloser 接口的对象。
     * 当调用返回的 TraceCloser 对象的相应方法（如 close() 或类似清理方法）时，实际执行的操作是调用 TraceContext.remove() 方法来完成跟踪上下文的关闭或清理工作。
     *
     * @param spanName
     * @param traceId
     * @return
     */
    public static TraceCloser put(String spanName, Long traceId) {
        TraceSpan traceSpan = new TraceSpan();
        traceSpan.setSpanName(spanName);
        traceSpan.setSpanId(r.nextLong());
        traceSpan.setTraceId(traceId);
        put(traceSpan);
        return TraceContext::remove;
    }

    public static void put(TraceSpan traceSpan) {
        List<TraceSpan> traceSpans = context.get();
        if (traceSpans == null) {
            context.set(traceSpans = new ArrayList<>());
        }
        traceSpans.add(traceSpan);
    }

    public static TraceCloser put(String spanName) {
        TraceSpan traceSpan = getLast();//为什么要获取最后一个TraceSpan？
        return put(spanName, traceSpan == null ? r.nextLong() : traceSpan.getTraceId());
    }


    /**
     * 清空本地线程中所有的TraceSpan
     */
    public static void clear() {
        context.remove();
    }

    /**
     * 移除单个TraceSpan
     */
    public static void remove() {
        List<TraceSpan> traceSpans = context.get();
        if (traceSpans == null || traceSpans.isEmpty()) {
            context.remove();
            return;
        }
        traceSpans.remove(traceSpans.size() - 1);
        if (traceSpans.isEmpty()) {
            context.remove();
        }
    }

    public static TraceSpan getLast() {
        List<TraceSpan> traceSpans = context.get();
        if (traceSpans == null) {
            return null;
        }
        return traceSpans.get(traceSpans.size() - 1);
    }
}
