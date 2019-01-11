package cn.nju.agile.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.nju.agile.travel.entity.Participate;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long>{

	List<Participate> getParticipateByUserId(Long userId);
	
}
