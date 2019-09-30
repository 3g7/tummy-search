package com.fayelau.tummy.search.rest.store;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.core.constants.TummySearchDefaultConstants;
import com.fayelau.tummy.search.inter.service.store.IUenterService;
import com.fayelau.tummy.search.store.mongo.entity.Uenter;

/**
 * 入场消息请求
 * 
 * @author 3g7 2019-09-09 14:10:22
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("uenter")
public class UenterRest {
    
    private static final Logger logger = LoggerFactory.getLogger(UenterRest.class);

    @Autowired
    private IUenterService uenterService;

    @GetMapping
    public ResponseRange<Uenter> search(Uenter uenter) {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterRest.search");
            logger.debug("params uenter:" + uenter);
        }
        ResponseRange<Uenter> responseRange = new ResponseRange<>();
        try {
            Collection<Uenter> uenters = this.uenterService.search(uenter,
                    TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY, Direction.DESC);
            responseRange.setData(uenters);
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
    public ResponseRange<Uenter> pageableSearch(Uenter uenter, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterRest.pageableSearch");
            logger.debug("params uenter:" + uenter);
        }
        ResponseRange<Uenter> responseRange = new ResponseRange<>();
        try {
            if (page == null) {
                page = 0;
            }
            if (size == null) {
                size = 20;
            }
            responseRange.openPage(page, size);
            Collection<Uenter> uenters = this.uenterService.pageableSearch(uenter, page, size,
                    TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY, Direction.DESC);
            responseRange.setData(uenters);
            responseRange.setTotal(this.uenterService.count(uenter));
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
    public ResponseRange<Long> count(Uenter uenter) {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterRest.search");
            logger.debug("params uenter:" + uenter);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            Long count = this.uenterService.count(uenter);
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
