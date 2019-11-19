package com.fayelau.tummy.search.inter.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.OperationLog;

/**
 * 操作日志业务层接口
 * 
 * @author 3g7 2019-10-12 15:46:36
 * @version 0.0.1
 *
 */
public interface IOperationLogService {

    /**
     * 增加单个操作日志
     *
     * @param operationLog
     * @return
     * @throws TummyException
     */
    public OperationLog save(OperationLog operationLog) throws TummyException;

    /**
     * 批量增加操作日志
     *
     * @param operationLogs
     * @param exists
     * @return
     * @throws TummyException
     */
    public Collection<OperationLog> batchSave(Collection<OperationLog> operationLogs) throws TummyException;

    /**
     * 修改操作日志
     *
     * @param operationLog
     * @return
     * @throws TummyException
     */
    public OperationLog modify(OperationLog operationLog) throws TummyException;

    /**
     * 批量修改操作日志集合
     *
     * @param operationLogs
     * @return
     * @throws TummyException
     */
    public Collection<OperationLog> batchModify(Collection<OperationLog> operationLogs) throws TummyException;

    /**
     * 删除操作日志
     *
     * @param operationLog
     * @return
     * @throws TummyException
     */
    public void remove(OperationLog operationLog) throws TummyException;

    /**
     * 批量删除操作日志
     *
     * @param operationLogs
     * @return
     * @throws TummyException
     */
    public Collection<OperationLog> batchRemove(Collection<OperationLog> operationLogs) throws TummyException;

    /**
     * 查询操作日志
     *
     * @param operationLog
     * @return
     * @throws TummyException
     */
    public Collection<OperationLog> search(OperationLog operationLog) throws TummyException;

    /**
     * 查询操作日志条数
     *
     * @param operationLog
     * @return
     * @throws TummyException
     */
    public Long count(OperationLog operationLog) throws TummyException;

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     * @throws TummyException
     */
    public OperationLog getById(String id) throws TummyException;

    /**
     * 分页查询操作日志
     *
     * @param pageable
     * @return
     * @throws TummyException
     */
    public Page<OperationLog> pageableSearch(OperationLog operationLog, Pageable pageable)
            throws TummyException;

}
