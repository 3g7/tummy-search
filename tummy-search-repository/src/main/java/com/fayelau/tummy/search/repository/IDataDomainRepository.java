package com.fayelau.tummy.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fayelau.tummy.search.entity.DataDomain;

/**
 * 数据分域持久化JPA接口
 * 
 * @author 3g7 2019-10-12 15:42:05
 * @version 0.0.1
 *
 */
public interface IDataDomainRepository extends JpaRepository<DataDomain, String>{

}
