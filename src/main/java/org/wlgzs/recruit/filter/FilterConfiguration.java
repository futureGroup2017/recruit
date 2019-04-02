package org.wlgzs.recruit.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<DemoFilter> filterDemoRegistration() {
        FilterRegistrationBean<DemoFilter> registration = new FilterRegistrationBean<>();
        //注入过滤器
        registration.setFilter(new DemoFilter());
        //拦截规则
        registration.addUrlPatterns("/interview/*");
        registration.addUrlPatterns("/question/*");
        registration.addUrlPatterns("/scoreItem/*");
        registration.addUrlPatterns("/student/*");
        registration.addUrlPatterns("/index");
        registration.addUrlPatterns("/toLoginOut");
        //过滤器名称
        registration.setName("DemoFilter");
        //是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(true);
        //过滤器顺序
        registration.setOrder(Integer.MAX_VALUE - 1);
        return registration;
    }

}