package com.fayelau.tummy.search.store.mongo.pojo;

/**
 * 排行类
 * @author 3g7 2019-09-30 09:50:35
 * @version 0.0.1
 *
 */
public class Rank implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    protected String uid;
    
    protected String nickname;
    
    protected String count;
    
    protected Integer rank;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
    
}
