package com.fayelau.tummy.search.inter.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.Session;

/**
 * 会话业务层接口
 * 
 * @author 3g7 2019-10-12 09:27:43
 * @version 0.0.1
 *
 */
public interface ISessionService {
    
    /**
     * 增加单个会话信息
     *
     * @param session
     * @return
     * @throws TummyException
     */
    public Session save(Session session) throws TummyException;

    /**
     * 批量增加会话信息
     *
     * @param sessions
     * @param exists
     * @return
     * @throws TummyException
     */
    public Collection<Session> batchSave(Collection<Session> sessions) throws TummyException;

    /**
     * 修改会话信息
     *
     * @param session
     * @return
     * @throws TummyException
     */
    public Session modify(Session session) throws TummyException;

    /**
     * 批量修改会话信息集合
     *
     * @param sessions
     * @return
     * @throws TummyException
     */
    public Collection<Session> batchModify(Collection<Session> sessions) throws TummyException;

    /**
     * 删除会话信息
     *
     * @param session
     * @return
     * @throws TummyException
     */
    public void remove(Session session) throws TummyException;

    /**
     * 批量删除会话信息
     *
     * @param sessions
     * @return
     * @throws TummyException
     */
    public Collection<Session> batchRemove(Collection<Session> sessions) throws TummyException;

    /**
     * 查询会话信息
     *
     * @param session
     * @return
     * @throws TummyException
     */
    public Collection<Session> search(Session session) throws TummyException;

    /**
     * 查询会话信息条数
     *
     * @param session
     * @return
     * @throws TummyException
     */
    public Long count(Session session) throws TummyException;

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     * @throws TummyException
     */
    public Session getById(String id) throws TummyException;

    /**
     * 分页查询会话信息
     *
     * @param pageable
     * @return
     * @throws TummyException
     */
    public Page<Session> pageableSearch(Session session, Pageable pageable) throws TummyException;

}
