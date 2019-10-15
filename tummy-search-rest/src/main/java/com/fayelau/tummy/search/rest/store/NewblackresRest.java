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
import com.fayelau.tummy.search.inter.service.store.INewblackresService;
import com.fayelau.tummy.store.entity.Newblackres;

/**
 * 黑名单回执请求
 * 
 * @author 3g7 2019-09-09 14:07:49
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("newblackres")
public class NewblackresRest {
    
    private static final Logger logger = LoggerFactory.getLogger(NewblackresRest.class);

    @Autowired
    private INewblackresService newblackresService;

    @GetMapping
    public ResponseRange<Newblackres> search(Newblackres newblackres) {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresRest.search");
            logger.debug("params newblackres:" + newblackres);
        }
        ResponseRange<Newblackres> responseRange = new ResponseRange<>();
        try {
            Collection<Newblackres> newblackreses = this.newblackresService.search(newblackres,
                    DefaultConstants.DEFAULT_SORT_PROPERTY, CommonConstants.DIRECTION_DESC);
            responseRange.setData(newblackreses);
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
    public ResponseRange<Newblackres> pageableSearch(Newblackres newblackres, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresRest.pageableSearch");
            logger.debug("params newblackres:" + newblackres);
        }
        ResponseRange<Newblackres> responseRange = new ResponseRange<>();
        try {
            if (page == null) {
                page = 0;
            }
            if (size == null) {
                size = 20;
            }
            responseRange.openPage(page, size);
            Collection<Newblackres> newblackreses = this.newblackresService.pageableSearch(newblackres, page, size,
                    DefaultConstants.DEFAULT_SORT_PROPERTY, CommonConstants.DIRECTION_DESC);
            responseRange.setData(newblackreses);
            responseRange.setTotal(this.newblackresService.count(newblackres));
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
    public ResponseRange<Long> count(Newblackres newblackres) {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresRest.search");
            logger.debug("params newblackres:" + newblackres);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            Long count = this.newblackresService.count(newblackres);
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
