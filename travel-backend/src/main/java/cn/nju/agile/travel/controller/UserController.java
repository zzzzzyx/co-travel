

package cn.nju.agile.travel.controller;


import cn.nju.agile.travel.config.interceptor.Secured;
import cn.nju.agile.travel.consts.StatusCode;
import cn.nju.agile.travel.consts.UserConstant;
import cn.nju.agile.travel.entity.User;
import cn.nju.agile.travel.pojo.Result;
import cn.nju.agile.travel.pojo.UserPOJO;
import cn.nju.agile.travel.service.LogService;
import cn.nju.agile.travel.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private LogService logger;

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session) {
        String userName = request.getHeader(UserConstant.USER_NAME);
        String password = request.getHeader(UserConstant.PASS_WORD);
        User user = userService.getByUserName(userName);
        Result result;
        if (user != null) {
            if (user.getPwd().equals(password)) {
                UserPOJO return_user = new UserPOJO(user);

                session.setAttribute(UserConstant.USER_ID, return_user.getUserId());
                session.setAttribute(UserConstant.USER_NAME, return_user.getUserName());
                redisTemplate.opsForValue().set(UserConstant.USER_ID+return_user.getUserId(),session.getId());

                result = new Result(StatusCode.SUCCESS, return_user);
                logger.getLogger()
                        .debug("User Info: {}", JSON.toJSONString(user));
            } else {
                result = new Result(StatusCode.AUTH_FAILED, "");
            }

        } else {
            result = new Result(StatusCode.USER_NOT_EXIST, "");
        }
        return result.toJsonString();
    }

    @Secured
    @GetMapping("/test")
    public String getTest(HttpSession session) {
        return (String) session.getAttribute(UserConstant.USER_NAME);
    }

    // 修改个人基本信息,需要一个修改用户接口，传给你id
    @RequestMapping(value = "/getByUserName")
    @ResponseBody
    public User getByUserName(HttpServletRequest request) {
        String userName = request.getParameter("userName");

        return userService.getByUserName(userName);
    }
}
