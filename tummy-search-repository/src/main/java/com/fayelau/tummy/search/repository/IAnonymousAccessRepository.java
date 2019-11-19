package com.fayelau.tummy.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fayelau.tummy.search.entity.AnonymousAccess;

/**
 * 匿名访问持久化JPA接口
 * 
 * @author 3g7 2019-10-12 09:22:43
 * @version 0.0.1
 *
 */
public interface IAnonymousAccessRepository extends JpaRepository<AnonymousAccess, String> {

}
