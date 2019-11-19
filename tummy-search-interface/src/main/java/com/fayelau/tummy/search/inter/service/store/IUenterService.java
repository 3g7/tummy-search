package com.fayelau.tummy.search.inter.service.store;

import java.util.Collection;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.store.entity.Uenter;

/**
 * 入场信息业务层接口
 * 
 * @author 3g7 2019-09-09 12:01:07
 * @version 0.0.1
 *
 */
public interface IUenterService {

    /**
     * 以传入的入场实体对象作为调节获取入场实体集合
     * 
     * @param uenter
     * @return
     * @throws TummyException
     */
    public Collection<Uenter> search(Uenter uenter, String sortProperty, String direction) throws TummyException;

    /**
     * 分页查询实体集合
     * 
     * @param uenter
     * @param page
     * @param size
     * @return
     * @throws TummyException
     */
    public Collection<Uenter> pageableSearch(Uenter uenter, Integer page, Integer size, String sortProperty,
            String direction) throws TummyException;
    
    /**
     * 查询数据条数根据条件
     * @param uenter
     * @return
     * @throws TummyException
     */
    public Long count(Uenter uenter) throws TummyException;

}
