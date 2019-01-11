package cn.nju.agile.travel.entity;

import java.util.Date;

import javax.persistence.*;


@Entity
public class Activity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String activityName;
	private Date startTime;
	private Date endTime;
	private String category;
	private String location;
	private String detail;
	private Long organizerId;
	private Date registrationDeadline;
	private String activityStatus;

	public Activity() {
	}

	public Activity(String activityName, Date startTime, Date endTime, String category, String location, String detail, Long organizerId, Date registrationDeadline, String activityStatus) {
		this.activityName = activityName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.category = category;
		this.location = location;
		this.detail = detail;
		this.organizerId = organizerId;
		this.registrationDeadline = registrationDeadline;
		this.activityStatus = activityStatus;
	}

	@Override
	public String toString() {
		return "Activity{" +
				"activityName='" + activityName + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", category='" + category + '\'' +
				", location='" + location + '\'' +
				", detail='" + detail + '\'' +
				", organizerId=" + organizerId +
				", registrationDeadline=" + registrationDeadline +
				", activityStatus='" + activityStatus + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(Long organizerId) {
		this.organizerId = organizerId;
	}

	public Date getRegistrationDeadline() {
		return registrationDeadline;
	}

	public void setRegistrationDeadline(Date registrationDeadline) {
		this.registrationDeadline = registrationDeadline;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	
}
