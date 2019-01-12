package cn.nju.agile.travel.consts;


public enum StatusCode {
    SUCCESS(200, "登录成功"),
    SUCCESS_IN_REGISTER(300,"注册成功"),
    FAILED_IN_REGISTER(301,"注册失败"),
    NEED_LOGIN(401, "用户未登录"),
    USER_EXIST(4001, "用户名已存在"),

    AUTH_FAILED(4001,"用户名与密码不匹配"),
    LOGIN_FAILURE(402, "用户登录失败");
    
    private int code;
    private String message;
    
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public int getCode() {
        return code;
    }
}
