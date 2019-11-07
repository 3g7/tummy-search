package com.fayelau.tummy.search.core.pojo;

/**
 * 斗鱼返回值
 * 
 * @author 3g7 2019-10-17 10:18:59
 * @version 0.0.1
 *
 */
public class DouyuResponseRO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    protected Integer error;
    protected RoomInfoRO data;
    
    public Integer getError() {
        return error;
    }
    public void setError(Integer error) {
        this.error = error;
    }
    public RoomInfoRO getData() {
        return data;
    }
    public void setData(RoomInfoRO data) {
        this.data = data;
    }
    
}
