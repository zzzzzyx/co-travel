package cn.nju.agile.travel.repository;

import cn.nju.agile.travel.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,String> {

    Activity getActivityById(int id);

}
