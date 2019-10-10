package com.fayelau.tummy.search.dubbo.inter.store;

import java.util.Collection;

import com.fayelau.tummy.store.entity.Blab;

/**
 * 粉丝牌升级信息业务层接口
 * 
 * @author 3g7 2019-09-09 11:57:55
 * @version 0.0.1
 *
 */
public interface IBlabDubboService {
    
    /**
     * 分页查询实体集合
     * 
     * @param blab
     * @param page
     * @param size
     * @return
     * @throws TummyException
     */
    public Collection<Blab> pageableSearch(Blab blab, Integer page, Integer size, String sortProperty,
            String direction) throws Exception;

}
