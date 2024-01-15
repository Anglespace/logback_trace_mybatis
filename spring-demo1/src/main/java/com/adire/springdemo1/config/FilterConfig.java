package com.adire.springdemo1.config;

import com.adire.springdemo1.filter.WebTraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author Adire
 * @create 2024-01-15 15:41
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<WebTraceFilter> registFilter() {
        FilterRegistrationBean<WebTraceFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new WebTraceFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setName("TraceFilter");

        return registrationBean;

    }
}
