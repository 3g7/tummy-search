package com.fayelau.tummy.search.store.mongo.repository;

import java.util.Collection;

import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.store.entity.Chatmsg;
import com.fayelau.tummy.store.pojo.Rank;

/**
 * 弹幕持久化查询接口
 * 
 * @author 3g7 2019-09-07 01:37:34
 * @version 0.0.1
 *
 */
public interface IChatmsgRepository {

    /**
     * 以传入的弹幕实体对象作为调节获取弹幕实体集合
     * 
     * @param chatmsg
     * @return
     * @throws TummyException
     */
    public Collection<Chatmsg> search(Chatmsg chatmsg, String sortProperty, Direction direction) throws TummyException;

    /**
     * 分页查询实体集合
     * 
     * @param chatmsg
     * @param page
     * @param size
     * @return
     * @throws TummyException
     */
    public Collection<Chatmsg> pageableSearch(Chatmsg chatmsg, Integer page, Integer size, String sortProperty,
            Direction direction) throws TummyException;
    
    /**
     * 查询数据条数根据条件
     * @param chatmsg
     * @return
     * @throws TummyException
     */
    public Long count(Chatmsg chatmsg) throws TummyException;
    
    /**
     * 查询数据条数根据条件
     * @param chatmsg
     * @return
     * @throws TummyException
     */
    public Long countByTime(Chatmsg chatmsg, Long start, Long end) throws TummyException;

    /**
     * 根据时间区间查询排行
     * @param start
     * @param end
     * @param limit
     * @return
     * @throws TummyException
     */
    public Collection<Rank> rankByTime(Long start, Long end, Long limit) throws TummyException;
}
