package com.fayelau.tummy.search.dubbo.inter.store;

import java.util.Collection;

import com.fayelau.tummy.store.entity.Rss;

/**
 * 开关播业务层接口
 * 
 * @author 3g7 2019-09-09 12:00:35
 * @version 0.0.1
 *
 */
public interface IRssDubboService {

    /**
     * 分页查询实体集合
     * 
     * @param rss
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Collection<Rss> pageableSearch(Rss rss, Integer page, Integer size, String sortProperty, String direction)
            throws Exception;

}
