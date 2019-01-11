package cn.nju.agile.travel.service;

import cn.nju.agile.travel.entity.User;
import cn.nju.agile.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }
}
