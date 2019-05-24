package com.jbwz.core.common.base;


/**
 * 统一返回界面的json格式
 *
 * @author yyh
 */
public class ResponseJson {

    private int code; //返回界面判断
    private String message; //简洁消息
    private Object data;//自定义json数据

    public ResponseJson() {
    }

    public ResponseJson(int code) {
        this.code = code;
    }

    public ResponseJson(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResponseJson(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    /**
     * 自定义json数据
     */
    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"code\":").append(code)
                .append(",\"message\":").append(message)
                .append(",\"data\":").append(data)
                .append("}");
        return stringBuilder.toString();
    }
}
