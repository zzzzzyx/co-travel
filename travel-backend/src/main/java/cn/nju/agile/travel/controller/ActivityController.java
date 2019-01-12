package cn.nju.agile.travel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.nju.agile.travel.consts.UserConstant;
import cn.nju.agile.travel.service.LogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.nju.agile.travel.entity.Activity;
import cn.nju.agile.travel.entity.User;
import cn.nju.agile.travel.service.ActivityService;
import cn.nju.agile.travel.service.UserService;

@RestController
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private LogService logger;

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getActivityById")
    @ResponseBody
    public HashMap<String, Object> getActivityById(HttpServletRequest request){
        Long activityId=Long.parseLong(request.getParameter("activityId"));
        Activity activity=activityService.getActivityById(activityId);
        
        HashMap<String, Object> res=new HashMap<>();
    	res.put("activity", activity);
        
        String ifOrganizerInfo=request.getParameter("ifOrganizerInfo");
        if(ifOrganizerInfo.equals("true")) {
        	Long userId=activity.getOrganizerId();
        	User user=userService.getUserById(userId);
        	
        	res.put("organizer", user);
        	
        }

        return res;
    }
	
	@RequestMapping(value="/getActivityByName")
    @ResponseBody
    public Activity getActivityByName(HttpServletRequest request){
        String activityName=request.getParameter("activityName");

        return activityService.getActivityByActivityName(activityName);
    }


}
