package cn.nju.agile.travel.config.interceptor;


import cn.nju.agile.travel.consts.StatusCode;
import cn.nju.agile.travel.consts.UserConstant;
import cn.nju.agile.travel.pojo.Result;
import cn.nju.agile.travel.service.LogService;
import cn.nju.agile.travel.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private LogService logService;

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        if (session.getAttribute(UserConstant.USER_ID) != null) {
//            try {
//                String loginSessionId = redisTemplate.opsForValue().get(UserConstant.USER_ID + (long) session.getAttribute(UserConstant.USER_ID));
//                if (loginSessionId != null && loginSessionId.equals(session.getId())) {
//                    return true;
//                }
//            } catch (Exception e) {
//                logService.getLogger()
//                        .error("ERROR occurred when checked login, message = {}", e.getMessage());
//            }
//        }
//        try {
//            render(response, StatusCode.NEED_LOGIN);
//        } catch (Exception e) {
//            logService.getLogger()
//                    .error("ERROR occurred when response: message = {}",e.getMessage());
//        }
//        return false;
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Secured secured = hm.getMethodAnnotation(Secured.class);
            if (secured != null) {
                String token = request.getHeader(UserConstant.USER_TOKEN);
                String userId = request.getHeader(UserConstant.USER_ID);
                logService.getLogger().debug("token {} userId {}", token, userId);
                if (StringUtils.isEmpty(token)) {
                    render(response, StatusCode.NEED_LOGIN);
                    return false;
                }

                if (!tokenService.checkToken(token, userId)) {
                    render(response, StatusCode.LOGIN_FAILURE);
                    return false;
                }
            }
            return true;
        }
        return true;
    }


    private void render(HttpServletResponse response, StatusCode statusCode) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();

        Result result = new Result(statusCode, "登录信息验证错误");
        out.write(result.toJsonString().getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
