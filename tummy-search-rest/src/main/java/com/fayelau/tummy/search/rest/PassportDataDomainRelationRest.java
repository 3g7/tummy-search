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
import com.fayelau.tummy.search.entity.PassportDataDomainRelation;
import com.fayelau.tummy.search.inter.service.IPassportDataDomainRelationService;

/**
 * 通行证数据分域关系
 * 
 * @author 3g7 2019-10-12 15:57:07
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("passport_data_domain_relation")
public class PassportDataDomainRelationRest {
    
    public static final Logger logger = LoggerFactory.getLogger(PassportDataDomainRelationRest.class);

    @Autowired
    private IPassportDataDomainRelationService passportDataDomainRelationService;

    @PostMapping
    public ResponseRange<PassportDataDomainRelation> save(PassportDataDomainRelation passportDataDomainRelation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.save");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        ResponseRange<PassportDataDomainRelation> responseRange = new ResponseRange<>();
        try {
            if (passportDataDomainRelation != null) {
                passportDataDomainRelation = passportDataDomainRelationService.save(passportDataDomainRelation);
            }
            responseRange.setOneData(passportDataDomainRelation);
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
    public ResponseRange<PassportDataDomainRelation> batchSave(Collection<PassportDataDomainRelation> passportDataDomainRelations) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.batchSave");
            logger.debug("The parameter is passportDataDomainRelations:" + passportDataDomainRelations);
        }
        ResponseRange<PassportDataDomainRelation> responseRange = new ResponseRange<>();
        try {
            if (!passportDataDomainRelations.isEmpty()) {
                passportDataDomainRelations = passportDataDomainRelationService.batchSave(passportDataDomainRelations);
            }
            responseRange.setData(passportDataDomainRelations);
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
    public ResponseRange<PassportDataDomainRelation> modify(PassportDataDomainRelation passportDataDomainRelation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.modify");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        ResponseRange<PassportDataDomainRelation> responseRange = new ResponseRange<>();
        try {
            if (passportDataDomainRelation != null) {
                passportDataDomainRelation = passportDataDomainRelationService.modify(passportDataDomainRelation);
            }
            responseRange.setOneData(passportDataDomainRelation);
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
    public ResponseRange<PassportDataDomainRelation> batchModify(Collection<PassportDataDomainRelation> passportDataDomainRelations) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.batchModify");
            logger.debug("The parameter is passportDataDomainRelations:" + passportDataDomainRelations);
        }
        ResponseRange<PassportDataDomainRelation> responseRange = new ResponseRange<>();
        try {
            if (passportDataDomainRelations.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            passportDataDomainRelations = passportDataDomainRelationService.batchModify(passportDataDomainRelations);
            responseRange.setData(passportDataDomainRelations);
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
    public ResponseRange<PassportDataDomainRelation> remove(PassportDataDomainRelation passportDataDomainRelation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.remove");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        ResponseRange<PassportDataDomainRelation> responseRange = new ResponseRange<>();
        try {
            if (passportDataDomainRelation != null) {
                passportDataDomainRelationService.remove(passportDataDomainRelation);
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
    public ResponseRange<PassportDataDomainRelation> batchRemove(@RequestBody List<PassportDataDomainRelation> passportDataDomainRelations) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.batchRemove");
            logger.debug("The parameter is passportDataDomainRelations:" + passportDataDomainRelations);
        }
        ResponseRange<PassportDataDomainRelation> responseRange = new ResponseRange<>();
        try {
            if (passportDataDomainRelations.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            passportDataDomainRelations = (List<PassportDataDomainRelation>) passportDataDomainRelationService.batchRemove(passportDataDomainRelations);
            if (!passportDataDomainRelations.isEmpty()) {
                responseRange.setData(passportDataDomainRelations);
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
    public ResponseRange<PassportDataDomainRelation> search(PassportDataDomainRelation passportDataDomainRelation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.search");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        ResponseRange<PassportDataDomainRelation> responseRange = new ResponseRange<>();
        try {
            if (passportDataDomainRelation != null) {
                for (PassportDataDomainRelation g : passportDataDomainRelationService.search(passportDataDomainRelation)) {
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
    public ResponseRange<Long> count(PassportDataDomainRelation passportDataDomainRelation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.count");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            responseRange.setOneData(passportDataDomainRelationService.count(passportDataDomainRelation));
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
    public ResponseRange<PassportDataDomainRelation> search(PassportDataDomainRelation passportDataDomainRelation, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportDataDomainRelationRest.search");
            logger.debug("The parameter is passportDataDomainRelation:" + passportDataDomainRelation);
        }
        ResponseRange<PassportDataDomainRelation> responseRange = new ResponseRange<>();
        try {
            if (page == null || page == 0)
                page = 0;
            else
                page = page - 1;
            if (size == null)
                size = 20;
            Sort sort = Sort.by(Direction.DESC, DefaultConstants.SYSTEM_SORT_PROPERTY);
            PageRequest pageable = PageRequest.of(page, size, sort);
            if (passportDataDomainRelation != null) {
                Page<PassportDataDomainRelation> pageData = passportDataDomainRelationService.pageableSearch(passportDataDomainRelation, pageable);
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
