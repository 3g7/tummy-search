package com.fayelau.tummy.search.rest.store;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.core.constants.CommonConstants;
import com.fayelau.tummy.search.core.constants.DefaultConstants;
import com.fayelau.tummy.search.inter.service.store.IRssService;
import com.fayelau.tummy.store.entity.Rss;

/**
 * 开关播提醒请求
 * 
 * @author 3g7 2019-09-09 14:08:56
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("rss")
public class RssRest {
    
    private static final Logger logger = LoggerFactory.getLogger(RssRest.class);

    @Autowired
    private IRssService rssService;

    @GetMapping
    public ResponseRange<Rss> search(Rss rss) {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssRest.search");
            logger.debug("params rss:" + rss);
        }
        ResponseRange<Rss> responseRange = new ResponseRange<>();
        try {
            Collection<Rss> rsses = this.rssService.search(rss,
                    DefaultConstants.DEFAULT_SORT_PROPERTY, CommonConstants.DIRECTION_DESC);
            responseRange.setData(rsses);
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

    @GetMapping(params = { "pageable=true" })
    public ResponseRange<Rss> pageableSearch(Rss rss, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssRest.pageableSearch");
            logger.debug("params rss:" + rss);
        }
        ResponseRange<Rss> responseRange = new ResponseRange<>();
        try {
            if (page == null) {
                page = 0;
            }
            if (size == null) {
                size = 20;
            }
            responseRange.openPage(page, size);
            Collection<Rss> rsses = this.rssService.pageableSearch(rss, page, size,
                    DefaultConstants.DEFAULT_SORT_PROPERTY, CommonConstants.DIRECTION_DESC);
            responseRange.setData(rsses);
            responseRange.setTotal(this.rssService.count(rss));
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
    
    @GetMapping("count")
    public ResponseRange<Long> count(Rss rss) {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssRest.search");
            logger.debug("params rss:" + rss);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            Long count = this.rssService.count(rss);
            responseRange.setOneData(count);
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
