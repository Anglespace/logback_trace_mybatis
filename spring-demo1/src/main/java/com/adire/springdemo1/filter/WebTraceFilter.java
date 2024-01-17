package com.adire.springdemo1.filter;

import com.adire.springdemo1.trace.TraceCloser;
import com.adire.springdemo1.trace.TraceContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Adire
 * @create 2024-01-15 14:33
 */
public class WebTraceFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(WebTraceFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try (TraceCloser ignore = TraceContext.put("HTTP:" + request.getRequestURI())) {
            log.info("把traceId加入本地线程");
            filterChain.doFilter(request, servletResponse);
        } catch (Exception e) {
            log.error("WebTraceFilter过滤器执行失败:{}", e);
            e.printStackTrace();
        } finally {
            log.info("清除本地线程中的traceId");
            TraceContext.clear();
        }
    }

}
