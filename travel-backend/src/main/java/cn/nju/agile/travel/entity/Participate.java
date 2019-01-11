package cn.nju.agile.travel.entity;

import javax.persistence.*;

@Entity
public class Participate{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Long activityId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
