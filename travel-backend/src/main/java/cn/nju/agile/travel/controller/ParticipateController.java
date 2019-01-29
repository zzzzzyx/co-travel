package cn.nju.agile.travel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.nju.agile.travel.entity.Participate;
import cn.nju.agile.travel.service.ActivityService;
import cn.nju.agile.travel.service.ParticipateService;
import cn.nju.agile.travel.service.UserService;

@RestController
@RequestMapping(value = "/participate")
public class ParticipateController {
	
	@Autowired
	ParticipateService participateService;
	@Autowired
	UserService userService;
	@Autowired
	ActivityService activityService;
	
	@RequestMapping(value="/addParticipate")
    @ResponseBody
    public Participate addParticipate(HttpServletRequest request) {
		Long userId=Long.parseLong(request.getParameter("userId"));
		Long activityId=Long.parseLong(request.getParameter("activityId"));
		
		List<Participate> list=participateService.getParticipateByUserId(userId);
		boolean res=false;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getActivityId().equals(activityId)) {
				res=true;
				break;
			}
		}
		
		Participate participate=new Participate();
		participate.setUserId(userId);
		participate.setActivityId(activityId);
		
		if(res){
			return participate;
		}
		
		return participateService.addParticipate(participate);
		
	}




	@RequestMapping(value="/ifUserParticipated")
    @ResponseBody
    public boolean ifUserParticipated(HttpServletRequest request) {
		Long userId=Long.parseLong(request.getParameter("userId"));
		Long activityId=Long.parseLong(request.getParameter("activityId"));
		
		List<Participate> list=participateService.getParticipateByUserId(userId);
		boolean res=false;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getActivityId().equals(activityId)) {
				res=true;
				break;
			}
		}
		return res;
	}

}
