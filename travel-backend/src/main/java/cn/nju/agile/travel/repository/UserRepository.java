package cn.nju.agile.travel.repository;

import cn.nju.agile.travel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    User getUserByUserName(String name);

}
