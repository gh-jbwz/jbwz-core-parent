package com.jbwz.core.common.constant;

/**
 * 公用常量
 */
public class CommonConstant {

    /**
     * 启用状态
     */
    public static final String ENABLE_STATUS = "0";
    /**
     * 禁用状态
     */
    public static final String DISABLE_STATUS = "1";
    public static final String ENABLE_VALUE = "启用";
    public static final String DISABLE_VALUE = "禁用";

    /**
     * 登陆如果用户不存在使用userId=0，记录日志时用到
     */
    public static final Long NULL_ACCOUNT_USER_ID = 0L;

    /**
     * 英文点 句号
     */
    public static final String POINT_EN = ".";
    public static final String HEADER_SECURITY_CODE = "security-code-";
    public static final String HEADER_SERVER_NAME = "server-name";

    /**
     * loginType username password
     */
    public static final int USERNAME_PWD = 1;

    /**
     * phone number and captcha
     */
    public static final int PHONE_CAPTCHA = 2;


    /**
     * mini_APP
     */
    public static final int MINI_APP_AUTH = 3;

    /**
     * 来源 1: APP
     */
    public static final String SOURCE_APP = "1";

    /**
     * 来源 2:小程序
     */
    public static final String SOURCE_MINI_PROGRAM = "2";

    /**
     * 微信授权
     */
    public static final String FLAG_WX_AUTH = "3";

    /**
     * 微信其他手机号登陆
     */
    public static final String FLAG_WX_PHONE_CAPTCHA = "4";

    public static final String HEAD_FLAG = "flag";

    public static final String HEADER_CLIENT = "Client-From";

    /**
     * 用户注册类别 1:手机号注册
     */
    public static final String USER_NAME_TYPE_MOBILE = "1";

    /**
     * 用户注册类别 1:邮箱注册
     */
    public static final String USER_NAME_TYPE_EMAIL = "2";

    /**
     * 用户注册类别 1:用户名注册
     */
    public static final String USER_NAME_TYPE_USERNAME = "3";

    /**
     * 手机验证码有效期(分钟)
     */
    public static final int PHONE_CAPTCHA_VALIDITY = 5;

}
