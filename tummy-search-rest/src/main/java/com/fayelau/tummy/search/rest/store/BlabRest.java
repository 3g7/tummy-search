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
import com.fayelau.tummy.search.inter.service.store.IBlabService;
import com.fayelau.tummy.search.store.mongo.entity.Blab;

/**
 * 粉丝牌升级信息请求
 * 
 * @author 3g7 2019-09-09 13:06:15
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("blab")
public class BlabRest {

    private static final Logger logger = LoggerFactory.getLogger(BlabRest.class);

    @Autowired
    private IBlabService blabService;

    @GetMapping
    public ResponseRange<Blab> search(Blab blab) {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabRest.search");
            logger.debug("params blab:" + blab);
        }
        ResponseRange<Blab> responseRange = new ResponseRange<>();
        try {
            Collection<Blab> blabs = this.blabService.search(blab, TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY,
                    Direction.DESC);
            responseRange.setData(blabs);
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
    public ResponseRange<Blab> pageableSearch(Blab blab, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabRest.pageableSearch");
            logger.debug("params blab:" + blab);
        }
        ResponseRange<Blab> responseRange = new ResponseRange<>();
        try {
            if (page == null) {
                page = 0;
            }
            if (size == null) {
                size = 20;
            }
            responseRange.openPage(page, size);
            Collection<Blab> blabs = this.blabService.pageableSearch(blab, page, size,
                    TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY, Direction.DESC);
            responseRange.setData(blabs);
            responseRange.setTotal(this.blabService.count(blab));
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
    public ResponseRange<Long> count(Blab blab) {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabRest.count");
            logger.debug("params blab:" + blab);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            Long count = this.blabService.count(blab);
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
