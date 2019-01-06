package cn.nju.agile.travel.service;

import cn.nju.agile.travel.redis.AESUtil;
import cn.nju.agile.travel.redis.RedisService;
import cn.nju.agile.travel.redis.UserKey;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TokenService {
    @Autowired
    private LogService logger;
    
    @Autowired
    private RedisService redisService;
    
    public String getToken(String userId) throws Exception {
        String token = getTokenByUserId(userId);
        redisService.set(UserKey.userAccessKey, userId, token);
        return token;
    }
    
    
    public boolean checkToken(String token,String userId) {
        String currentToken = redisService.get(UserKey.userAccessKey, userId, String.class);
        if (StringUtils.isEmpty(currentToken)) {
            return false;
        }
        if (!token.equals(currentToken)) {
            return false;
        }
        return true;
    }
    
    public static String getUserIdByToken(String token) throws Exception{
        return AESUtil.decryptByDefaultKey(token).split("_")[0];
    }
    
    public static String getTokenByUserId(String userId) throws Exception {
        return AESUtil.encryptByDefaultKey(Joiner.on("_").join(userId, System.currentTimeMillis()));
    }
}
