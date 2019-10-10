package com.fayelau.tummy.search.rest.business;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.inter.service.business.IRankService;
import com.fayelau.tummy.store.pojo.Rank;

/**
 * 排行请求接口
 * 
 * @author 3g7 2019-09-30 10:04:38
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("rank")
public class RankRest {
    
    private static final Logger logger = LoggerFactory.getLogger(RankRest.class);

    @Autowired
    private IRankService rankService;
    
    @GetMapping
    public ResponseRange<Rank> rankByTime(Long start, Long end, Long limit) {
        if (logger.isDebugEnabled()) {
            logger.debug("run RankRest.rankByTime");
            logger.debug("params start:" + start);
            logger.debug("params end:" + end);
            logger.debug("params limit:" + limit);
        }
        ResponseRange<Rank> responseRange = new ResponseRange<>();
        try {
            Collection<Rank> ranks = this.rankService.rankByTime(start, end, limit);
            responseRange.setData(ranks);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

}
