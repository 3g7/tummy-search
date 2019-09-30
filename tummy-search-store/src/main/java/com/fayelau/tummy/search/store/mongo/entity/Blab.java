package com.fayelau.tummy.search.store.mongo.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 粉丝牌升级信息实体
 * 
 * @author 3g7 2019-09-07 02:02:35
 * @version 0.0.1
 *
 */
@Document("blab")
public class Blab extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    protected String nickname; // 昵称

    protected String oldFansLevel; // 就粉丝牌等级

    protected String fansLevel; // 当前粉丝牌等级

    protected String rid; // 房间号

    protected String ba; // 未知

    protected String fansBrand; // 粉丝牌

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOldFansLevel() {
        return oldFansLevel;
    }

    public void setOldFansLevel(String oldFansLevel) {
        this.oldFansLevel = oldFansLevel;
    }

    public String getFansLevel() {
        return fansLevel;
    }

    public void setFansLevel(String fansLevel) {
        this.fansLevel = fansLevel;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getBa() {
        return ba;
    }

    public void setBa(String ba) {
        this.ba = ba;
    }

    public String getFansBrand() {
        return fansBrand;
    }

    public void setFansBrand(String fansBrand) {
        this.fansBrand = fansBrand;
    }
    
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
