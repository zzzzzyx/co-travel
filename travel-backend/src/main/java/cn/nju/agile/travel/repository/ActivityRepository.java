package cn.nju.agile.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.nju.agile.travel.entity.Activity;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
	
	Activity getActivityByActivityName(String activityName);

	List<Activity> findAllByCategory(String Category);


}
