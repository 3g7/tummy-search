package com.fayelau.tummy.search.store.mongo.repository;

import java.util.Collection;

import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.store.entity.Newblackres;

/**
 * 黑名单回执实体持久化查询接口
 * 
 * @author 3g7 2019-09-07 12:46:54
 * @version 0.0.1
 *
 */
public interface INewblackresRepository {

    /**
     * 以传入的黑名单回执实体对象作为调节获取黑名单回执实体集合
     * 
     * @param newblackres
     * @return
     * @throws TummyException
     */
    public Collection<Newblackres> search(Newblackres newblackres, String sortProperty, Direction direction)
            throws TummyException;

    /**
     * 分页查询实体集合
     * 
     * @param newblackres
     * @param page
     * @param size
     * @return
     * @throws TummyException
     */
    public Collection<Newblackres> pageableSearch(Newblackres newblackres, Integer page, Integer size,
            String sortProperty, Direction direction) throws TummyException;
    
    /**
     * 查询数据条数根据条件
     * @param newblackres
     * @return
     * @throws TummyException
     */
    public Long count(Newblackres newblackres) throws TummyException;
}
