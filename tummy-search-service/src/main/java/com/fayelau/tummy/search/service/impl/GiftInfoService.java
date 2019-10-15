package com.fayelau.tummy.search.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.CommonUtils;
import com.fayelau.tummy.search.core.constants.CommonConstants;
import com.fayelau.tummy.search.core.utils.BaseSecurity;
import com.fayelau.tummy.search.entity.GiftInfo;
import com.fayelau.tummy.search.inter.service.IGiftInfoService;
import com.fayelau.tummy.search.repository.IGiftInfoRepository;

/**
 * 礼物信息业务层实现
 * 
 * @author 3g7 2019-10-11 16:52:11
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class GiftInfoService implements IGiftInfoService {

    public static final Logger logger = LoggerFactory.getLogger(GiftInfoService.class);

    @Autowired
    private IGiftInfoRepository giftInfoRepository;

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public GiftInfo save(GiftInfo giftInfo) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.save");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        try {
            giftInfo.setCreated(CommonUtils.currentMillis());
            giftInfo.setCreateId(BaseSecurity.currentPassportId());
            giftInfo.setEnable(CommonConstants.YES);
            giftInfo.setLocking(CommonConstants.NO);
            return giftInfoRepository.save(giftInfo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public Collection<GiftInfo> batchSave(Collection<GiftInfo> giftInfos) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.batchSave");
            logger.debug("The parameter is giftInfos:" + giftInfos);
        }
        try {
            List<GiftInfo> saved = new ArrayList<>();
            for (GiftInfo giftInfo: giftInfos) {
                saved.add(this.save(giftInfo));
            }
            return saved;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public GiftInfo modify(GiftInfo giftInfo) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.modify");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        try {
            Optional<GiftInfo> old = giftInfoRepository.findById(giftInfo.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            giftInfo.setModified(CommonUtils.currentMillis());
            giftInfo.setModifyId(BaseSecurity.currentPassportId());
            return giftInfoRepository.save(giftInfo);
        } catch (ObjectOptimisticLockingFailureException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(TummyExCode.DATA_VERSION_ERROR);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public Collection<GiftInfo> batchModify(Collection<GiftInfo> giftInfos) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.batchModify");
            logger.debug("The parameter is giftInfos:" + giftInfos);
        }
        try {
            List<GiftInfo> modified = new ArrayList<>();
            for (GiftInfo giftInfo: giftInfos) {
                modified.add(this.save(giftInfo));
            }
            return modified;
        } catch (ObjectOptimisticLockingFailureException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(TummyExCode.DATA_VERSION_ERROR);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public void remove(GiftInfo giftInfo) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.remove");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        try {
            Optional<GiftInfo> old = giftInfoRepository.findById(giftInfo.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            giftInfoRepository.delete(giftInfo);
        } catch (ObjectOptimisticLockingFailureException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(TummyExCode.DATA_VERSION_ERROR);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public Collection<GiftInfo> batchRemove(Collection<GiftInfo> giftInfos) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.batchRemove");
            logger.debug("The parameter is giftInfos:" + giftInfos);
        }
        List<GiftInfo> deletes = new LinkedList<>();
        List<GiftInfo> unExists = new LinkedList<>();
        try {
            for (GiftInfo giftInfo : giftInfos) {
                Optional<GiftInfo> old = giftInfoRepository.findById(giftInfo.getId());
                if (!old.isPresent()) {
                    unExists.add(giftInfo);
                    continue;
                }
                deletes.add(giftInfo);
            }
            giftInfoRepository.deleteAll(deletes);
            return unExists;
        } catch (ObjectOptimisticLockingFailureException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(TummyExCode.DATA_VERSION_ERROR);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    /**
     * {@Inherited}
     */
    @Override
    public Collection<GiftInfo> search(GiftInfo giftInfo) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.search");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        try {
            Example<GiftInfo> example = Example.of(giftInfo);
            return giftInfoRepository.findAll(example);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    /**
     * {@Inherited}
     */
    @Override
    public Long count(GiftInfo giftInfo) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.count");
            logger.debug("The parameter is giftInfo:" + giftInfo);
        }
        try {
            Example<GiftInfo> example = Example.of(giftInfo);
            return giftInfoRepository.count(example);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public GiftInfo getById(String id) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.getById");
            logger.debug("The parameter is id:" + id);
        }
        try {
            return giftInfoRepository.findById(id).get();
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    /**
     * {@Inherited}
     */
    @Override
    public Page<GiftInfo> pageableSearch(GiftInfo giftInfo, Pageable pageable) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function GiftInfoService.pageableSearch");
            logger.debug("The parameter is giftInfo:" + giftInfo);
            logger.debug("The parameter is pageable:" + pageable);
        }
        try {
            Example<GiftInfo> example = Example.of(giftInfo);
            return giftInfoRepository.findAll(example, pageable);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
