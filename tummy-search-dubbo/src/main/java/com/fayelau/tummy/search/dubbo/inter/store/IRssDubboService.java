package com.fayelau.tummy.search.dubbo.inter.store;

import java.util.Collection;

import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.store.mongo.entity.Rss;

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
     * @throws TummyException
     */
    public Collection<Rss> pageableSearch(Rss rss, Integer page, Integer size, String sortProperty, Direction direction)
            throws TummyException;

}
