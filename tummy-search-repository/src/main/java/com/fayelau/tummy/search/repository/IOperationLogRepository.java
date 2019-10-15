package com.fayelau.tummy.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fayelau.tummy.search.entity.OperationLog;

/**
 * 操作日志持久化JPA接口
 * 
 * @author 3g7 2019-10-12 15:39:56
 * @version 0.0.1
 *
 */
public interface IOperationLogRepository extends JpaRepository<OperationLog, String> {

}
