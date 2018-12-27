package cn.nju.agile.travel.pojo;

import cn.nju.agile.travel.consts.StatusCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;

public class Result {
    private StatusCode statusCode;
    private Object data;
    
    
    public Result(StatusCode statusCode, Object data) {
        this.statusCode = statusCode;
        this.data = data;
    }
    
    public String toJsonString() {
        SerializeConfig config = new SerializeConfig();
        config.configEnumAsJavaBean(StatusCode.class);
        return JSON.toJSONString(this, config);
    }
    
    public StatusCode getStatusCode() {
        return statusCode;
    }
    
    
    public Object getData() {
        return data;
    }
    
    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
    
    
    public void setData(Object data) {
        this.data = data;
    }
}
