package cn.nju.agile.travel.redis;

public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}
