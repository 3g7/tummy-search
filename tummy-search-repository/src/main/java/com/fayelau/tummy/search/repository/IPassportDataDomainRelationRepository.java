package com.fayelau.tummy.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fayelau.tummy.search.entity.PassportDataDomainRelation;

/**
 * 通行证数据分域关系持久化JPA接口
 * 
 * @author 3g7 2019-10-12 15:44:06
 * @version 0.0.1
 *
 */
public interface IPassportDataDomainRelationRepository extends JpaRepository<PassportDataDomainRelation, String>{

}
