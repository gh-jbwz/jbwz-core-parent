package com.jbwz.core.security.filter;

import com.jbwz.core.security.authentication.FormLoginAuthenticationToken;
import com.jbwz.core.security.common.LoginType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * log in filter
 */
public class FormLoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormLoginFilter.class);

    private String loginTypeParam = "loginType";
    private static RequestMatcher pathMatch = new AntPathRequestMatcher("/login", "POST");

    public FormLoginFilter() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        LoginType loginType = obtainLoginType(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        FormLoginAuthenticationToken authenticationToken = new FormLoginAuthenticationToken(username, password, loginType);
        this.setDetails(request, authenticationToken);

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString(), failed);
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
        }

        this.getRememberMeServices().loginFail(request, response);

        this.getFailureHandler().onAuthenticationFailure(request, response, failed);
    }

    protected LoginType obtainLoginType(HttpServletRequest request) {
        String s = request.getParameter(getLoginTypeParam());
        if (StringUtils.isBlank(s)) {
            s = "0";
        }
        Integer type = 0;
        try {
            type = Integer.valueOf(s);
        } catch (NumberFormatException e) {
            type = 0;
        }
        return LoginType.typeCode(type);
    }

    protected void obtainParams(HttpServletRequest request, FormLoginAuthenticationToken authenticationToken) {
        setDetails(request, authenticationToken);
    }

    protected boolean requiresAuthentication(HttpServletRequest request,
                                             HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return false;
        }
        return pathMatch.matches(request);
    }

    public String getLoginTypeParam() {
        return loginTypeParam;
    }

    public void setLoginTypeParam(String loginTypeParam) {
        this.loginTypeParam = loginTypeParam;
    }
}
