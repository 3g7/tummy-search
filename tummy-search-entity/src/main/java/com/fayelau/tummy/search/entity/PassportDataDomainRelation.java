package com.fayelau.tummy.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Formula;

/**
 * 通行证数据分域关系
 * 
 * @author 3g7 2019-10-12 14:49:44
 * @version 0.0.1
 *
 */
@Entity
@Table(name = "TUMMY_SEARCH_PT_DATA_DOMAIN_R")
public class PassportDataDomainRelation extends BaseJpaEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "PASSPORT_ID", nullable = false, length = 32)
    protected String passportId;
    
    @Column(name = "TARGET", nullable = false, length = 128)
    protected String target;
    
    @Column(name = "DATA_DOMAIN_ID", nullable = false, length = 32)
    protected String dataDomainId;
    
    @Formula("(SELECT P.USERNAME FROM tummy_search_passport P WHERE P.ID = PASSPORT_ID)")
    protected String passportUsername;
    
    @Formula("(SELECT P.NICKNAME FROM tummy_search_passport P WHERE P.ID = PASSPORT_ID)")
    protected String passportNickname;
    
    @Formula("(SELECT D.NAME FROM tummy_search_data_domain D WHERE D.ID = DATA_DOMAIN_ID)")
    protected String dataDomainName;

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getDataDomainId() {
        return dataDomainId;
    }

    public void setDataDomainId(String dataDomainId) {
        this.dataDomainId = dataDomainId;
    }

    public String getPassportUsername() {
        return passportUsername;
    }

    public void setPassportUsername(String passportUsername) {
        this.passportUsername = passportUsername;
    }

    public String getPassportNickname() {
        return passportNickname;
    }

    public void setPassportNickname(String passportNickname) {
        this.passportNickname = passportNickname;
    }

    public String getDataDomainName() {
        return dataDomainName;
    }

    public void setDataDomainName(String dataDomainName) {
        this.dataDomainName = dataDomainName;
    }
    
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
