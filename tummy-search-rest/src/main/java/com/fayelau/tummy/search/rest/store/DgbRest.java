package com.fayelau.tummy.search.rest.store;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
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
import com.fayelau.tummy.search.inter.service.store.IDgbService;
import com.fayelau.tummy.store.entity.Dgb;

/**
 * 礼物消息请求
 * 
 * @author 3g7 2019-09-09 14:06:49
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("dgb")
public class DgbRest {

    private static final Logger logger = LoggerFactory.getLogger(DgbRest.class);

    @Autowired
    private IDgbService dgbService;

    @GetMapping
    public ResponseRange<Dgb> search(Dgb dgb, String sortProperty, String direction) {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbRest.search");
            logger.debug("params dgb:" + dgb);
        }
        ResponseRange<Dgb> responseRange = new ResponseRange<>();
        try {
            if (StringUtils.isEmpty(sortProperty)) {
                sortProperty = DefaultConstants.DEFAULT_SORT_PROPERTY;
            }
            if (StringUtils.isEmpty(direction)) {
                direction = CommonConstants.DIRECTION_DESC;
            }
            Collection<Dgb> dgbs = this.dgbService.search(dgb, sortProperty, direction);
            responseRange.setData(dgbs);
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
    public ResponseRange<Dgb> pageableSearch(Dgb dgb, Integer page, Integer size, String sortProperty,
            String direction) {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbRest.pageableSearch");
            logger.debug("params dgb:" + dgb);
        }
        ResponseRange<Dgb> responseRange = new ResponseRange<>();
        try {
            if (page == null) {
                page = 0;
            }
            if (size == null) {
                size = 20;
            }
            responseRange.openPage(page, size);
            if (StringUtils.isEmpty(sortProperty)) {
                sortProperty = DefaultConstants.DEFAULT_SORT_PROPERTY;
            }
            if (StringUtils.isEmpty(direction)) {
                direction = CommonConstants.DIRECTION_DESC;
            }
            Collection<Dgb> dgbs = this.dgbService.pageableSearch(dgb, page, size, sortProperty, direction);
            responseRange.setData(dgbs);
            responseRange.setTotal(this.dgbService.count(dgb));
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
    public ResponseRange<Long> count(Dgb dgb) {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbRest.count");
            logger.debug("params dgb:" + dgb);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            Long count = this.dgbService.count(dgb);
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
