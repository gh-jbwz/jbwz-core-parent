package com.jbwz.core.common.base;

/**
 * every controller should inherit  BaseController
 *
 * @author yyh
 */
public class BaseController {


    protected ResponseJson success(int code, Object data) {
        return new ResponseJson(code, data);
    }


    protected ResponseJson success(Object data) {
        return new ResponseJson(ResponseCode.SUCCESS, data);
    }

    protected ResponseJson success(int code) {
        return new ResponseJson(code, ResponseCode.SUCCESS);
    }

    protected ResponseJson success() {
        return new ResponseJson(ResponseCode.SUCCESS);
    }


    protected ResponseJson fail(int code) {
        return new ResponseJson(code);
    }

    protected ResponseJson fail(int code, String message) {
        return new ResponseJson(code, message);
    }

}
