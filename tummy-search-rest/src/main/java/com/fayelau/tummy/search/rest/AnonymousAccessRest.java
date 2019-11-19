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
import com.fayelau.tummy.search.entity.AnonymousAccess;
import com.fayelau.tummy.search.inter.service.IAnonymousAccessService;

/**
 * 匿名访问请求
 * @author 3g7 2019-10-12 09:33:33
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("anonymous_access")
public class AnonymousAccessRest {
    
    public static final Logger logger = LoggerFactory.getLogger(AnonymousAccessRest.class);

    @Autowired
    private IAnonymousAccessService anonymousAccessService;

    @PostMapping
    public ResponseRange<AnonymousAccess> save(AnonymousAccess anonymousAccess) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.save");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        ResponseRange<AnonymousAccess> responseRange = new ResponseRange<>();
        try {
            if (anonymousAccess != null) {
                anonymousAccess = anonymousAccessService.save(anonymousAccess);
            }
            responseRange.setOneData(anonymousAccess);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PostMapping(params = { "batch=true" })
    public ResponseRange<AnonymousAccess> batchSave(Collection<AnonymousAccess> anonymousAccesss) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.batchSave");
            logger.debug("The parameter is anonymousAccesss:" + anonymousAccesss);
        }
        ResponseRange<AnonymousAccess> responseRange = new ResponseRange<>();
        try {
            if (!anonymousAccesss.isEmpty()) {
                anonymousAccesss = anonymousAccessService.batchSave(anonymousAccesss);
            }
            responseRange.setData(anonymousAccesss);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping
    public ResponseRange<AnonymousAccess> modify(AnonymousAccess anonymousAccess) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.modify");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        ResponseRange<AnonymousAccess> responseRange = new ResponseRange<>();
        try {
            if (anonymousAccess != null) {
                anonymousAccess = anonymousAccessService.modify(anonymousAccess);
            }
            responseRange.setOneData(anonymousAccess);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping(params = { "batch=true" })
    public ResponseRange<AnonymousAccess> batchModify(Collection<AnonymousAccess> anonymousAccesss) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.batchModify");
            logger.debug("The parameter is anonymousAccesss:" + anonymousAccesss);
        }
        ResponseRange<AnonymousAccess> responseRange = new ResponseRange<>();
        try {
            if (anonymousAccesss.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            anonymousAccesss = anonymousAccessService.batchModify(anonymousAccesss);
            responseRange.setData(anonymousAccesss);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @DeleteMapping
    public ResponseRange<AnonymousAccess> remove(AnonymousAccess anonymousAccess) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.remove");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        ResponseRange<AnonymousAccess> responseRange = new ResponseRange<>();
        try {
            if (anonymousAccess != null) {
                anonymousAccessService.remove(anonymousAccess);
            }
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @DeleteMapping(params = { "batch=true" })
    public ResponseRange<AnonymousAccess> batchRemove(@RequestBody List<AnonymousAccess> anonymousAccesss) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.batchRemove");
            logger.debug("The parameter is anonymousAccesss:" + anonymousAccesss);
        }
        ResponseRange<AnonymousAccess> responseRange = new ResponseRange<>();
        try {
            if (anonymousAccesss.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            anonymousAccesss = (List<AnonymousAccess>) anonymousAccessService.batchRemove(anonymousAccesss);
            if (!anonymousAccesss.isEmpty()) {
                responseRange.setData(anonymousAccesss);
            }
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @GetMapping
    public ResponseRange<AnonymousAccess> search(AnonymousAccess anonymousAccess) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.search");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        ResponseRange<AnonymousAccess> responseRange = new ResponseRange<>();
        try {
            if (anonymousAccess != null) {
                for (AnonymousAccess g : anonymousAccessService.search(anonymousAccess)) {
                    responseRange.setOneData(g);
                }
            }
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
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
    public ResponseRange<Long> count(AnonymousAccess anonymousAccess) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.count");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            responseRange.setOneData(anonymousAccessService.count(anonymousAccess));
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
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
    public ResponseRange<AnonymousAccess> search(AnonymousAccess anonymousAccess, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AnonymousAccessRest.search");
            logger.debug("The parameter is anonymousAccess:" + anonymousAccess);
        }
        ResponseRange<AnonymousAccess> responseRange = new ResponseRange<>();
        try {
            if (page == null || page == 0)
                page = 0;
            else
                page = page - 1;
            if (size == null)
                size = 20;
            Sort sort = Sort.by(Direction.DESC, DefaultConstants.SYSTEM_SORT_PROPERTY);
            PageRequest pageable = PageRequest.of(page, size, sort);
            if (anonymousAccess != null) {
                Page<AnonymousAccess> pageData = anonymousAccessService.pageableSearch(anonymousAccess, pageable);
                responseRange.setData(pageData.getContent());
                responseRange.openPage(pageData.getNumber(), size);
                responseRange.setTotal(pageData.getTotalElements());
            }
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
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
