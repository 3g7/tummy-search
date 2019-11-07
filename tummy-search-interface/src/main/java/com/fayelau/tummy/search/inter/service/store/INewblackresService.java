package com.fayelau.tummy.search.inter.service.store;

import java.util.Collection;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.store.entity.Newblackres;

/**
 * 黑名单回执业务层接口
 * 
 * @author 3g7 2019-09-09 12:00:08
 * @version 0.0.1
 *
 */
public interface INewblackresService {

    /**
     * 以传入的黑名单回执实体对象作为调节获取黑名单回执实体集合
     * 
     * @param newblackres
     * @return
     * @throws TummyException
     */
    public Collection<Newblackres> search(Newblackres newblackres, String sortProperty, String direction)
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
            String sortProperty, String direction) throws TummyException;
    
    /**
     * 查询数据条数根据条件
     * @param newblackres
     * @return
     * @throws TummyException
     */
    public Long count(Newblackres newblackres) throws TummyException;

}
