package com.fayelau.tummy.search.inter.service.business;

import java.util.Collection;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.GiftInfo;

/**
 * 礼物信息功能接口
 * 
 * @author 3g7 2019-10-17 09:56:54
 * @version 0.0.1
 *
 */
public interface IGiftInfoFeatureService {

    /**
     * 用房间ID通过斗鱼API获取礼物信息
     * 
     * @param roomId
     * @return
     * @throws TummyException
     */
    public Collection<GiftInfo> getGiftInfoForDouyu(String roomId) throws TummyException;

}
