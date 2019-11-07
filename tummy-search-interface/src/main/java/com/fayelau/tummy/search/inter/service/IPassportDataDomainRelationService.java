package com.fayelau.tummy.search.inter.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.PassportDataDomainRelation;

/**
 * 通行证数据分域关系业务层接口
 * 
 * @author 3g7 2019-10-12 15:47:35
 * @version 0.0.1
 *
 */
public interface IPassportDataDomainRelationService {
    
    /**
     * 增加单个通行证数据分域关系
     *
     * @param passportDataDomainRelation
     * @return
     * @throws TummyException
     */
    public PassportDataDomainRelation save(PassportDataDomainRelation passportDataDomainRelation) throws TummyException;

    /**
     * 批量增加通行证数据分域关系
     *
     * @param passportDataDomainRelations
     * @param exists
     * @return
     * @throws TummyException
     */
    public Collection<PassportDataDomainRelation> batchSave(Collection<PassportDataDomainRelation> passportDataDomainRelations) throws TummyException;

    /**
     * 修改通行证数据分域关系
     *
     * @param passportDataDomainRelation
     * @return
     * @throws TummyException
     */
    public PassportDataDomainRelation modify(PassportDataDomainRelation passportDataDomainRelation) throws TummyException;

    /**
     * 批量修改通行证数据分域关系集合
     *
     * @param passportDataDomainRelations
     * @return
     * @throws TummyException
     */
    public Collection<PassportDataDomainRelation> batchModify(Collection<PassportDataDomainRelation> passportDataDomainRelations) throws TummyException;

    /**
     * 删除通行证数据分域关系
     *
     * @param passportDataDomainRelation
     * @return
     * @throws TummyException
     */
    public void remove(PassportDataDomainRelation passportDataDomainRelation) throws TummyException;

    /**
     * 批量删除通行证数据分域关系
     *
     * @param passportDataDomainRelations
     * @return
     * @throws TummyException
     */
    public Collection<PassportDataDomainRelation> batchRemove(Collection<PassportDataDomainRelation> passportDataDomainRelations) throws TummyException;

    /**
     * 查询通行证数据分域关系
     *
     * @param passportDataDomainRelation
     * @return
     * @throws TummyException
     */
    public Collection<PassportDataDomainRelation> search(PassportDataDomainRelation passportDataDomainRelation) throws TummyException;

    /**
     * 查询通行证数据分域关系条数
     *
     * @param passportDataDomainRelation
     * @return
     * @throws TummyException
     */
    public Long count(PassportDataDomainRelation passportDataDomainRelation) throws TummyException;

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     * @throws TummyException
     */
    public PassportDataDomainRelation getById(String id) throws TummyException;

    /**
     * 分页查询通行证数据分域关系
     *
     * @param pageable
     * @return
     * @throws TummyException
     */
    public Page<PassportDataDomainRelation> pageableSearch(PassportDataDomainRelation passportDataDomainRelation, Pageable pageable) throws TummyException;

}
