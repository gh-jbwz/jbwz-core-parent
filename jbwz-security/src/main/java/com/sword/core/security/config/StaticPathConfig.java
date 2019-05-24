package com.jbwz.core.security.config;

import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class StaticPathConfig {
    public static final Set<String> STATIC_RESOURCES_URL = new HashSet<>();
    private static AntPathMatcher matcher = new AntPathMatcher();

    static {
        STATIC_RESOURCES_URL.add("/favicon.ico");
        STATIC_RESOURCES_URL.add("*.jpg");
        STATIC_RESOURCES_URL.add("*.jpeg");
        STATIC_RESOURCES_URL.add("*.png");
        STATIC_RESOURCES_URL.add("*.gif");
        STATIC_RESOURCES_URL.add("*.js");
        STATIC_RESOURCES_URL.add("*.css");
        STATIC_RESOURCES_URL.add("*.psd");
        STATIC_RESOURCES_URL.add("*.ico");
        STATIC_RESOURCES_URL.add("*.tif");
        STATIC_RESOURCES_URL.add("*.bmp");
        STATIC_RESOURCES_URL.add("/webjars/**");
        //字体
        STATIC_RESOURCES_URL.add("*.otf");
        STATIC_RESOURCES_URL.add("*.eot");
        STATIC_RESOURCES_URL.add("*.svg");
        STATIC_RESOURCES_URL.add("*.ttf");
        STATIC_RESOURCES_URL.add("*.woff");
        STATIC_RESOURCES_URL.add("*.woff2");
    }

    public static boolean isStaticResource(HttpServletRequest request) {
        boolean b = false;
        for (String s : StaticPathConfig.STATIC_RESOURCES_URL) {
            if (matcher.match(s, request.getServletPath())) {
                b = true;
            }
        }
        return b;
    }
}
