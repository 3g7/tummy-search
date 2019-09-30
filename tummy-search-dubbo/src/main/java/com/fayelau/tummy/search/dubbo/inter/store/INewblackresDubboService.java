package com.fayelau.tummy.search.dubbo.inter.store;

import java.util.Collection;

import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.store.mongo.entity.Newblackres;

/**
 * 黑名单回执业务层接口
 * 
 * @author 3g7 2019-09-09 12:00:08
 * @version 0.0.1
 *
 */
public interface INewblackresDubboService {

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

}
