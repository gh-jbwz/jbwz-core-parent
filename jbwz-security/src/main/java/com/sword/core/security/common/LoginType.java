package com.jbwz.core.security.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登陆类型
 *
 * @author yyh
 */
public enum LoginType {
    USERNAME_PASSWORD(1, "用户名密码登录"), PHONE_NUMBER_CAPTCHA(2,
            "手机验证码登录"), MINI_APP_AUTH(3,
            "微信登录"), NONE(0,
            "没有设置登陆方式");
    private int typeCode;
    private String description;

    LoginType(int typeCode, String description) {
        this.typeCode = typeCode;
        this.description = description;
    }

    public int typeCode() {
        return typeCode;
    }

    public String description() {
        return description;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginType.class);

    public static LoginType typeCode(Integer type) {
        LoginType loginType = LoginType.NONE;
        if (type != null) {
            for (LoginType tmp : LoginType.values()) {
                if (tmp.typeCode() == type) {
                    loginType = tmp;
                    break;
                }
            }
        }
        return loginType;
    }
}
