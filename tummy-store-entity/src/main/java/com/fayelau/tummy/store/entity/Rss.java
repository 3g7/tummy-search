package com.fayelau.tummy.store.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 开关播提醒
 * 
 * @author 3g7 2019-09-07 12:15:34
 * @version 0.0.1
 *
 */
public class Rss extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;
    
    protected String ss; //未知
    
    protected String rt; //未知
    
    protected String gid; //未知
    
    protected String code; //未知
    
    protected String rtv; //未知
    
    protected String endtime; //关播时间
    
    protected String rid; //房间号
    
    protected String notify; //未知

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRtv() {
        return rtv;
    }

    public void setRtv(String rtv) {
        this.rtv = rtv;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
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
