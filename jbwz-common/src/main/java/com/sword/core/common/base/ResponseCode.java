package com.jbwz.core.common.base;

/**
 * 定义返回状态码
 *
 * @author yyh
 */
public class ResponseCode {

  /**
   * 成功完成
   */
  public final static int SUCCESS = 0;
  /**
   * 系统内部错误
   */
  public final static int ERROR = 1;
  /**
   * 用户名密码错误
   */
  public final static int ACCOUNT_WRONG_PASSWORD = 201;
  /**
   * 账号不存在
   */
  public final static int ACCOUNT_UNKNOW = 202;

  /**
   * 未登录
   */
  public final static int ACCOUNT_NOT_LOGIN = 203;
  /**
   * 账号被禁用
   */
  public final static int ACCOUNT_DISABLED = 204;
  /**
   * 账号被锁定
   */
  public final static int ACCOUNT_LOCKED = 205;
  /**
   * 账号被删除
   */
  public final static int ACCOUNT_DELETED = 205;
  /**
   * 名称已存在
   */
  public final static int ACCOUNT_EXIST = 206;

  /**
   * app 版本已经是最新
   */
  public static final int APP_VERSION_UPDATED = 2020;

  // ============================= 其它异常错误code以300开头
  // ======================================
  /**
   * 请求参数错误
   */
  public final static int REQUEST_PARAM_WRONG = 3001;

  /**
   * 文件内容格式错误
   */
  public final static int FILE_PARSE_ERROR = 3002;
  /**
   * 文件格式不支持
   */
  public static final int FILE_WRONG_SUFFIX = 3003;
  /**
   * 上传失败，请重试
   */
  public static final int FILE_UPLOAD_FAIL = 3004;
  /**
   * 文件过大
   */
  public static final int FILE_SIZE_LARGER = 3010;
  /**
   * 验证码过期
   */
  public static final int CAPTCHA_EXPIRED = 3007;
  /**
   * 验证码错误
   */
  public final static int CAPTCHA_WRONG = 3008;
  /**
   * 验证码还未过期，可以继续使用
   */
  public static final int CAPTCHA_NOT_USED = 3006;
  /**
   * 消息发送失败,发送消息时异常
   */
  public static final int MESSAGE_CLIENT_ERROR = 3005;

  /**
   * 短信发送成功
   */
  public static final String RESPONSE_OK = "OK";
}
