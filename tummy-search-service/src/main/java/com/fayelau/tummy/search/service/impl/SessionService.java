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
import com.fayelau.tummy.search.entity.Session;
import com.fayelau.tummy.search.inter.service.ISessionService;
import com.fayelau.tummy.search.repository.ISessionRepository;

/**
 * 会话业务层实现
 * 
 * @author 3g7 2019-10-12 09:32:20
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class SessionService implements ISessionService {

    public static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    @Autowired
    private ISessionRepository sessionRepository;

    /**
     * {@Inherited}
     */
    @Override
    @Transactional(readOnly = false)
    public Session save(Session session) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.save");
            logger.debug("The parameter is session:" + session);
        }
        try {
            session.setCreated(CommonUtils.currentMillis());
            session.setCreateId(BaseSecurity.currentPassportId());
            session.setEnable(CommonConstants.YES);
            session.setLocking(CommonConstants.NO);
            return sessionRepository.save(session);
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
    public Collection<Session> batchSave(Collection<Session> sessions) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.batchSave");
            logger.debug("The parameter is sessions:" + sessions);
        }
        try {
            List<Session> saved = new ArrayList<>();
            for (Session session : sessions) {
                saved.add(this.save(session));
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
    public Session modify(Session session) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.modify");
            logger.debug("The parameter is session:" + session);
        }
        try {
            Optional<Session> old = sessionRepository.findById(session.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            session.setModified(CommonUtils.currentMillis());
            session.setModifyId(BaseSecurity.currentPassportId());
            return sessionRepository.save(session);
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
    public Collection<Session> batchModify(Collection<Session> sessions) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.batchModify");
            logger.debug("The parameter is sessions:" + sessions);
        }
        try {
            List<Session> modified = new ArrayList<>();
            for (Session session : sessions) {
                modified.add(this.save(session));
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
    public void remove(Session session) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.remove");
            logger.debug("The parameter is session:" + session);
        }
        try {
            Optional<Session> old = sessionRepository.findById(session.getId());
            if (!old.isPresent()) {
                throw TummyException.getException(TummyExCode.OLD_DATA_NOT_FOUNT);
            }
            sessionRepository.delete(session);
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
    public Collection<Session> batchRemove(Collection<Session> sessions) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.batchRemove");
            logger.debug("The parameter is sessions:" + sessions);
        }
        List<Session> deletes = new LinkedList<>();
        List<Session> unExists = new LinkedList<>();
        try {
            for (Session session : sessions) {
                Optional<Session> old = sessionRepository.findById(session.getId());
                if (!old.isPresent()) {
                    unExists.add(session);
                    continue;
                }
                deletes.add(session);
            }
            sessionRepository.deleteAll(deletes);
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
    public Collection<Session> search(Session session) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.search");
            logger.debug("The parameter is session:" + session);
        }
        try {
            Example<Session> example = Example.of(session);
            return sessionRepository.findAll(example);
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
    public Long count(Session session) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.count");
            logger.debug("The parameter is session:" + session);
        }
        try {
            Example<Session> example = Example.of(session);
            return sessionRepository.count(example);
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
    public Session getById(String id) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.getById");
            logger.debug("The parameter is id:" + id);
        }
        try {
            return sessionRepository.findById(id).get();
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
    public Page<Session> pageableSearch(Session session, Pageable pageable) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionService.pageableSearch");
            logger.debug("The parameter is session:" + session);
            logger.debug("The parameter is pageable:" + pageable);
        }
        try {
            Example<Session> example = Example.of(session);
            return sessionRepository.findAll(example, pageable);
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
