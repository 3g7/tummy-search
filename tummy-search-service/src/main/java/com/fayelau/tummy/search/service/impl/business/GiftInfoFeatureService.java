package com.fayelau.tummy.search.service.impl.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.CommonUtils;
import com.fayelau.tummy.search.core.constants.CommonConstants;
import com.fayelau.tummy.search.core.constants.DouyuConstants;
import com.fayelau.tummy.search.core.pojo.DouyuResponseRO;
import com.fayelau.tummy.search.core.pojo.GiftInfoRO;
import com.fayelau.tummy.search.core.utils.BaseSecurity;
import com.fayelau.tummy.search.entity.GiftInfo;
import com.fayelau.tummy.search.inter.service.business.IGiftInfoFeatureService;
import com.fayelau.tummy.search.repository.IGiftInfoRepository;

/**
 * 获取礼物信息
 * 
 * @author 3g7 2019-10-17 09:59:06
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class GiftInfoFeatureService implements IGiftInfoFeatureService {
    
    public static final Logger logger = LoggerFactory.getLogger(GiftInfoFeatureService.class);
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private IGiftInfoRepository giftInfoRepository;

    @Override
    @Transactional(readOnly = false)
    public Collection<GiftInfo> getGiftInfoForDouyu(String roomId) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoFeatureService.getGiftInfoForDouyu");
            logger.debug("The parameter is roomId:" + roomId);
        }
        try {
            if (StringUtils.isEmpty(roomId)) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            ResponseEntity<DouyuResponseRO> douyuResponseRORE = restTemplate.getForEntity(DouyuConstants.ROOM_INFO_API + roomId, DouyuResponseRO.class);
            if (!douyuResponseRORE.getStatusCode().is2xxSuccessful()) {
                throw TummyException.getException(TummyExCode.API_ACCESS_FAIL);
            }
            DouyuResponseRO douyuResponseRO = douyuResponseRORE.getBody();
            if (douyuResponseRO.getError() != 0) {
                throw TummyException.getException(TummyExCode.API_ACCESS_FAIL);
            }
            List<GiftInfo> giftInfos = new ArrayList<>();
            for (GiftInfoRO giftInfoRO: douyuResponseRO.getData().getGift()) {
                GiftInfo giftInfo = new GiftInfo();
                giftInfo.setCode(giftInfoRO.getId());
                Example<GiftInfo> giftInfoExample = Example.of(giftInfo);
                Collection<GiftInfo> giftInfoResults = this.giftInfoRepository.findAll(giftInfoExample);
                if (!giftInfoResults.isEmpty()) {
                    continue;
                }
                giftInfo.setGif(giftInfoRO.getHimg());
                giftInfo.setIntimacy(giftInfoRO.getGx());
                giftInfo.setName(giftInfoRO.getName());
                giftInfo.setPrice(giftInfoRO.getPc());
                giftInfo.setCreateId(BaseSecurity.currentPassportId());
                giftInfo.setCreated(CommonUtils.currentMillis());
                giftInfo.setEnable(CommonConstants.YES);
                giftInfo.setLocking(CommonConstants.NO);
                giftInfos.add(giftInfoRepository.save(giftInfo));
            }
            return giftInfos;
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
