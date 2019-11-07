package com.fayelau.tummy.search.dubbo.inter.store;

import java.util.Collection;

import com.fayelau.tummy.store.entity.Uenter;

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
     * @throws Exception
     */
    public Collection<Uenter> pageableSearch(Uenter uenter, Integer page, Integer size, String sortProperty,
            String direction) throws Exception;

}
