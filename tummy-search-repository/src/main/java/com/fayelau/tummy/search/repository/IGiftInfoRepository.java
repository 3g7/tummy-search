package com.fayelau.tummy.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fayelau.tummy.search.entity.GiftInfo;

/**
 * 礼物信息持久化Jpa接口
 * 
 * @author 3g7 2019-10-11 16:34:58
 * @version 0.0.1
 *
 */
public interface IGiftInfoRepository extends JpaRepository<GiftInfo, String> {

}
