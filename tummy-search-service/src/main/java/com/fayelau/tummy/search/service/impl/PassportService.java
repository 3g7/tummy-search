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
import com.fayelau.tummy.search.entity.Passport;
import com.fayelau.tummy.search.inter.service.IPassportService;
import com.fayelau.tummy.search.repository.IPassportRepository;

/**
 * 通行证业务层实现
 * 
 * @author 3g7 2019-10-12 09:31:05
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class PassportService implements IPassportService {

    public static final Logger logger = LoggerFactory.getLogger(PassportService.class);

    @Autowired
    private IPassportRepository passportRepository;

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public Passport save(Passport passport) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.save");
            logger.debug("The parameter is passport:" + passport);
        }
        try {
            passport.setCreated(CommonUtils.currentMillis());
            passport.setCreateId(BaseSecurity.currentPassportId());
            passport.setEnable(CommonConstants.YES);
            passport.setLocking(CommonConstants.NO);
            passport.setPassword(BaseSecurity.MD5(passport.getPassword(), passport.getUsername()));
            return passportRepository.save(passport);
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
    public Collection<Passport> batchSave(Collection<Passport> passports) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.batchSave");
            logger.debug("The parameter is passports:" + passports);
        }
        try {
            List<Passport> saved = new ArrayList<>();
            for (Passport passport : passports) {
                saved.add(this.save(passport));
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
    public Passport modify(Passport passport) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.modify");
            logger.debug("The parameter is passport:" + passport);
        }
        try {
            Optional<Passport> old = passportRepository.findById(passport.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            passport.setModified(CommonUtils.currentMillis());
            passport.setModifyId(BaseSecurity.currentPassportId());
            return passportRepository.save(passport);
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
    public Collection<Passport> batchModify(Collection<Passport> passports) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.batchModify");
            logger.debug("The parameter is passports:" + passports);
        }
        try {
            List<Passport> modified = new ArrayList<>();
            for (Passport passport : passports) {
                modified.add(this.save(passport));
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
    public void remove(Passport passport) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.remove");
            logger.debug("The parameter is passport:" + passport);
        }
        try {
            Optional<Passport> old = passportRepository.findById(passport.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            passportRepository.delete(passport);
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
    public Collection<Passport> batchRemove(Collection<Passport> passports) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.batchRemove");
            logger.debug("The parameter is passports:" + passports);
        }
        List<Passport> deletes = new LinkedList<>();
        List<Passport> unExists = new LinkedList<>();
        try {
            for (Passport passport : passports) {
                Optional<Passport> old = passportRepository.findById(passport.getId());
                if (!old.isPresent()) {
                    unExists.add(passport);
                    continue;
                }
                deletes.add(passport);
            }
            passportRepository.deleteAll(deletes);
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
    public Collection<Passport> search(Passport passport) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.search");
            logger.debug("The parameter is passport:" + passport);
        }
        try {
            Example<Passport> example = Example.of(passport);
            return passportRepository.findAll(example);
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
    public Long count(Passport passport) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.count");
            logger.debug("The parameter is passport:" + passport);
        }
        try {
            Example<Passport> example = Example.of(passport);
            return passportRepository.count(example);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public Passport getById(String id) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.getById");
            logger.debug("The parameter is id:" + id);
        }
        try {
            return passportRepository.findById(id).get();
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
    public Page<Passport> pageableSearch(Passport passport, Pageable pageable) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportService.pageableSearch");
            logger.debug("The parameter is passport:" + passport);
            logger.debug("The parameter is pageable:" + pageable);
        }
        try {
            Example<Passport> example = Example.of(passport);
            return passportRepository.findAll(example, pageable);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
