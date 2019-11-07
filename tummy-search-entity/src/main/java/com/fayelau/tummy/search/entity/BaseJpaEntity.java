package com.fayelau.tummy.search.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

/**
 * 基础Jpa实体
 * 
 * @author 3g7 2019-10-12 08:51:17
 * @version 0.0.1
 *
 */
@MappedSuperclass
public class BaseJpaEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "GenericGenerator")
    @GenericGenerator(name = "GenericGenerator", strategy = "uuid")
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    protected String id;

    @Column(name = "ENABLE", nullable = false, length = 1)
    protected String enable;

    @Column(name = "LOCKING", nullable = false, length = 1)
    protected String locking;

    @Column(name = "CREATED", nullable = false)
    protected Long created;

    @Column(name = "CREATE_ID", nullable = false, length = 32)
    protected String createId;

    @Column(name = "MODIFIED")
    protected Long modified;

    @Column(name = "MODIFY_ID", length = 32)
    protected String modifyId;

    @Column(name = "REMARK", length = 255)
    protected String remark;

    @Version
    protected Long version;

    // 虚拟列
    @Formula("(SELECT P.USERNAME FROM tummy_search_passport P WHERE P.ID = CREATE_ID)")
    protected String createUsername;

    @Formula("(SELECT P.NICKNAME FROM tummy_search_passport P WHERE P.ID = CREATE_ID)")
    protected String createNickname;

    @Formula("(SELECT P.USERNAME FROM tummy_search_passport P WHERE P.ID = MODIFY_ID)")
    protected String modifyUsername;

    @Formula("(SELECT P.NICKNAME FROM tummy_search_passport P WHERE P.ID = MODIFY_ID)")
    protected String modifyNickname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getLocking() {
        return locking;
    }

    public void setLocking(String locking) {
        this.locking = locking;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public String getModifyId() {
        return modifyId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public String getCreateNickname() {
        return createNickname;
    }

    public void setCreateNickname(String createNickname) {
        this.createNickname = createNickname;
    }

    public String getModifyUsername() {
        return modifyUsername;
    }

    public void setModifyUsername(String modifyUsername) {
        this.modifyUsername = modifyUsername;
    }

    public String getModifyNickname() {
        return modifyNickname;
    }

    public void setModifyNickname(String modifyNickname) {
        this.modifyNickname = modifyNickname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }
    
}
