package com.fayelau.tummy.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 斗鱼礼物信息
 * @author 3g7 2019-10-11 16:21:51
 * @version 0.0.1
 *
 */
@Entity
@Table(name = "TUMMY_SEARCH_GIFT_INFO")
public class GiftInfo extends BaseJpaEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "NAME", nullable = false, length = 32)
    protected String name;
    
    @Column(name = "CODE", nullable = false, length = 16)
    protected String code;
    
    @Column(name = "gif", nullable = false, length = 255)
    protected String gif;
    
    @Column(name = "PRICE", nullable = false)
    protected Integer price;
    
    @Column(name = "INTIMACY", nullable = false)
    protected Integer intimacy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(Integer intimacy) {
        this.intimacy = intimacy;
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
