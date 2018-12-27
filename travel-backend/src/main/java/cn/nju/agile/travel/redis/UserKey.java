package cn.nju.agile.travel.redis;

public class UserKey extends BasePrefix {
    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey userAccessKey = new UserKey("access");
}
