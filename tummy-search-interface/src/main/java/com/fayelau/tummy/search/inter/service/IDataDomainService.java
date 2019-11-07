package com.fayelau.tummy.search.inter.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.DataDomain;

/**
 * 数据分域业务层接口
 * 
 * @author 3g7 2019-10-12 15:46:21
 * @version 0.0.1
 *
 */
public interface IDataDomainService {

    /**
     * 增加单个数据分域
     *
     * @param dataDomain
     * @return
     * @throws TummyException
     */
    public DataDomain save(DataDomain dataDomain) throws TummyException;

    /**
     * 批量增加数据分域
     *
     * @param dataDomains
     * @param exists
     * @return
     * @throws TummyException
     */
    public Collection<DataDomain> batchSave(Collection<DataDomain> dataDomains) throws TummyException;

    /**
     * 修改数据分域
     *
     * @param dataDomain
     * @return
     * @throws TummyException
     */
    public DataDomain modify(DataDomain dataDomain) throws TummyException;

    /**
     * 批量修改数据分域集合
     *
     * @param dataDomains
     * @return
     * @throws TummyException
     */
    public Collection<DataDomain> batchModify(Collection<DataDomain> dataDomains) throws TummyException;

    /**
     * 删除数据分域
     *
     * @param dataDomain
     * @return
     * @throws TummyException
     */
    public void remove(DataDomain dataDomain) throws TummyException;

    /**
     * 批量删除数据分域
     *
     * @param dataDomains
     * @return
     * @throws TummyException
     */
    public Collection<DataDomain> batchRemove(Collection<DataDomain> dataDomains) throws TummyException;

    /**
     * 查询数据分域
     *
     * @param dataDomain
     * @return
     * @throws TummyException
     */
    public Collection<DataDomain> search(DataDomain dataDomain) throws TummyException;

    /**
     * 查询数据分域条数
     *
     * @param dataDomain
     * @return
     * @throws TummyException
     */
    public Long count(DataDomain dataDomain) throws TummyException;

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     * @throws TummyException
     */
    public DataDomain getById(String id) throws TummyException;

    /**
     * 分页查询数据分域
     *
     * @param pageable
     * @return
     * @throws TummyException
     */
    public Page<DataDomain> pageableSearch(DataDomain dataDomain, Pageable pageable) throws TummyException;

}
