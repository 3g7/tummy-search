package com.fayelau.tummy.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 通行证
 * 
 * @author 3g7 2019-10-12 08:50:37
 * @version 0.0.1
 *
 */
@Entity
@Table(name = "TUMMY_SEARCH_PASSPORT")
public class Passport extends BaseJpaEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "NICKNAME", nullable = false, length = 32)
    protected String nickname;
    
    @Column(name = "USERNAME", unique = true, nullable = false, length = 64)
    protected String username;
    
    @Column(name = "PASSWORD", nullable = false, length = 64)
    protected String password;
    
    @Column(name = "AVATAR", nullable = true, length = 255)
    protected String avatar;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
