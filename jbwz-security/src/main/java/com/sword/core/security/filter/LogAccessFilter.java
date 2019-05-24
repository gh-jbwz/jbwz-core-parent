package com.jbwz.core.security.filter;

import com.jbwz.core.common.constant.CommonConstant;
import com.jbwz.core.common.util.RequestUtil;
import com.jbwz.core.security.config.StaticPathConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;

@WebFilter(urlPatterns = "/*", filterName = "accessLogFilter")
@Order(1)
public class LogAccessFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAccessFilter.class);


    /**
     * 记录每个请求的日志，除了静态资源
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (StaticPathConfig.isStaticResource((HttpServletRequest) request)) {
            logRequest((HttpServletRequest) request);
        }
    }

    /**
     * 记录请求日志
     */
    private void logRequest(HttpServletRequest request) {
        String path = request.getServletPath();
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP REQUEST:　METHOD[").append(request.getMethod())
                .append("] Content-Type[")
                .append(request.getContentType()).append("] PATH[").append(path).append("] PARAMETERS[");
        Iterator<String> iterator = servletWebRequest.getParameterNames();
        while (iterator.hasNext()) {
            String s = iterator.next();
            stringBuilder.append(s);
            stringBuilder.append(":");
            if ("password".equals(s)) {
                stringBuilder.append("PROTECTED");
            } else {
                stringBuilder.append(request.getParameter(s));
            }
            if (iterator.hasNext()) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] IP[").append(RequestUtil.getIpAddr(request)).append("] HEADER[");
        Iterator<String> header = servletWebRequest.getHeaderNames();
        while (header.hasNext()) {
            String s = header.next();
            if (CommonConstant.HEAD_FLAG.equals(s) || CommonConstant.HEADER_CLIENT.equals(s)) {
                stringBuilder.append(s);
                stringBuilder.append(":");
                stringBuilder.append(request.getHeader(s));
                if (header.hasNext()) {
                    stringBuilder.append(",");
                }
            }
        }
        stringBuilder.append("]");
        LOGGER.info(stringBuilder.toString());
    }

}
