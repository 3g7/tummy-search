package com.fayelau.tummy.store.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 礼物信息实体
 * 
 * @author 3g7 2019-09-07 02:06:13
 * @version 0.0.1
 *
 */
public class Dgb extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    protected String eid; // 未知

    protected String bst; // 未知

    protected String brid; // 粉丝牌所在房间号

    protected String bnid; // 未知

    protected String gfid; // 礼物ID

    protected String pid; // 未知

    protected String rid; // 斗鱼房间号

    protected String dw; // 未知

    protected String nickname; // 昵称

    protected String from; // 未知

    protected String gpf; // 未知

    protected String gfnum; // 礼物数量

    protected String bnl; // 未知

    protected String level; // 等级

    protected String bcnt; // 未知

    protected String cm; // 未知

    protected String avatar; // 头像

    protected String gs; // 未知

    protected String hits; // 连击数

    protected String ct; // 未知

    protected String sahf; // 未知

    protected String hc; // 未知

    protected String fansLevel; // 粉丝牌等级

    protected String fc; // 未知

    protected String eic; // 未知

    protected String fansBrand; // 粉丝牌

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getBst() {
        return bst;
    }

    public void setBst(String bst) {
        this.bst = bst;
    }

    public String getBrid() {
        return brid;
    }

    public void setBrid(String brid) {
        this.brid = brid;
    }

    public String getBnid() {
        return bnid;
    }

    public void setBnid(String bnid) {
        this.bnid = bnid;
    }

    public String getGfid() {
        return gfid;
    }

    public void setGfid(String gfid) {
        this.gfid = gfid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getGpf() {
        return gpf;
    }

    public void setGpf(String gpf) {
        this.gpf = gpf;
    }

    public String getGfnum() {
        return gfnum;
    }

    public void setGfnum(String gfnum) {
        this.gfnum = gfnum;
    }

    public String getBnl() {
        return bnl;
    }

    public void setBnl(String bnl) {
        this.bnl = bnl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBcnt() {
        return bcnt;
    }

    public void setBcnt(String bcnt) {
        this.bcnt = bcnt;
    }

    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
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

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getEic() {
        return eic;
    }

    public void setEic(String eic) {
        this.eic = eic;
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
