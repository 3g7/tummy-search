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
import com.fayelau.tummy.search.entity.GiftInfo;
import com.fayelau.tummy.search.inter.service.business.IGiftInfoFeatureService;

/**
 * 礼物信息
 * 
 * @author 3g7 2019-11-13 11:31:45
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("gift_info/feature")
public class GiftInfoFeatureRest {

    private static final Logger logger = LoggerFactory.getLogger(GiftInfoFeatureRest.class);

    @Autowired
    private IGiftInfoFeatureService giftInfoFeatureService;

    @GetMapping
    public ResponseRange<GiftInfo> getGiftInfoByDouyu(String roomId) {
        if (logger.isDebugEnabled()) {
            logger.debug("run GiftInfoFeatureRest.getGiftInfoByDouyu");
        }
        ResponseRange<GiftInfo> responseRange = new ResponseRange<>();
        try {
            Collection<GiftInfo> giftInfos = this.giftInfoFeatureService.getGiftInfoForDouyu(roomId);
            responseRange.setData(giftInfos);
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
