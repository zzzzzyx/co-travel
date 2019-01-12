package cn.nju.agile.travel.service;

import cn.nju.agile.travel.config.RedisUtil;
import cn.nju.agile.travel.entity.User;
import cn.nju.agile.travel.pojo.UserPOJO;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service("tokenService")
public class TokenService {
    
    @Resource
    private RedisUtil redisUtil;
    
    public String generateToken(String userName) {
        return new StringBuffer()
                .append(DigestUtils.sha1DigestAsHex(userName))
                .append("-")
                .append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))
                .append("-")
                .append(new Random().nextInt(987654321))
                .toString();
    }
    
    public void save(String token, UserPOJO user) {
        redisUtil.set(token, JSON.toJSONString(user));
    }
    
    public UserPOJO getUserByToken(String token) {
        return JSON.parseObject(redisUtil.get(token), UserPOJO.class);
    }
    
    public boolean checkToken(String token, String userId) {
        String userJsonString = redisUtil.get(token);
        
        if (StringUtils.isEmpty(userJsonString)) {
            return false;
        }
        UserPOJO user = JSON.parseObject(userJsonString, UserPOJO.class);
        return userId.equals(String.valueOf(user.getUserId()));
    }
}