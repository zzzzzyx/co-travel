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

    public User getUserByUsernameAndPassword(String userName, String password) {
        return userRepository.getUserByNameAndPassword(userName, password);
    }

    public boolean isUserNameExists(String userName) {
        return userRepository.isUserNameExists(userName) > 0;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

}
