package com.fayelau.tummy.search.dubbo.inter.store;

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
public interface IDgbDubboService {

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

}
