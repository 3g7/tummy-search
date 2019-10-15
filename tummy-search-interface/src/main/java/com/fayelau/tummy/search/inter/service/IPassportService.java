package com.fayelau.tummy.search.inter.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.Passport;

/**
 * 通行证业务层接口
 * 
 * @author 3g7 2019-10-12 09:27:00
 * @version 0.0.1
 *
 */
public interface IPassportService {
    
    /**
     * 增加单个通行证
     *
     * @param passport
     * @return
     * @throws TummyException
     */
    public Passport save(Passport passport) throws TummyException;

    /**
     * 批量增加通行证
     *
     * @param passports
     * @param exists
     * @return
     * @throws TummyException
     */
    public Collection<Passport> batchSave(Collection<Passport> passports) throws TummyException;

    /**
     * 修改通行证
     *
     * @param passport
     * @return
     * @throws TummyException
     */
    public Passport modify(Passport passport) throws TummyException;

    /**
     * 批量修改通行证集合
     *
     * @param passports
     * @return
     * @throws TummyException
     */
    public Collection<Passport> batchModify(Collection<Passport> passports) throws TummyException;

    /**
     * 删除通行证
     *
     * @param passport
     * @return
     * @throws TummyException
     */
    public void remove(Passport passport) throws TummyException;

    /**
     * 批量删除通行证
     *
     * @param passports
     * @return
     * @throws TummyException
     */
    public Collection<Passport> batchRemove(Collection<Passport> passports) throws TummyException;

    /**
     * 查询通行证
     *
     * @param passport
     * @return
     * @throws TummyException
     */
    public Collection<Passport> search(Passport passport) throws TummyException;

    /**
     * 查询通行证条数
     *
     * @param passport
     * @return
     * @throws TummyException
     */
    public Long count(Passport passport) throws TummyException;

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     * @throws TummyException
     */
    public Passport getById(String id) throws TummyException;

    /**
     * 分页查询通行证
     *
     * @param pageable
     * @return
     * @throws TummyException
     */
    public Page<Passport> pageableSearch(Passport passport, Pageable pageable) throws TummyException;

}
