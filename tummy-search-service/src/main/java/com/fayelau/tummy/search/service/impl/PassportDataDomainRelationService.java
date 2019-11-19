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
import com.fayelau.tummy.search.entity.PassportDataDomainRelation;
import com.fayelau.tummy.search.inter.service.IPassportDataDomainRelationService;
import com.fayelau.tummy.search.repository.IPassportDataDomainRelationRepository;

/**
 * 通行证数据分域关系业务层实现
 * 
 * @author 3g7 2019-10-12 15:52:37
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class PassportDataDomainRelationService implements IPassportDataDomainRelationService {
    
    public static final Logger logger = LoggerFactory.getLogger(PassportDataDomainRelationService.class);

    @Autowired
    private IPassportDataDomainRelationRepository passportDataDomainRelationRepository;

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public PassportDataDomainRelation save(PassportDataDomainRelation passportDataDomainRelation) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.save");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        try {
            passportDataDomainRelation.setCreated(CommonUtils.currentMillis());
            passportDataDomainRelation.setCreateId(BaseSecurity.currentPassportId());
            passportDataDomainRelation.setEnable(CommonConstants.YES);
            passportDataDomainRelation.setLocking(CommonConstants.NO);
            return passportDataDomainRelationRepository.save(passportDataDomainRelation);
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

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public Collection<PassportDataDomainRelation> batchSave(Collection<PassportDataDomainRelation> passportDataDomainRelationes) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.batchSave");
            logger.debug("The parameter is passportDataDomainRelationes:" + passportDataDomainRelationes);
        }
        try {
            List<PassportDataDomainRelation> saved = new ArrayList<>();
            for (PassportDataDomainRelation passportDataDomainRelation: passportDataDomainRelationes) {
                saved.add(this.save(passportDataDomainRelation));
            }
            return saved;
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

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public PassportDataDomainRelation modify(PassportDataDomainRelation passportDataDomainRelation) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.modify");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        try {
            Optional<PassportDataDomainRelation> old = passportDataDomainRelationRepository.findById(passportDataDomainRelation.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            passportDataDomainRelation.setModified(CommonUtils.currentMillis());
            passportDataDomainRelation.setModifyId(BaseSecurity.currentPassportId());
            return passportDataDomainRelationRepository.save(passportDataDomainRelation);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
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
    public Collection<PassportDataDomainRelation> batchModify(Collection<PassportDataDomainRelation> passportDataDomainRelationes) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.batchModify");
            logger.debug("The parameter is passportDataDomainRelationes:" + passportDataDomainRelationes);
        }
        try {
            List<PassportDataDomainRelation> modified = new ArrayList<>();
            for (PassportDataDomainRelation passportDataDomainRelation: passportDataDomainRelationes) {
                modified.add(this.save(passportDataDomainRelation));
            }
            return modified;
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
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
    public void remove(PassportDataDomainRelation passportDataDomainRelation) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.remove");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        try {
            Optional<PassportDataDomainRelation> old = passportDataDomainRelationRepository.findById(passportDataDomainRelation.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            passportDataDomainRelationRepository.delete(passportDataDomainRelation);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
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
    public Collection<PassportDataDomainRelation> batchRemove(Collection<PassportDataDomainRelation> passportDataDomainRelations) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.batchRemove");
            logger.debug("The parameter is passportDataDomainRelations:" + passportDataDomainRelations);
        }
        List<PassportDataDomainRelation> deletes = new LinkedList<>();
        List<PassportDataDomainRelation> unExists = new LinkedList<>();
        try {
            for (PassportDataDomainRelation passportDataDomainRelation : passportDataDomainRelations) {
                Optional<PassportDataDomainRelation> old = passportDataDomainRelationRepository.findById(passportDataDomainRelation.getId());
                if (!old.isPresent()) {
                    unExists.add(passportDataDomainRelation);
                    continue;
                }
                deletes.add(passportDataDomainRelation);
            }
            passportDataDomainRelationRepository.deleteAll(deletes);
            return unExists;
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
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
    public Collection<PassportDataDomainRelation> search(PassportDataDomainRelation passportDataDomainRelation) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.search");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        try {
            Example<PassportDataDomainRelation> example = Example.of(passportDataDomainRelation);
            return passportDataDomainRelationRepository.findAll(example);
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

    /**
     * {@Inherited}
     */
    @Override
    public Long count(PassportDataDomainRelation passportDataDomainRelation) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.count");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        try {
            Example<PassportDataDomainRelation> example = Example.of(passportDataDomainRelation);
            return passportDataDomainRelationRepository.count(example);
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

    @Override
    public PassportDataDomainRelation getById(String id) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.getById");
            logger.debug("The parameter is id:" + id);
        }
        try {
            return passportDataDomainRelationRepository.findById(id).get();
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

    /**
     * {@Inherited}
     */
    @Override
    public Page<PassportDataDomainRelation> pageableSearch(PassportDataDomainRelation passportDataDomainRelation, Pageable pageable)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationService.pageableSearch");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
            logger.debug("The parameter is pageable:" + pageable);
        }
        try {
            Example<PassportDataDomainRelation> example = Example.of(passportDataDomainRelation);
            
            return passportDataDomainRelationRepository.findAll(example, pageable);
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
