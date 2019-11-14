package com.fayelau.tummy.search.service.impl.business;

import java.util.Collection;
import java.util.Map;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.dubbo.inter.business.IRankDubboService;
import com.fayelau.tummy.search.inter.service.business.IRankService;
import com.fayelau.tummy.search.store.mongo.repository.IChatmsgRepository;
import com.fayelau.tummy.store.entity.Chatmsg;
import com.fayelau.tummy.store.pojo.Rank;

/**
 * 排行榜相关实现
 * 
 * @author 3g7 2019-09-30 09:54:42
 * @version 0.0.1
 *
 */
@Service
public class RankService implements IRankDubboService, IRankService {
    
    private static final Logger logger = LoggerFactory.getLogger(RankService.class);

    @Autowired
    private IChatmsgRepository chatmsgRepository;

    @Override
    public Collection<Rank> rankByTime(Long start, Long end, Long limit, Map<String, Object> domainParams) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run RankService.rankByTime");
            logger.debug("params start:" + start);
            logger.debug("params end:" + end);
            logger.debug("params limit:" + limit);
        }
        try {
            if (start == null) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            if (end == null) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            if (limit == null) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            return chatmsgRepository.rankByTime(new Chatmsg(), start, end, limit, domainParams);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
