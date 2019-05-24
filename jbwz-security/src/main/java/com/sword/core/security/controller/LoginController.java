package com.jbwz.core.security.controller;

import com.jbwz.core.common.base.BaseController;
import com.jbwz.core.common.base.ResponseCode;
import com.jbwz.core.common.base.ResponseJson;
import com.jbwz.core.security.common.FormDataVO;
import com.jbwz.core.security.common.SessionUser;
import com.jbwz.core.security.service.BaseUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.jbwz.core.common.util.RequestUtil.isAppLogin;
import static com.jbwz.core.security.common.SessionUtils.getSessionUser;
import static org.springframework.security.web.WebAttributes.AUTHENTICATION_EXCEPTION;

@RestController
public class LoginController extends BaseController {

    private static final int DEFAULT_APP_SESSION_TIMEOUT = 15 * 24 * 60 * 60;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Value("${login.session-timeout.web:1800}")
    private int webSessionTimeOut;
    @Value("${login.session-timeout.app:0}")
    private int appSessionTimeOut;
    @Autowired
    BaseUserDetailsService loginService;


    /**
     * 跳转登陆界面
     */
    @GetMapping("/login")
    public ResponseJson loginGet(HttpServletRequest request) {
        return fail(ResponseCode.ACCOUNT_NOT_LOGIN);
    }

    /**
     * 登陆成功
     *
     * @param formDataVO
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseJson loginPostSuccess(FormDataVO formDataVO, HttpServletRequest request) {
        saveSessionIdToSessionUser(request);
        setSessionTimeOut(request);
        return success(loginService.loginSuccessHandle(formDataVO));
    }


    /**
     * 登陆失败
     *
     * @param formDataVO
     * @param request
     * @return
     */
    @RequestMapping("/login?error")
    public ResponseJson loginError(FormDataVO formDataVO, HttpServletRequest request) {
        Exception exception = (Exception) request.getAttribute(AUTHENTICATION_EXCEPTION);
        int code = ResponseCode.ACCOUNT_DELETED;
        String msg = "账号被删除";
        if (exception != null) {
            LOGGER.debug("exception name is===================== " + exception.getClass().getName());
            if (exception instanceof DisabledException) {
                code = ResponseCode.ACCOUNT_DISABLED;
                msg = "账号被禁用";
            } else if (exception instanceof UsernameNotFoundException) {
                code = ResponseCode.ACCOUNT_UNKNOW;
                msg = "账号不存在";
            } else if (exception instanceof LockedException) {
                code = ResponseCode.ACCOUNT_LOCKED;
                msg = "账号被锁定";
            } else if (exception instanceof BadCredentialsException) {
                code = ResponseCode.ACCOUNT_WRONG_PASSWORD;
                msg = "用户名密码错误";
            } else if (exception instanceof CredentialsExpiredException) {
                code = ResponseCode.CAPTCHA_EXPIRED;
                msg = "验证码已过期";
            } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
                code = ResponseCode.CAPTCHA_WRONG;
                msg = "验证码错误";
            }
            LOGGER.debug("=================================================" + code);
            loginService.loginErrorHandle(code, formDataVO);
        }
        return fail(code, msg);
    }

    /**
     * 退出登陆
     *
     * @param formDataVO
     * @param request
     * @return
     */
    @RequestMapping("/login?logout")
    public ResponseJson logout(FormDataVO formDataVO, HttpServletRequest request) {
        return success();
    }


    //test
    @RequestMapping("/sein")
    public ResponseJson sessionInvalid() {
        return fail(1111, "session invalid");
    }

    private void saveSessionIdToSessionUser(HttpServletRequest request) {
        SessionUser sessionUser = getSessionUser();
        sessionUser.setSessionId(getRequestSession(request).getId());
    }

    private void setSessionTimeOut(HttpServletRequest request) {
        HttpSession session = getRequestSession(request);
        int timeout = getWebSessionTimeout();
        if (isAppLogin(request)) {
            timeout = getAppSessionTimeout();
        }
        session.setMaxInactiveInterval(timeout);
    }

    private int getWebSessionTimeout() {
        return webSessionTimeOut;
    }


    private int getAppSessionTimeout() {
        if (appSessionTimeOut == 0) {
            return DEFAULT_APP_SESSION_TIMEOUT;
        } else
            return appSessionTimeOut;
    }

    private HttpSession getRequestSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return session;
    }
}
