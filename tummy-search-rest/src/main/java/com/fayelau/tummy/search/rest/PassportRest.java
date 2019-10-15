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
import com.fayelau.tummy.search.entity.Passport;
import com.fayelau.tummy.search.inter.service.IPassportService;

/**
 * 通行证请求
 * 
 * @author 3g7 2019-10-12 09:37:10
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("passport")
public class PassportRest {
    
    public static final Logger logger = LoggerFactory.getLogger(PassportRest.class);

    @Autowired
    private IPassportService passportService;

    @PostMapping
    public ResponseRange<Passport> save(Passport passport) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.save");
            logger.debug("The parameter is passport:" + passport);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            if (passport != null) {
                passport = passportService.save(passport);
            }
            responseRange.setOneData(passport);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PostMapping(params = { "batch=true" })
    public ResponseRange<Passport> batchSave(Collection<Passport> passports) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.batchSave");
            logger.debug("The parameter is passports:" + passports);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            if (!passports.isEmpty()) {
                passports = passportService.batchSave(passports);
            }
            responseRange.setData(passports);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping
    public ResponseRange<Passport> modify(Passport passport) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.modify");
            logger.debug("The parameter is passport:" + passport);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            if (passport != null) {
                passport = passportService.modify(passport);
            }
            responseRange.setOneData(passport);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping(params = { "batch=true" })
    public ResponseRange<Passport> batchModify(Collection<Passport> passports) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.batchModify");
            logger.debug("The parameter is passports:" + passports);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            if (passports.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            passports = passportService.batchModify(passports);
            responseRange.setData(passports);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @DeleteMapping
    public ResponseRange<Passport> remove(Passport passport) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.remove");
            logger.debug("The parameter is passport:" + passport);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            if (passport != null) {
                passportService.remove(passport);
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
    public ResponseRange<Passport> batchRemove(@RequestBody List<Passport> passports) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.batchRemove");
            logger.debug("The parameter is passports:" + passports);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            if (passports.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            passports = (List<Passport>) passportService.batchRemove(passports);
            if (!passports.isEmpty()) {
                responseRange.setData(passports);
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
    public ResponseRange<Passport> search(Passport passport) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.search");
            logger.debug("The parameter is passport:" + passport);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            if (passport != null) {
                for (Passport g : passportService.search(passport)) {
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
    public ResponseRange<Long> count(Passport passport) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.count");
            logger.debug("The parameter is passport:" + passport);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            responseRange.setOneData(passportService.count(passport));
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
    public ResponseRange<Passport> search(Passport passport, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function PassportRest.search");
            logger.debug("The parameter is passport:" + passport);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            if (page == null || page == 0)
                page = 0;
            else
                page = page - 1;
            if (size == null)
                size = 20;
            Sort sort = Sort.by(Direction.DESC, DefaultConstants.SYSTEM_SORT_PROPERTY);
            PageRequest pageable = PageRequest.of(page, size, sort);
            if (passport != null) {
                Page<Passport> pageData = passportService.pageableSearch(passport, pageable);
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
