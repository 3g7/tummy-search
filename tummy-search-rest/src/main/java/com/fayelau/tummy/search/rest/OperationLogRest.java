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
import com.fayelau.tummy.search.entity.OperationLog;
import com.fayelau.tummy.search.inter.service.IOperationLogService;

/**
 * 操作日志请求
 * 
 * @author 3g7 2019-10-12 15:55:53
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("operation_log")
public class OperationLogRest {
    
    public static final Logger logger = LoggerFactory.getLogger(OperationLogRest.class);

    @Autowired
    private IOperationLogService operationLogService;

    @PostMapping
    public ResponseRange<OperationLog> save(OperationLog operationLog) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.save");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        ResponseRange<OperationLog> responseRange = new ResponseRange<>();
        try {
            if (operationLog != null) {
                operationLog = operationLogService.save(operationLog);
            }
            responseRange.setOneData(operationLog);
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
    public ResponseRange<OperationLog> batchSave(Collection<OperationLog> operationLogs) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.batchSave");
            logger.debug("The parameter is operationLogs:" + operationLogs);
        }
        ResponseRange<OperationLog> responseRange = new ResponseRange<>();
        try {
            if (!operationLogs.isEmpty()) {
                operationLogs = operationLogService.batchSave(operationLogs);
            }
            responseRange.setData(operationLogs);
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
    public ResponseRange<OperationLog> modify(OperationLog operationLog) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.modify");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        ResponseRange<OperationLog> responseRange = new ResponseRange<>();
        try {
            if (operationLog != null) {
                operationLog = operationLogService.modify(operationLog);
            }
            responseRange.setOneData(operationLog);
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
    public ResponseRange<OperationLog> batchModify(Collection<OperationLog> operationLogs) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.batchModify");
            logger.debug("The parameter is operationLogs:" + operationLogs);
        }
        ResponseRange<OperationLog> responseRange = new ResponseRange<>();
        try {
            if (operationLogs.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            operationLogs = operationLogService.batchModify(operationLogs);
            responseRange.setData(operationLogs);
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
    public ResponseRange<OperationLog> remove(OperationLog operationLog) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.remove");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        ResponseRange<OperationLog> responseRange = new ResponseRange<>();
        try {
            if (operationLog != null) {
                operationLogService.remove(operationLog);
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
    public ResponseRange<OperationLog> batchRemove(@RequestBody List<OperationLog> operationLogs) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.batchRemove");
            logger.debug("The parameter is operationLogs:" + operationLogs);
        }
        ResponseRange<OperationLog> responseRange = new ResponseRange<>();
        try {
            if (operationLogs.isEmpty()) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            operationLogs = (List<OperationLog>) operationLogService.batchRemove(operationLogs);
            if (!operationLogs.isEmpty()) {
                responseRange.setData(operationLogs);
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
    public ResponseRange<OperationLog> search(OperationLog operationLog) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.search");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        ResponseRange<OperationLog> responseRange = new ResponseRange<>();
        try {
            if (operationLog != null) {
                for (OperationLog g : operationLogService.search(operationLog)) {
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
    public ResponseRange<Long> count(OperationLog operationLog) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.count");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            responseRange.setOneData(operationLogService.count(operationLog));
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
    public ResponseRange<OperationLog> search(OperationLog operationLog, Integer page, Integer size) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function OperationLogRest.search");
            logger.debug("The parameter is operationLog:" + operationLog);
        }
        ResponseRange<OperationLog> responseRange = new ResponseRange<>();
        try {
            if (page == null || page == 0)
                page = 0;
            else
                page = page - 1;
            if (size == null)
                size = 20;
            Sort sort = Sort.by(Direction.DESC, DefaultConstants.SYSTEM_SORT_PROPERTY);
            PageRequest pageable = PageRequest.of(page, size, sort);
            if (operationLog != null) {
                Page<OperationLog> pageData = operationLogService.pageableSearch(operationLog, pageable);
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
