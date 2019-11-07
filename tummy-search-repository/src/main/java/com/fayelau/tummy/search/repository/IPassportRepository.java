package com.fayelau.tummy.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fayelau.tummy.search.entity.Passport;

/**
 * 通行证持久化JPA接口
 * 
 * @author 3g7 2019-10-12 09:23:36
 * @version 0.0.1
 *
 */
public interface IPassportRepository extends JpaRepository<Passport, String> {

}
