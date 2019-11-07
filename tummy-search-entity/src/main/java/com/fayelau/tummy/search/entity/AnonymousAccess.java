package com.fayelau.tummy.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 匿名访问列表
 * 
 * @author 3g7 2019-10-12 09:15:28
 * @version 0.0.1
 *
 */
@Entity
@Table(name = "TUMMY_SEARCH_ANONYMOUS_ACCESS")
public class AnonymousAccess extends BaseJpaEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "NAME", nullable = false, length = 32)
    protected String name;
    
    @Column(name = "REUQEST_URI", nullable = false, length = 255)
    protected String requestUri;
    
    @Column(name = "REUQEST_METHOD", nullable = false, length = 16)
    protected String requestMethod;
    
    @Column(name = "MATCH_TYPE", nullable = false, length = 1)
    protected String matchType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
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
