package com.fayelau.tummy.store.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 入场信息
 * 
 * @author 3g7 2019-09-07 12:16:44
 * @version 0.0.1
 *
 */
public class Uenter extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    protected String wgei; // 未知

    protected String level; // 等级

    protected String avatar; // 头像

    protected String rid; // 房间号

    protected String rni; // 未知

    protected String sahf; // 未知

    protected String el; // 未知

    protected String nickname; // 昵称

    protected String nl; // 未知

    public String getWgei() {
        return wgei;
    }

    public void setWgei(String wgei) {
        this.wgei = wgei;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getRni() {
        return rni;
    }

    public void setRni(String rni) {
        this.rni = rni;
    }

    public String getSahf() {
        return sahf;
    }

    public void setSahf(String sahf) {
        this.sahf = sahf;
    }

    public String getEl() {
        return el;
    }

    public void setEl(String el) {
        this.el = el;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
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
