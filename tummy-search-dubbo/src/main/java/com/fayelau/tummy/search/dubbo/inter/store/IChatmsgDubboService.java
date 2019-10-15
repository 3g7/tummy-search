package com.fayelau.tummy.search.dubbo.inter.store;

import java.util.Collection;

import com.fayelau.tummy.store.entity.Chatmsg;

/**
 * 弹幕信息业务层接口
 * 
 * @author 3g7 2019-09-09 11:58:33
 * @version 0.0.1
 *
 */
public interface IChatmsgDubboService {

    /**
     * 分页查询实体集合
     * 
     * @param chatmsg
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Collection<Chatmsg> pageableSearch(Chatmsg chatmsg, Integer page, Integer size, String sortProperty,
            String direction) throws Exception;

    /**
     * 查询数据条数根据条件
     * 
     * @param chatmsg
     * @return
     * @throws Exception
     */
    public Long count(Chatmsg chatmsg) throws Exception;

}
