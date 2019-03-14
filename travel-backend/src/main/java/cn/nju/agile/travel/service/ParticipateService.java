package cn.nju.agile.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nju.agile.travel.entity.Participate;
import cn.nju.agile.travel.entity.User;
import cn.nju.agile.travel.repository.ParticipateRepository;

@Service
public class ParticipateService {
    
    @Autowired
    ParticipateRepository participateRepository;
    
    public Participate addParticipate(Participate participate) {
        return participateRepository.save(participate);
    }
    
    public List<Participate> getParticipateByUserId(Long userId) {
        return participateRepository.getParticipateByUserId(userId);
    }
    
    public void removeParticipateByUserIdAndActivityId(Long userId, Long activityId) {
        participateRepository.removeParticipateByUserIdAndActivityId(userId, activityId);
    }
}
