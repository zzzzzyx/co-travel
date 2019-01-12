package cn.nju.agile.travel.pojo;

import cn.nju.agile.travel.entity.User;

import java.io.Serializable;

public class UserPOJO implements Serializable {
    private static final long serialVersionUID = 1279872207803310961L;

    private long userId;
    private String userName;
    private String sex;
    private String university;

    public UserPOJO(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.sex = user.getSex();
        this.university = user.getUniversity();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
