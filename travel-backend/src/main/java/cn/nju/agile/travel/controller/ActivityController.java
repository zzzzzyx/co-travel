package cn.nju.agile.travel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.nju.agile.travel.consts.UserConstant;
import cn.nju.agile.travel.service.LogService;
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

    @RequestMapping(value = "/getActivityById")
    @ResponseBody
    public HashMap<String, Object> getActivityById(HttpServletRequest request) {
        Long activityId = Long.parseLong(request.getParameter("activityId"));
        Activity activity = activityService.getActivityById(activityId);

        HashMap<String, Object> res = new HashMap<>();
        res.put("activity", activity);

        String ifOrganizerInfo = request.getParameter("ifOrganizerInfo");
        if (ifOrganizerInfo.equals("true")) {
            Long userId = activity.getOrganizerId();
            User user = userService.getUserById(userId);

            res.put("organizer", user);

        }

        return res;
    }

    @RequestMapping(value = "/getActivityByName")
    @ResponseBody
    public Activity getActivityByName(HttpServletRequest request) {
        String activityName = request.getParameter("activityName");

        return activityService.getActivityByActivityName(activityName);
    }

    @RequestMapping(value = "/getActivityByOrganizerId")
    @ResponseBody
    public List<Activity> getActivityByOrganizerId(HttpServletRequest request) {
        String organizer_id = request.getParameter("organizer_id");
        if (organizer_id.equals("undefined")) {
            return activityService.getActivityByOrganizerId("1002");
        }
        return activityService.getActivityByOrganizerId(organizer_id);
    }

    @RequestMapping(value = "/endActivityById")
    @ResponseBody
    public Integer endActivityById(HttpServletRequest request) {
        Long activityId = Long.parseLong(request.getParameter("activityId"));
        Long userId = Long.parseLong(request.getParameter("userId"));
        System.out.println("activityID:" + activityId);
        return activityService.endActivityByActivityId(activityId);
    }


    @RequestMapping(value = "/findAllByCategory")
    @ResponseBody
    public List<Activity> findAllByCategory(HttpServletRequest request) {
        String category = request.getParameter("category");

        System.out.println(category);
        if (category.equals("all")) {
            return activityService.findAll();
        }
        return activityService.findAllByCategory(category);
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public Activity saveActivity(HttpServletRequest request) throws ParseException {
        String activityName = request.getHeader("activityName");
        String startTime = request.getHeader("startTime");
        String endTime = request.getHeader("endTime");
        String category = request.getHeader("category");
        String location = request.getHeader("location");
        String detail = request.getHeader("detail");
        String organizerId = request.getHeader("user_id");
        String registrationDeadline = request.getHeader("registrationDeadline");
        String activityStatus = "registering";
        System.out.println(organizerId);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        Activity newActivity = new Activity(activityName, f.parse(startTime), f.parse(endTime), category, location, detail, Long.parseLong(organizerId), f.parse(registrationDeadline), activityStatus);
        System.out.println(newActivity);
        activityService.save(newActivity);
        System.out.println("{\"code\":200,\"msg\":success}");
        return newActivity;
    }

    private Date parseByLength(String datestr) throws ParseException{
        SimpleDateFormat f;
        if(datestr.length()==23)
            f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        else if(datestr.length()==19)
            f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        else
            f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        return f.parse(datestr);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Activity updateActivity(HttpServletRequest request) throws ParseException {
        String activityId = request.getParameter("activityId");
        String activityName = request.getParameter("activityName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String category = request.getParameter("category");
        String location = request.getParameter("location");
        String detail = request.getParameter("detail");
        String organizerId = request.getParameter("user_id");
        String registrationDeadline = request.getParameter("registrationDeadline");
        String activityStatus = request.getParameter("status");
        System.out.println(organizerId);

        Activity newActivity = new Activity(activityName, parseByLength(startTime), parseByLength(endTime), category, location, detail, Long.parseLong(organizerId), parseByLength(registrationDeadline), activityStatus);
        newActivity.setId(Long.parseLong(activityId));
        System.out.println(newActivity);
        activityService.save(newActivity);
        System.out.println("{\"code\":200,\"msg\":success}");
        return newActivity;
    }

}
