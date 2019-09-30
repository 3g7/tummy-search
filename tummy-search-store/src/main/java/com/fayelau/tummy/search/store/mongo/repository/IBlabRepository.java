package com.fayelau.tummy.search.store.mongo.repository;

import java.util.Collection;

import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.store.mongo.entity.Blab;

/**
 * 粉丝牌升级实体持久化查询接口
 * 
 * @author 3g7 2019-09-07 12:32:17
 * @version 0.0.1
 *
 */
public interface IBlabRepository {

    /**
     * 以传入的粉丝牌升级实体对象作为调节获取粉丝牌升级实体集合
     * 
     * @param blab
     * @return
     * @throws TummyException
     */
    public Collection<Blab> search(Blab blab, String sortProperty, Direction direction) throws TummyException;

    /**
     * 分页查询实体集合
     * 
     * @param blab
     * @param page
     * @param size
     * @return
     * @throws TummyException
     */
    public Collection<Blab> pageableSearch(Blab blab, Integer page, Integer size, String sortProperty,
            Direction direction) throws TummyException;
    
    /**
     * 查询数据条数根据条件
     * @param blab
     * @return
     * @throws TummyException
     */
    public Long count(Blab blab) throws TummyException;

}
