package com.adire.springdemo1.filter;

import com.adire.springdemo1.util.MDCTraceUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Adire
 * @create 2024-01-15 14:33
 */
public class WebTraceFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(WebTraceFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            log.info("插入traceId");
            String traceId = request.getHeader(MDCTraceUtil.TRACE_ID_HEADER);
            if(StringUtils.isEmpty(traceId)){
                MDCTraceUtil.addTrace();
            }else {
                MDCTraceUtil.putTrace(traceId);
            }
            filterChain.doFilter(request,servletResponse);
        } finally {
            log.info("移除traceId");
            MDCTraceUtil.removeTrace();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
