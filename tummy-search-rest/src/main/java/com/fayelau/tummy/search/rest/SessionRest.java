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
import com.fayelau.tummy.search.entity.Session;
import com.fayelau.tummy.search.inter.service.ISessionService;

/**
 * 会话请求
 * 
 * @author 3g7 2019-10-12 09:38:05
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("session")
public class SessionRest {
    
    public static final Logger logger = LoggerFactory.getLogger(SessionRest.class);

    @Autowired
    private ISessionService sessionService;

    @PostMapping
    public ResponseRange<Session> save(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.save");
            logger.debug("The parameter is session:" + session);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            if (session != null) {
                session = sessionService.save(session);
            }
            responseRange.setOneData(session);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PostMapping(params = { "batch=true" })
    public ResponseRange<Session> batchSave(Collection<Session> sessions) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.batchSave");
            logger.debug("The parameter is sessions:" + sessions);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            if (!sessions.isEmpty()) {
                sessions = sessionService.batchSave(sessions);
            }
            responseRange.setData(sessions);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping
    public ResponseRange<Session> modify(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.modify");
            logger.debug("The parameter is session:" + session);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            if (session != null) {
                session = sessionService.modify(session);
            }
            responseRange.setOneData(session);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping(params = { "batch=true" })
    public ResponseRange<Session> batchModify(Collection<Session> sessions) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.batchModify");
            logger.debug("The parameter is sessions:" + sessions);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            if (sessions.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            sessions = sessionService.batchModify(sessions);
            responseRange.setData(sessions);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @DeleteMapping
    public ResponseRange<Session> remove(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.remove");
            logger.debug("The parameter is session:" + session);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            if (session != null) {
                sessionService.remove(session);
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
    public ResponseRange<Session> batchRemove(@RequestBody List<Session> sessions) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.batchRemove");
            logger.debug("The parameter is sessions:" + sessions);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            if (sessions.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            sessions = (List<Session>) sessionService.batchRemove(sessions);
            if (!sessions.isEmpty()) {
                responseRange.setData(sessions);
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
    public ResponseRange<Session> search(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.search");
            logger.debug("The parameter is session:" + session);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            if (session != null) {
                for (Session g : sessionService.search(session)) {
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
    public ResponseRange<Long> count(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.count");
            logger.debug("The parameter is session:" + session);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            responseRange.setOneData(sessionService.count(session));
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
    public ResponseRange<Session> search(Session session, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function SessionRest.search");
            logger.debug("The parameter is session:" + session);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            if (page == null || page == 0)
                page = 0;
            else
                page = page - 1;
            if (size == null)
                size = 20;
            Sort sort = Sort.by(Direction.DESC, DefaultConstants.SYSTEM_SORT_PROPERTY);
            PageRequest pageable = PageRequest.of(page, size, sort);
            if (session != null) {
                Page<Session> pageData = sessionService.pageableSearch(session, pageable);
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
