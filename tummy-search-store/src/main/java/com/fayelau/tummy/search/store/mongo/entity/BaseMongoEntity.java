package com.fayelau.tummy.search.store.mongo.entity;

import org.springframework.data.annotation.Id;

/**
 * mongo实体的通用字段
 * 
 * @author 3g7 2019-09-07 01:31:58
 * @version 0.0.1
 *
 */
public class BaseMongoEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    protected String id; // 编号

    protected String roomId; // 房间号

    protected Long timestamp; // 创建时间

    protected String type; // 消息类型
    
    protected String liveStatus; //开关播状态
    
    protected String uid; //斗鱼用户ID
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
}
