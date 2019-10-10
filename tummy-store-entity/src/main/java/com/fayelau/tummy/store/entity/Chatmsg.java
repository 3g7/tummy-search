package com.fayelau.tummy.store.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 弹幕实体
 * 
 * @author 3g7 2019-09-07 01:28:59
 * @version 0.0.1
 *
 */
public class Chatmsg extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    protected String level; // 等级

    protected String cst; // 斗鱼时间戳

    protected String brid; // 粉丝牌所属房间

    protected String urlev; // 未知

    protected String avatar; // 头像

    protected String rid; // 房间号

    protected String txt; // 弹幕

    protected String nickname; // 昵称

    protected String sahf; // 未知

    protected String hc; // 未知

    protected String fansLevel; // 粉丝牌等级

    protected String cid; // 未知

    protected String fansBrand; // 粉丝牌名称

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getBrid() {
        return brid;
    }

    public void setBrid(String brid) {
        this.brid = brid;
    }

    public String getUrlev() {
        return urlev;
    }

    public void setUrlev(String urlev) {
        this.urlev = urlev;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSahf() {
        return sahf;
    }

    public void setSahf(String sahf) {
        this.sahf = sahf;
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    public String getFansLevel() {
        return fansLevel;
    }

    public void setFansLevel(String fansLevel) {
        this.fansLevel = fansLevel;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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
