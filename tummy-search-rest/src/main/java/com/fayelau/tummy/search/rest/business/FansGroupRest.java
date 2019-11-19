package com.fayelau.tummy.search.rest.business;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.inter.service.business.IFansGroupService;
import com.fayelau.tummy.secretary.api.entry.GroupMessageEntity;

/**
 * 粉丝群请求
 * 
 * @author 3g7 2019-11-13 11:28:37
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("fans_group")
public class FansGroupRest {
    
    private static final Logger logger = LoggerFactory.getLogger(FansGroupRest.class);
    
    @Autowired
    private IFansGroupService fansGroupService;
    
    @GetMapping
    public ResponseRange<GroupMessageEntity> search(Long groupId, Long userId, Integer pageIndex, Integer pageSize){
        if (logger.isDebugEnabled()) {
            logger.debug("run FansGroupRest.search");
        }
        ResponseRange<GroupMessageEntity> responseRange = new ResponseRange<>();
        try {
            if (groupId == null) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            if (pageIndex == null) pageIndex = 1;
            if (pageSize == null) pageSize = 20;
            Collection<GroupMessageEntity> groupMessageEntities = this.fansGroupService.search(groupId, userId, pageIndex, pageSize);
            responseRange.setData(groupMessageEntities);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

}
