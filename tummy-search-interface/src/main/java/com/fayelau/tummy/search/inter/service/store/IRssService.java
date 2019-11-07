package com.fayelau.tummy.search.inter.service.store;

import java.util.Collection;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.store.entity.Rss;

/**
 * 开关播业务层接口
 * 
 * @author 3g7 2019-09-09 12:00:35
 * @version 0.0.1
 *
 */
public interface IRssService {

    /**
     * 以传入的开关播提醒实体对象作为调节获取开关播提醒实体集合
     * 
     * @param rss
     * @return
     * @throws TummyException
     */
    public Collection<Rss> search(Rss rss, String sortProperty, String direction) throws TummyException;

    /**
     * 分页查询实体集合
     * 
     * @param rss
     * @param page
     * @param size
     * @return
     * @throws TummyException
     */
    public Collection<Rss> pageableSearch(Rss rss, Integer page, Integer size, String sortProperty, String direction)
            throws TummyException;
    
    /**
     * 查询数据条数根据条件
     * @param rss
     * @return
     * @throws TummyException
     */
    public Long count(Rss rss) throws TummyException;

}
