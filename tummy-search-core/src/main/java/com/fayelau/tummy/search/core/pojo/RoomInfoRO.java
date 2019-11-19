package com.fayelau.tummy.search.core.pojo;

import java.util.Collection;

/**
 * 房间信息
 * @author 3g7 2019-10-17 10:13:00
 * @version 0.0.1
 *
 */
public class RoomInfoRO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    protected String room_id;
    protected Collection<GiftInfoRO> gift;
    
    public String getRoom_id() {
        return room_id;
    }
    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
    public Collection<GiftInfoRO> getGift() {
        return gift;
    }
    public void setGift(Collection<GiftInfoRO> gift) {
        this.gift = gift;
    }
    
}
