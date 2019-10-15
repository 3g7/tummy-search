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
import com.fayelau.tummy.search.entity.DataDomain;
import com.fayelau.tummy.search.inter.service.IDataDomainService;

/**
 * 数据分域请求
 * 
 * @author 3g7 2019-10-12 15:54:27
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("data_domain")
public class DataDomainRest {
    
    public static final Logger logger = LoggerFactory.getLogger(DataDomainRest.class);

    @Autowired
    private IDataDomainService dataDomainService;

    @PostMapping
    public ResponseRange<DataDomain> save(DataDomain dataDomain) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.save");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        ResponseRange<DataDomain> responseRange = new ResponseRange<>();
        try {
            if (dataDomain != null) {
                dataDomain = dataDomainService.save(dataDomain);
            }
            responseRange.setOneData(dataDomain);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PostMapping(params = { "batch=true" })
    public ResponseRange<DataDomain> batchSave(Collection<DataDomain> dataDomains) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.batchSave");
            logger.debug("The parameter is dataDomains:" + dataDomains);
        }
        ResponseRange<DataDomain> responseRange = new ResponseRange<>();
        try {
            if (!dataDomains.isEmpty()) {
                dataDomains = dataDomainService.batchSave(dataDomains);
            }
            responseRange.setData(dataDomains);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping
    public ResponseRange<DataDomain> modify(DataDomain dataDomain) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.modify");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        ResponseRange<DataDomain> responseRange = new ResponseRange<>();
        try {
            if (dataDomain != null) {
                dataDomain = dataDomainService.modify(dataDomain);
            }
            responseRange.setOneData(dataDomain);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @PutMapping(params = { "batch=true" })
    public ResponseRange<DataDomain> batchModify(Collection<DataDomain> dataDomains) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.batchModify");
            logger.debug("The parameter is dataDomains:" + dataDomains);
        }
        ResponseRange<DataDomain> responseRange = new ResponseRange<>();
        try {
            if (dataDomains.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            dataDomains = dataDomainService.batchModify(dataDomains);
            responseRange.setData(dataDomains);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @DeleteMapping
    public ResponseRange<DataDomain> remove(DataDomain dataDomain) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.remove");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        ResponseRange<DataDomain> responseRange = new ResponseRange<>();
        try {
            if (dataDomain != null) {
                dataDomainService.remove(dataDomain);
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
    public ResponseRange<DataDomain> batchRemove(@RequestBody List<DataDomain> dataDomains) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.batchRemove");
            logger.debug("The parameter is dataDomains:" + dataDomains);
        }
        ResponseRange<DataDomain> responseRange = new ResponseRange<>();
        try {
            if (dataDomains.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            dataDomains = (List<DataDomain>) dataDomainService.batchRemove(dataDomains);
            if (!dataDomains.isEmpty()) {
                responseRange.setData(dataDomains);
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
    public ResponseRange<DataDomain> search(DataDomain dataDomain) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.search");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        ResponseRange<DataDomain> responseRange = new ResponseRange<>();
        try {
            if (dataDomain != null) {
                for (DataDomain g : dataDomainService.search(dataDomain)) {
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
    public ResponseRange<Long> count(DataDomain dataDomain) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.count");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            responseRange.setOneData(dataDomainService.count(dataDomain));
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
    public ResponseRange<DataDomain> search(DataDomain dataDomain, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function DataDomainRest.search");
            logger.debug("The parameter is dataDomain:" + dataDomain);
        }
        ResponseRange<DataDomain> responseRange = new ResponseRange<>();
        try {
            if (page == null || page == 0)
                page = 0;
            else
                page = page - 1;
            if (size == null)
                size = 20;
            Sort sort = Sort.by(Direction.DESC, DefaultConstants.SYSTEM_SORT_PROPERTY);
            PageRequest pageable = PageRequest.of(page, size, sort);
            if (dataDomain != null) {
                Page<DataDomain> pageData = dataDomainService.pageableSearch(dataDomain, pageable);
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
