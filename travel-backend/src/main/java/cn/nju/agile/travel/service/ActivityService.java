package cn.nju.agile.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nju.agile.travel.entity.Activity;
import cn.nju.agile.travel.repository.ActivityRepository;

@Service
public class ActivityService {
	
	@Autowired
	ActivityRepository activityRepository;
	
	public Activity getActivityById(Long activityId) {
		return activityRepository.findById(activityId).get();
	}
	
	public Activity getActivityByActivityName(String activityName) {
		
		return activityRepository.getActivityByActivityName(activityName);
		
	}

    public void save(Activity newActivity) {
		activityRepository.save(newActivity);
    }
}
