package cn.nju.agile.travel.controller;


import cn.nju.agile.travel.config.interceptor.Secured;
import cn.nju.agile.travel.consts.StatusCode;
import cn.nju.agile.travel.consts.UserConstant;
import cn.nju.agile.travel.pojo.Result;
import cn.nju.agile.travel.pojo.UserPOJO;
import cn.nju.agile.travel.service.LogService;
import cn.nju.agile.travel.service.TokenService;
import cn.nju.agile.travel.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private LogService logger;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TokenService tokenService;
    
    
    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        String userName = request.getHeader(UserConstant.User_NAME);
        String password = request.getHeader(UserConstant.PASS_WORD);
        UserPOJO user = userService.findUserByAccountAndPassword(userName, password);
        Result result;
        if (user != null) {
            try {
                user.setToken(tokenService.getToken(user.getuserId()));
                result = new Result(StatusCode.SUCCESS, user);
            } catch (Exception e) {
                e.printStackTrace();
                logger.getLogger().
                        error("UserID {} occurred error when tried to login", user.getuserId());
                logger.getLogger().
                        error(e.getMessage());
                result = new Result(StatusCode.NEED_LOGIN, "");
            }
            logger.getLogger()
                    .debug("User Info: {}", JSON.toJSONString(user));
        } else {
            result = new Result(StatusCode.NEED_LOGIN, "");
        }
        return result.toJsonString();
    }
    
    @Secured
    @PostMapping(value = "/getUserInfo")
    public String getUserInfo(String userId) {
        UserPOJO user = userService.findUserByUserId(userId);
        Result result;
        if (user != null) {
            result = new Result(StatusCode.SUCCESS, user);
        } else {
            result = new Result(StatusCode.USER_NOT_EXIST, "");
        }
        return result.toJsonString();
    }
}
