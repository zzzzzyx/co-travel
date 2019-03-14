package cn.nju.agile.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.nju.agile.travel.entity.Participate;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long>{

	List<Participate> getParticipateByUserId(Long userId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Participate where userId = ?1 and activityId = ?2")
    void removeParticipateByUserIdAndActivityId(Long userId, Long activityId);
}
