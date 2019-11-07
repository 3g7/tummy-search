package com.fayelau.tummy.search.inter.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.AnonymousAccess;

/**
 * 匿名访问业务层接口
 * 
 * @author 3g7 2019-10-12 09:25:48
 * @version 0.0.1
 *
 */
public interface IAnonymousAccessService {
    
    /**
     * 增加单个匿名访问
     *
     * @param anonymousAccess
     * @return
     * @throws TummyException
     */
    public AnonymousAccess save(AnonymousAccess anonymousAccess) throws TummyException;

    /**
     * 批量增加匿名访问
     *
     * @param anonymousAccesss
     * @param exists
     * @return
     * @throws TummyException
     */
    public Collection<AnonymousAccess> batchSave(Collection<AnonymousAccess> anonymousAccesss) throws TummyException;

    /**
     * 修改匿名访问
     *
     * @param anonymousAccess
     * @return
     * @throws TummyException
     */
    public AnonymousAccess modify(AnonymousAccess anonymousAccess) throws TummyException;

    /**
     * 批量修改匿名访问集合
     *
     * @param anonymousAccesss
     * @return
     * @throws TummyException
     */
    public Collection<AnonymousAccess> batchModify(Collection<AnonymousAccess> anonymousAccesss) throws TummyException;

    /**
     * 删除匿名访问
     *
     * @param anonymousAccess
     * @return
     * @throws TummyException
     */
    public void remove(AnonymousAccess anonymousAccess) throws TummyException;

    /**
     * 批量删除匿名访问
     *
     * @param anonymousAccesss
     * @return
     * @throws TummyException
     */
    public Collection<AnonymousAccess> batchRemove(Collection<AnonymousAccess> anonymousAccesss) throws TummyException;

    /**
     * 查询匿名访问
     *
     * @param anonymousAccess
     * @return
     * @throws TummyException
     */
    public Collection<AnonymousAccess> search(AnonymousAccess anonymousAccess) throws TummyException;

    /**
     * 查询匿名访问条数
     *
     * @param anonymousAccess
     * @return
     * @throws TummyException
     */
    public Long count(AnonymousAccess anonymousAccess) throws TummyException;

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     * @throws TummyException
     */
    public AnonymousAccess getById(String id) throws TummyException;

    /**
     * 分页查询匿名访问
     *
     * @param pageable
     * @return
     * @throws TummyException
     */
    public Page<AnonymousAccess> pageableSearch(AnonymousAccess anonymousAccess, Pageable pageable) throws TummyException;

}
