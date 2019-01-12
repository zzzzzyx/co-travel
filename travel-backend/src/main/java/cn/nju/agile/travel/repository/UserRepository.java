package cn.nju.agile.travel.repository;

import cn.nju.agile.travel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long id);

    @Query("select s from User s where s.userName=?1 and s.pwd=?2")
    User getUserByNameAndPassword(String userName,String password);

    @Query("select count(s) from User s where s.userName=?1")
    int isUserNameExists(String username);
}
