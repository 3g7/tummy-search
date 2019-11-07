package com.fayelau.tummy.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fayelau.tummy.search.entity.Session;

/**
 * 会话持久化接口
 * 
 * @author 3g7 2019-10-12 09:24:16
 * @version 0.0.1
 *
 */
public interface ISessionRepository extends JpaRepository<Session, String> {

}
