package com.fayelau.tummy.search.rest;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.core.constants.DefaultConstants;
import com.fayelau.tummy.search.entity.GiftInfo;
import com.fayelau.tummy.search.inter.service.IGiftInfoService;

/**
 * 礼物信息请求
 * 
 * @author 3g7 2019-10-11 17:02:01
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("gift_info")
public class GiftInfoRest {

    public static final Logger logger = LoggerFactory.getLogger(GiftInfoRest.class);

    @Autowired
    private IGiftInfoService giftInfoService;

    @PostMapping
    public ResponseRange<GiftInfo> save(GiftInfo giftInfo) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.save");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            if (giftInfo != null) {
                giftInfo = giftInfoService.save(giftInfo);
            }
            responseRange.setOneData(giftInfo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PostMapping(params = { "batch=true" })
    public ResponseRange<GiftInfo> batchSave(Collection<GiftInfo> giftInfos) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.batchSave");
            logger.debug("The parameter is giftInfos:" + giftInfos);
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            if (!giftInfos.isEmpty()) {
                giftInfos = giftInfoService.batchSave(giftInfos);
            }
            responseRange.setData(giftInfos);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping
    public ResponseRange<GiftInfo> modify(GiftInfo giftInfo) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.modify");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            if (giftInfo != null) {
                giftInfo = giftInfoService.modify(giftInfo);
            }
            responseRange.setOneData(giftInfo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping(params = { "batch=true" })
    public ResponseRange<GiftInfo> batchModify(Collection<GiftInfo> giftInfos) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.batchModify");
            logger.debug("The parameter is giftInfos:" + giftInfos);
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            if (giftInfos.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            giftInfos = giftInfoService.batchModify(giftInfos);
            responseRange.setData(giftInfos);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @DeleteMapping
    public ResponseRange<GiftInfo> remove(GiftInfo giftInfo) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.remove");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            if (giftInfo != null) {
                giftInfoService.remove(giftInfo);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @DeleteMapping(params = { "batch=true" })
    public ResponseRange<GiftInfo> batchRemove(@RequestBody List<GiftInfo> giftInfos) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.batchRemove");
            logger.debug("The parameter is giftInfos:" + giftInfos);
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            if (giftInfos.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            giftInfos = (List<GiftInfo>) giftInfoService.batchRemove(giftInfos);
            if (!giftInfos.isEmpty()) {
                responseRange.setData(giftInfos);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @GetMapping
    public ResponseRange<GiftInfo> search(GiftInfo giftInfo) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.search");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            if (giftInfo != null) {
                for (GiftInfo g : giftInfoService.search(giftInfo)) {
                    responseRange.setOneData(g);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @GetMapping(value = { "count" })
    public ResponseRange<Long> count(GiftInfo giftInfo) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.count");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            responseRange.setOneData(giftInfoService.count(giftInfo));
        } catch (Exception e) {
            e.printStackTrace();
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @GetMapping(params = { "pageable=true" })
    public ResponseRange<GiftInfo> search(GiftInfo giftInfo, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoRest.search");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            if (page == null || page == 0)
                page = 0;
            else
                page = page - 1;
            if (size == null)
                size = 20;
            Sort sort = Sort.by(Direction.DESC, DefaultConstants.SYSTEM_SORT_PROPERTY);
            PageRequest pageable = PageRequest.of(page, size, sort);
            if (giftInfo != null) {
                Page<GiftInfo> pageData = giftInfoService.pageableSearch(giftInfo, pageable);
                responseRange.setData(pageData.getContent());
                responseRange.openPage(pageData.getNumber(), size);
                responseRange.setTotal(pageData.getTotalElements());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

}
