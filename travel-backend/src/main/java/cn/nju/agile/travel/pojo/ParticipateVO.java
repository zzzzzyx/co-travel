package cn.nju.agile.travel.pojo;

import cn.nju.agile.travel.entity.Participate;

public class ParticipateVO {

    private Long userId;
    private Long activityId;
    private String activityName;

    public ParticipateVO(Participate participate, String activityName) {
        this.userId = participate.getUserId();
        this.activityId = participate.getActivityId();
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
