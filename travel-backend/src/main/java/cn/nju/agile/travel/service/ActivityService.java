package cn.nju.agile.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nju.agile.travel.entity.Activity;
import cn.nju.agile.travel.repository.ActivityRepository;

import java.util.List;
import java.util.Date;

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

	public List<Activity> getActivityByOrganizerId(String organizer_id) {

		return activityRepository.findAllByOrganizerId(Long.parseLong(organizer_id));

	}

    public void save(Activity newActivity) {
		activityRepository.save(newActivity);
    }

    public List<Activity> findAllByCategory(String category){
		return activityRepository.findAllByCategory(category);
	}

	public List<Activity> findAllByLocation(String location){
		return activityRepository.findAllByLocation(location);
	}

	public List<Activity> findAllByStartTime(Date startTime){
		return activityRepository.findAllByStartTime(startTime);
	}

	public List<Activity> findAll(){ return activityRepository.findAll();}

	public Integer endActivityByActivityId(Long activityId){
		return activityRepository.updateActivityByActivityId(activityId);
	}
    
    
    public void cancel(Long activityId, Long organizerId) {
		activityRepository.cancelActivity(activityId, organizerId);
    }
}
