package com.fayelau.tummy.search.inter.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.GiftInfo;

/**
 * 礼物信息业务层接口
 * 
 * @author 3g7 2019-10-11 16:38:16
 * @version 0.0.1
 *
 */
public interface IGiftInfoService {
    
    /**
     * 增加单个礼物信息
     *
     * @param giftInfo
     * @return
     * @throws TummyException
     */
    public GiftInfo save(GiftInfo giftInfo) throws TummyException;

    /**
     * 批量增加礼物信息
     *
     * @param giftInfos
     * @param exists
     * @return
     * @throws TummyException
     */
    public Collection<GiftInfo> batchSave(Collection<GiftInfo> giftInfos) throws TummyException;

    /**
     * 修改礼物信息
     *
     * @param giftInfo
     * @return
     * @throws TummyException
     */
    public GiftInfo modify(GiftInfo giftInfo) throws TummyException;

    /**
     * 批量修改礼物信息集合
     *
     * @param giftInfos
     * @return
     * @throws TummyException
     */
    public Collection<GiftInfo> batchModify(Collection<GiftInfo> giftInfos) throws TummyException;

    /**
     * 删除礼物信息
     *
     * @param giftInfo
     * @return
     * @throws TummyException
     */
    public void remove(GiftInfo giftInfo) throws TummyException;

    /**
     * 批量删除礼物信息
     *
     * @param giftInfos
     * @return
     * @throws TummyException
     */
    public Collection<GiftInfo> batchRemove(Collection<GiftInfo> giftInfos) throws TummyException;

    /**
     * 查询礼物信息
     *
     * @param giftInfo
     * @return
     * @throws TummyException
     */
    public Collection<GiftInfo> search(GiftInfo giftInfo) throws TummyException;

    /**
     * 查询礼物信息条数
     *
     * @param giftInfo
     * @return
     * @throws TummyException
     */
    public Long count(GiftInfo giftInfo) throws TummyException;

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     * @throws TummyException
     */
    public GiftInfo getById(String id) throws TummyException;

    /**
     * 分页查询礼物信息
     *
     * @param pageable
     * @return
     * @throws TummyException
     */
    public Page<GiftInfo> pageableSearch(GiftInfo giftInfo, Pageable pageable) throws TummyException;

}
