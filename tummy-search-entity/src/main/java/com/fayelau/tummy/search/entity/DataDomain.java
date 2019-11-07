package com.fayelau.tummy.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 数据分域
 * 
 * @author 3g7 2019-10-12 14:42:04
 * @version 0.0.1
 *
 */
@Entity
@Table(name = "TUMMY_SEARCH_DATA_DOMAIN")
public class DataDomain extends BaseJpaEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "NAME", nullable = false, length = 32)
    protected String name;
    
    @Column(name = "DOMAIN_FIELD", nullable = false, length = 32)
    protected String domainField;
    
    @Column(name = "DOMAIN_VALUE", nullable = false, length = 128)
    protected String domainValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomainField() {
        return domainField;
    }

    public void setDomainField(String domainField) {
        this.domainField = domainField;
    }

    public String getDomainValue() {
        return domainValue;
    }

    public void setDomainValue(String domainValue) {
        this.domainValue = domainValue;
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
