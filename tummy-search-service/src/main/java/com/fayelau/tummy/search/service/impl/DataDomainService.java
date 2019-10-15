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
import com.fayelau.tummy.search.entity.DataDomain;
import com.fayelau.tummy.search.inter.service.IDataDomainService;
import com.fayelau.tummy.search.repository.IDataDomainRepository;

/**
 * 数据分域业务层实现
 * 
 * @author 3g7 2019-10-12 15:49:02
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class DataDomainService implements IDataDomainService {
    
    public static final Logger logger = LoggerFactory.getLogger(DataDomainService.class);

    @Autowired
    private IDataDomainRepository dataDomainRepository;

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public DataDomain save(DataDomain dataDomain) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.save");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        try {
            dataDomain.setCreated(CommonUtils.currentMillis());
            dataDomain.setCreateId(BaseSecurity.currentPassportId());
            dataDomain.setEnable(CommonConstants.YES);
            dataDomain.setLocking(CommonConstants.NO);
            return dataDomainRepository.save(dataDomain);
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
    public Collection<DataDomain> batchSave(Collection<DataDomain> dataDomaines) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.batchSave");
            logger.debug("The parameter is dataDomaines:" + dataDomaines);
        }
        try {
            List<DataDomain> saved = new ArrayList<>();
            for (DataDomain dataDomain: dataDomaines) {
                saved.add(this.save(dataDomain));
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
    public DataDomain modify(DataDomain dataDomain) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.modify");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        try {
            Optional<DataDomain> old = dataDomainRepository.findById(dataDomain.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            dataDomain.setModified(CommonUtils.currentMillis());
            dataDomain.setModifyId(BaseSecurity.currentPassportId());
            return dataDomainRepository.save(dataDomain);
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
    public Collection<DataDomain> batchModify(Collection<DataDomain> dataDomaines) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.batchModify");
            logger.debug("The parameter is dataDomaines:" + dataDomaines);
        }
        try {
            List<DataDomain> modified = new ArrayList<>();
            for (DataDomain dataDomain: dataDomaines) {
                modified.add(this.save(dataDomain));
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
    public void remove(DataDomain dataDomain) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.remove");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        try {
            Optional<DataDomain> old = dataDomainRepository.findById(dataDomain.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            dataDomainRepository.delete(dataDomain);
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
    public Collection<DataDomain> batchRemove(Collection<DataDomain> dataDomains) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.batchRemove");
            logger.debug("The parameter is dataDomains:" + dataDomains);
        }
        List<DataDomain> deletes = new LinkedList<>();
        List<DataDomain> unExists = new LinkedList<>();
        try {
            for (DataDomain dataDomain : dataDomains) {
                Optional<DataDomain> old = dataDomainRepository.findById(dataDomain.getId());
                if (!old.isPresent()) {
                    unExists.add(dataDomain);
                    continue;
                }
                deletes.add(dataDomain);
            }
            dataDomainRepository.deleteAll(deletes);
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
    public Collection<DataDomain> search(DataDomain dataDomain) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.search");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        try {
            Example<DataDomain> example = Example.of(dataDomain);
            return dataDomainRepository.findAll(example);
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
    public Long count(DataDomain dataDomain) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.count");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        try {
            Example<DataDomain> example = Example.of(dataDomain);
            return dataDomainRepository.count(example);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public DataDomain getById(String id) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.getById");
            logger.debug("The parameter is id:" + id);
        }
        try {
            return dataDomainRepository.findById(id).get();
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
    public Page<DataDomain> pageableSearch(DataDomain dataDomain, Pageable pageable)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainService.pageableSearch");
            logger.debug("The parameter is dataDomain:" + dataDomain);
            logger.debug("The parameter is pageable:" + pageable);
        }
        try {
            Example<DataDomain> example = Example.of(dataDomain);
            
            return dataDomainRepository.findAll(example, pageable);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
