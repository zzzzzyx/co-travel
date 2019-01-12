package cn.nju.agile.travel.aop_logger;

import cn.nju.agile.travel.service.LogService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerLogger {
    @Autowired
    private LogService logService;
    
    @Pointcut(value = "execution(public * cn.nju.agile.travel.controller..*.*(..))")
    public void ControllerLog() {
    }
    
    @Before("ControllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        logService.getLogger()
                .info("Request URL: {}, HTTP method: {}, Class Method: {}, Args: {}",
                        request.getRequestURL().toString(),
                        request.getMethod(),
                        joinPoint.getSignature().toString(),
                        Arrays.toString(joinPoint.getArgs()));
    }
    
    @AfterReturning(returning = "result", pointcut = "ControllerLog()")
    public void doAfterReturning(MysqlxDatatypes.Object result) {
        logService.getLogger()
                .info("Response: {}", result);
    }
    
}
