package cn.nju.agile.travel.controller;

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
        
        User user = userService.getUserByUsernameAndPassword(userName, password);
        logger.getLogger()
                .debug("User Info: {}", JSON.toJSONString(user));
        Result result;
        
        if (user != null) {
            UserPOJO return_user = new UserPOJO(user);

            session.setAttribute(UserConstant.USER_ID, return_user.getUserId());
            session.setAttribute(UserConstant.USER_NAME, return_user.getUserName());
            redisTemplate.opsForValue().set(UserConstant.USER_ID + return_user.getUserId(), session.getId());

            result = new Result(StatusCode.SUCCESS, return_user);
            logger.getLogger()
                    .debug("User Info: {}", JSON.toJSONString(user));
        } else {
            result = new Result(StatusCode.AUTH_FAILED, "");
        }
        return result.toJsonString();
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request) {
        String userName = request.getHeader(UserConstant.USER_NAME);
        Result result;
        if (userService.isUserNameExists(userName)){
            result = new Result(StatusCode.USER_EXIST,"");
        }else {
            String password = request.getHeader(UserConstant.PASS_WORD);
            String sex = request.getHeader(UserConstant.SEX);
            String university = request.getHeader(UserConstant.UNIVERSITY);

            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setPwd(password);
            newUser.setSex(sex);
            newUser.setUniversity(university);

            User res = userService.addUser(newUser);
            if (res!=null){
                result = new Result(StatusCode.SUCCESS_IN_REGISTER,"");
            }else{
                result = new Result(StatusCode.FAILED_IN_REGISTER,"");
            }
        }
        return result.toJsonString();
    }

    // 修改个人基本信息,需要一个修改用户接口，传给你id
    @RequestMapping(value = "/getProfileById")
    @ResponseBody
    public User getProfileById() {
        Long id = 1002L;

        return userService.getUserById(id);
    }

}
