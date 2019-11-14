package com.fayelau.tummy.search.inter.service.business;

import java.util.Collection;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.secretary.api.entry.GroupMessageEntity;

/**
 * 粉丝群信息接口
 * 
 * @author 3g7 2019-11-13 10:42:24
 * @version 0.0.1
 *
 */
public interface IFansGroupService {
    
    /**
     * 获取QQ群信息
     * @param groupId
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws TummyException
     */
    public Collection<GroupMessageEntity> search(Long groupId, Long userId, int pageIndex, int pageSize) throws TummyException;

}
