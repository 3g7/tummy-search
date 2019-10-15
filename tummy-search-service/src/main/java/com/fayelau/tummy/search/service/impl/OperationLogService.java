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
import com.fayelau.tummy.search.entity.OperationLog;
import com.fayelau.tummy.search.inter.service.IOperationLogService;
import com.fayelau.tummy.search.repository.IOperationLogRepository;

/**
 * 操作日志业务层实现
 * 
 * @author 3g7 2019-10-12 15:51:22
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class OperationLogService implements IOperationLogService {
    
    public static final Logger logger = LoggerFactory.getLogger(OperationLogService.class);

    @Autowired
    private IOperationLogRepository operationLogRepository;

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public OperationLog save(OperationLog operationLog) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.save");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        try {
            operationLog.setCreated(CommonUtils.currentMillis());
            operationLog.setCreateId(BaseSecurity.currentPassportId());
            operationLog.setEnable(CommonConstants.YES);
            operationLog.setLocking(CommonConstants.NO);
            return operationLogRepository.save(operationLog);
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
    public Collection<OperationLog> batchSave(Collection<OperationLog> operationLoges) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.batchSave");
            logger.debug("The parameter is operationLoges:" + operationLoges);
        }
        try {
            List<OperationLog> saved = new ArrayList<>();
            for (OperationLog operationLog: operationLoges) {
                saved.add(this.save(operationLog));
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
    public OperationLog modify(OperationLog operationLog) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.modify");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        try {
            Optional<OperationLog> old = operationLogRepository.findById(operationLog.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            operationLog.setModified(CommonUtils.currentMillis());
            operationLog.setModifyId(BaseSecurity.currentPassportId());
            return operationLogRepository.save(operationLog);
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
    public Collection<OperationLog> batchModify(Collection<OperationLog> operationLoges) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.batchModify");
            logger.debug("The parameter is operationLoges:" + operationLoges);
        }
        try {
            List<OperationLog> modified = new ArrayList<>();
            for (OperationLog operationLog: operationLoges) {
                modified.add(this.save(operationLog));
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
    public void remove(OperationLog operationLog) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.remove");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        try {
            Optional<OperationLog> old = operationLogRepository.findById(operationLog.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            operationLogRepository.delete(operationLog);
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
    public Collection<OperationLog> batchRemove(Collection<OperationLog> operationLogs) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.batchRemove");
            logger.debug("The parameter is operationLogs:" + operationLogs);
        }
        List<OperationLog> deletes = new LinkedList<>();
        List<OperationLog> unExists = new LinkedList<>();
        try {
            for (OperationLog operationLog : operationLogs) {
                Optional<OperationLog> old = operationLogRepository.findById(operationLog.getId());
                if (!old.isPresent()) {
                    unExists.add(operationLog);
                    continue;
                }
                deletes.add(operationLog);
            }
            operationLogRepository.deleteAll(deletes);
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
    public Collection<OperationLog> search(OperationLog operationLog) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.search");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        try {
            Example<OperationLog> example = Example.of(operationLog);
            return operationLogRepository.findAll(example);
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
    public Long count(OperationLog operationLog) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.count");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        try {
            Example<OperationLog> example = Example.of(operationLog);
            return operationLogRepository.count(example);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public OperationLog getById(String id) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.getById");
            logger.debug("The parameter is id:" + id);
        }
        try {
            return operationLogRepository.findById(id).get();
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
    public Page<OperationLog> pageableSearch(OperationLog operationLog, Pageable pageable)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogService.pageableSearch");
            logger.debug("The parameter is operationLog:" + operationLog);
            logger.debug("The parameter is pageable:" + pageable);
        }
        try {
            Example<OperationLog> example = Example.of(operationLog);
            
            return operationLogRepository.findAll(example, pageable);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
