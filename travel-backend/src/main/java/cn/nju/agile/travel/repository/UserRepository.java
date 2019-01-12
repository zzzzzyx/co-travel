package cn.nju.agile.travel.repository;

import cn.nju.agile.travel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUserName(String name);

    @Query("select s from User s where s.userName=?1 and s.pwd=?2")
    User getUserByNameAndPassword(String userName,String password);
}
