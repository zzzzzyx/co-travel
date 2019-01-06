package cn.nju.agile.travel.service;

import cn.nju.agile.travel.entity.User;
import cn.nju.agile.travel.pojo.UserPOJO;
import cn.nju.agile.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserPOJO findUserByAccountAndPassword(String userName, String password) {
        return new UserPOJO("1","test2","123@123.com","female","19950819","http://avatar","");
    }
    
    public UserPOJO findUserByUserId(String userId) {
        return new UserPOJO("1","test2","123@123.com","female","19950819","http://avatar","");
    }

    public User getByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }
}
