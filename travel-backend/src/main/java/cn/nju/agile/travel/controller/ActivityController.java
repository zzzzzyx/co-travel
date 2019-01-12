package cn.nju.agile.travel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.nju.agile.travel.consts.UserConstant;
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

    @PostMapping(value="/save")
    public void saveActivity(HttpSession session, HttpServletRequest request) throws ParseException {
        String activityName = request.getHeader("activityName");
        String startTime = request.getHeader("startTime");
        String endTime = request.getHeader("endTime");
        String category = request.getHeader("category");
        String location = request.getHeader("location");
        String detail = request.getHeader("detail");
        String organizerId = (String)session.getAttribute(UserConstant.USER_ID);
        String registrationDeadline = request.getHeader("registrationDeadline");
        String activityStatus = "init";

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Activity newActivity = new Activity( activityName,  f.parse(startTime),  f.parse(endTime),  category,  location,  detail,  Long.parseLong(organizerId),  f.parse(registrationDeadline),  activityStatus);
        System.out.println(newActivity);
        activityService.save(newActivity);
    }

}
