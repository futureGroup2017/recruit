package org.wlgzs.recruit.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(DemoFilter.class);

    //不需要过滤
    private static List<Pattern> patterns = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("过滤器启动");
        patterns.add(Pattern.compile("student/selectStudent"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        logger.info(url);
        if(!isInclude(url)){
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("userName") != null) {
                // session存在
                chain.doFilter(httpRequest, httpResponse);
            } else {
                logger.info("验证未通过");
                // session不存在 准备跳转失败
                if (url.equals("index") || url.equals("toLoginOut")) {
                    httpResponse.sendRedirect("./toLogin");
                } else {
                    httpResponse.sendRedirect("../toLogin");
                }
            }
        } else{
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {

    }
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
