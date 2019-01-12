package cn.nju.agile.travel.consts;


public enum StatusCode {
    SUCCESS(200, "登录成功"),
    NEED_LOGIN(401, "用户未登录"),
    USER_NOT_EXIST(4001, "用户名不存在"),
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
