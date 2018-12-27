package cn.nju.agile.travel.pojo;

import java.io.Serializable;

public class UserPOJO implements Serializable {
    private static final long serialVersionUID = 1279872207803310961L;
    
    private String userID;
    private String userName;
    private String email;
    private String sex;
    private String birthday;
    private String avatarURL;
    private String token;
    
    public UserPOJO(String userID, String userName, String email, String sex, String birthday, String avatarURL, String token) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
        this.avatarURL = avatarURL;
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getAvatarURL() {
        return avatarURL;
    }
    
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
