package com.fayelau.tummy.search.dubbo.inter.business;

import java.util.Collection;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.store.mongo.pojo.Rank;

/**
 * Dubbo 排行相关接口
 * 
 * @author 3g7 2019-09-30 10:54:29
 * @version 0.0.1
 *
 */
public interface IRankDubboService {

    /**
     * 根据时间区间获取排行榜
     * 
     * @param start
     * @param end
     * @param limit
     * @return
     * @throws TummyException
     */
    public Collection<Rank> rankByTime(Long start, Long end, Long limit) throws TummyException;

}
