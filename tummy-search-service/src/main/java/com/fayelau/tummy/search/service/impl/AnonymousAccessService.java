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
import com.fayelau.tummy.search.entity.AnonymousAccess;
import com.fayelau.tummy.search.inter.service.IAnonymousAccessService;
import com.fayelau.tummy.search.repository.IAnonymousAccessRepository;

/**
 * 匿名访问业务层实现
 * 
 * @author 3g7 2019-10-12 09:29:34
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class AnonymousAccessService implements IAnonymousAccessService {

    public static final Logger logger = LoggerFactory.getLogger(AnonymousAccessService.class);

    @Autowired
    private IAnonymousAccessRepository anonymousAccessRepository;

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public AnonymousAccess save(AnonymousAccess anonymousAccess) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.save");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        try {
            anonymousAccess.setCreated(CommonUtils.currentMillis());
            anonymousAccess.setCreateId(BaseSecurity.currentPassportId());
            anonymousAccess.setEnable(CommonConstants.YES);
            anonymousAccess.setLocking(CommonConstants.NO);
            return anonymousAccessRepository.save(anonymousAccess);
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
    public Collection<AnonymousAccess> batchSave(Collection<AnonymousAccess> anonymousAccesses) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.batchSave");
            logger.debug("The parameter is anonymousAccesses:" + anonymousAccesses);
        }
        try {
            List<AnonymousAccess> saved = new ArrayList<>();
            for (AnonymousAccess anonymousAccess: anonymousAccesses) {
                saved.add(this.save(anonymousAccess));
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
    public AnonymousAccess modify(AnonymousAccess anonymousAccess) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.modify");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        try {
            Optional<AnonymousAccess> old = anonymousAccessRepository.findById(anonymousAccess.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            anonymousAccess.setModified(CommonUtils.currentMillis());
            anonymousAccess.setModifyId(BaseSecurity.currentPassportId());
            return anonymousAccessRepository.save(anonymousAccess);
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
    public Collection<AnonymousAccess> batchModify(Collection<AnonymousAccess> anonymousAccesses) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.batchModify");
            logger.debug("The parameter is anonymousAccesses:" + anonymousAccesses);
        }
        try {
            List<AnonymousAccess> modified = new ArrayList<>();
            for (AnonymousAccess anonymousAccess: anonymousAccesses) {
                modified.add(this.save(anonymousAccess));
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
    public void remove(AnonymousAccess anonymousAccess) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.remove");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        try {
            Optional<AnonymousAccess> old = anonymousAccessRepository.findById(anonymousAccess.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            anonymousAccessRepository.delete(anonymousAccess);
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
    public Collection<AnonymousAccess> batchRemove(Collection<AnonymousAccess> anonymousAccesss) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.batchRemove");
            logger.debug("The parameter is anonymousAccesss:" + anonymousAccesss);
        }
        List<AnonymousAccess> deletes = new LinkedList<>();
        List<AnonymousAccess> unExists = new LinkedList<>();
        try {
            for (AnonymousAccess anonymousAccess : anonymousAccesss) {
                Optional<AnonymousAccess> old = anonymousAccessRepository.findById(anonymousAccess.getId());
                if (!old.isPresent()) {
                    unExists.add(anonymousAccess);
                    continue;
                }
                deletes.add(anonymousAccess);
            }
            anonymousAccessRepository.deleteAll(deletes);
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
    public Collection<AnonymousAccess> search(AnonymousAccess anonymousAccess) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.search");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        try {
            Example<AnonymousAccess> example = Example.of(anonymousAccess);
            return anonymousAccessRepository.findAll(example);
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
    public Long count(AnonymousAccess anonymousAccess) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.count");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        try {
            Example<AnonymousAccess> example = Example.of(anonymousAccess);
            return anonymousAccessRepository.count(example);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public AnonymousAccess getById(String id) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.getById");
            logger.debug("The parameter is id:" + id);
        }
        try {
            return anonymousAccessRepository.findById(id).get();
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
    public Page<AnonymousAccess> pageableSearch(AnonymousAccess anonymousAccess, Pageable pageable)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessService.pageableSearch");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
            logger.debug("The parameter is pageable:" + pageable);
        }
        try {
            Example<AnonymousAccess> example = Example.of(anonymousAccess);
            
            return anonymousAccessRepository.findAll(example, pageable);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
