package com.fayelau.tummy.search.inter.service.store;

import java.util.Collection;

import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.store.mongo.entity.Dgb;

/**
 * 礼物信息业务层接口
 * 
 * @author 3g7 2019-09-09 11:59:22
 * @version 0.0.1
 *
 */
public interface IDgbService {

    /**
     * 以传入的礼物实体对象作为调节获取礼物实体集合
     * 
     * @param dgb
     * @return
     * @throws TummyException
     */
    public Collection<Dgb> search(Dgb dgb, String sortProperty, Direction direction) throws TummyException;

    /**
     * 分页查询实体集合
     * 
     * @param dgb
     * @param page
     * @param size
     * @return
     * @throws TummyException
     */
    public Collection<Dgb> pageableSearch(Dgb dgb, Integer page, Integer size, String sortProperty, Direction direction)
            throws TummyException;
    
    /**
     * 查询数据条数根据条件
     * @param dgb
     * @return
     * @throws TummyException
     */
    public Long count(Dgb dgb) throws TummyException;

}
