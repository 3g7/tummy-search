package com.fayelau.tummy.search.dubbo.inter.store;

import java.util.Collection;

import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.store.mongo.entity.Uenter;

/**
 * 入场信息业务层接口
 * 
 * @author 3g7 2019-09-09 12:01:07
 * @version 0.0.1
 *
 */
public interface IUenterDubboService {

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
            Direction direction) throws TummyException;

}
