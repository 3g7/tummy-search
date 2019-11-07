package com.fayelau.tummy.search.core.pojo;

import java.math.BigDecimal;

/**
 * 礼物信息
 * 
 * @author 3g7 2019-10-17 10:16:19
 * @version 0.0.1
 *
 */
public class GiftInfoRO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    protected String id;
    
    protected String name;
    
    protected BigDecimal pc;
    
    protected Integer gx;
    
    protected String himg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPc() {
        return pc;
    }

    public void setPc(BigDecimal pc) {
        this.pc = pc;
    }

    public Integer getGx() {
        return gx;
    }

    public void setGx(Integer gx) {
        this.gx = gx;
    }

    public String getHimg() {
        return himg;
    }

    public void setHimg(String himg) {
        this.himg = himg;
    }
    
}
