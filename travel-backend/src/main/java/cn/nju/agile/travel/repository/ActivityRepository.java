package cn.nju.agile.travel.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.nju.agile.travel.entity.Activity;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
	
	Activity getActivityByActivityName(String activityName);

	List<Activity> findAllByCategory(String Category);

	List<Activity> findAll();

	@Modifying
	@Query("update Activity act set act.activityStatus = finished where act.id = id")
	Boolean updateActivityByActivityId(@Param(value="id") Long activityId);

}
