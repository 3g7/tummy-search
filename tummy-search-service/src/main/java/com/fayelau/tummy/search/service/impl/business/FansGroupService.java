package com.fayelau.tummy.search.service.impl.business;

import java.util.Collection;

import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.inter.service.business.IFansGroupService;
import com.fayelau.tummy.secretary.api.entry.GroupMessageEntity;
import com.fayelau.tummy.secretary.api.service.QQGroupMessageService;

/**
 * 粉丝群业务类
 * 
 * @author 3g7 2019-11-13 11:08:20
 * @version 0.0.1
 *
 */
@Service
public class FansGroupService implements IFansGroupService {

    public static final Logger logger = LoggerFactory.getLogger(FansGroupService.class);

    @Reference
    private QQGroupMessageService qQGroupMessageService;

    @Override
    public Collection<GroupMessageEntity> search(Long groupId, Long userId, int pageIndex, int pageSize)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function FansGroupService.search");
            logger.debug("The parameter is groupId:" + groupId);
            logger.debug("The parameter is userId:" + userId);
            logger.debug("The parameter is pageIndex:" + pageIndex);
            logger.debug("The parameter is pageSize:" + pageSize);
        }
        try {
            Collection<GroupMessageEntity> groupMessageEntities = qQGroupMessageService.qqGroupMessage(groupId, userId, pageIndex, pageSize, "dateCreate", "desc");
            return groupMessageEntities;
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
