package com.fayelau.tummy.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Formula;

/**
 * 会话
 * 
 * @author 3g7 2019-10-12 09:04:24
 * @version 0.0.1
 *
 */
@Entity
@Table(name = "TUMMY_SEARCH_SESSION")
public class Session extends BaseJpaEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "PASSPORT_ID", nullable = false, length = 32)
    protected String passportId;
    
    @Column(name = "TOKEN", nullable = false, length = 32)
    protected String token;
    
    @Column(name = "CLIENT_BROWSER", length = 32)
    protected String clientBrowser;
    
    @Column(name = "CLIENT_IP", length = 32)
    protected String clientIp;
    
    @Column(name = "CLIENT_OS", length = 32)
    protected String clientOs;
    
    @Column(name = "EXPIRED_TIME", nullable = false)
    protected Long expiredTime;
    
    //虚拟列
    
    @Formula("(SELECT P.USERNAME FROM tummy_search_passport P WHERE P.ID = PASSPORT_ID)")
    protected String username;
    
    @Formula("(SELECT P.NICKNAME FROM tummy_search_passport P WHERE P.ID = PASSPORT_ID)")
    protected String nickname;

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientBrowser() {
        return clientBrowser;
    }

    public void setClientBrowser(String clientBrowser) {
        this.clientBrowser = clientBrowser;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientOs() {
        return clientOs;
    }

    public void setClientOs(String clientOs) {
        this.clientOs = clientOs;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
