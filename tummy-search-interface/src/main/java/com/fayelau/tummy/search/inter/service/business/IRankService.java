package com.fayelau.tummy.search.inter.service.business;

import java.util.Collection;
import java.util.Map;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.store.pojo.Rank;

/**
 * 排行相关接口
 * 
 * @author 3g7 2019-09-30 09:53:10
 * @version 0.0.1
 *
 */
public interface IRankService {

    /**
     * 根据时间区间获取排行榜
     * 
     * @param start
     * @param end
     * @param limit
     * @return
     * @throws TummyException
     */
    public Collection<Rank> rankByTime(Long start, Long end, Long limit, Map<String, Object> domainParams) throws TummyException;

}
