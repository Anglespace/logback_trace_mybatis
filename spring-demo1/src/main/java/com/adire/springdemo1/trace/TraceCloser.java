package com.adire.springdemo1.trace;

/**
 * @author Adire
 * @create 2024-01-16 13:52
 */
public interface TraceCloser extends AutoCloseable {
    @Override
    void close() throws Exception;
}
