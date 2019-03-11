package cn.nju.agile.travel.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.nju.agile.travel.entity.Activity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
	
	Activity getActivityByActivityName(String activityName);
	List<Activity> findAllByCategory(String Category);

	List<Activity> findAll();

	@Modifying
	@Transactional
	@Query("update Activity set activityStatus = 'finished' where id =?1")
	Integer updateActivityByActivityId(Long id);


	List<Activity> findAllByOrganizerId(Long organizer_id);
}
