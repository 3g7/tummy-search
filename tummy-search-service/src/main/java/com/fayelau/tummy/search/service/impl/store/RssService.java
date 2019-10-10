package com.fayelau.tummy.search.service.impl.store;

import java.util.Collection;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.CommonConstants;
import com.fayelau.tummy.search.dubbo.inter.store.IRssDubboService;
import com.fayelau.tummy.search.inter.service.store.IRssService;
import com.fayelau.tummy.search.store.mongo.repository.IRssRepository;
import com.fayelau.tummy.store.entity.Rss;

/**
 * 开关播提醒业务层接口
 * 
 * @author 3g7 2019-09-09 13:00:05
 * @version 0.0.1
 *
 */
@Service
public class RssService implements IRssDubboService, IRssService {

    private static final Logger logger = LoggerFactory.getLogger(RssService.class);

    @Autowired
    private IRssRepository rssRepository;

    @Override
    public Collection<Rss> search(Rss rss, String sortProperty, String direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssService.search");
            logger.debug("params rss:" + rss);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Direction d = Direction.DESC;
            if (direction.equals(CommonConstants.DIRECTION_ASC)) {
                d = Direction.ASC;
            }
            return this.rssRepository.search(rss, sortProperty, d);
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

    @Override
    public Collection<Rss> pageableSearch(Rss rss, Integer page, Integer size, String sortProperty, String direction)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssService.search");
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Direction d = Direction.DESC;
            if (direction.equals(CommonConstants.DIRECTION_ASC)) {
                d = Direction.ASC;
            }
            return this.rssRepository.pageableSearch(rss, page, size, sortProperty, d);
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
    
    @Override
    public Long count(Rss rss) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssService.count");
            logger.debug("params rss:" + rss);
        }
        try {
            return this.rssRepository.count(rss);
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
