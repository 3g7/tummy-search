package com.fayelau.tummy.search.service.impl.store;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.core.constants.TummySearchDefaultConstants;
import com.fayelau.tummy.search.dubbo.inter.store.IBlabDubboService;
import com.fayelau.tummy.search.inter.service.store.IBlabService;
import com.fayelau.tummy.search.store.mongo.entity.Blab;
import com.fayelau.tummy.search.store.mongo.repository.IBlabRepository;

/**
 * 粉丝牌升级业务层实现
 * 
 * @author 3g7 2019-09-09 12:44:51
 * @version 0.0.1
 *
 */
@Service
public class BlabService implements IBlabDubboService, IBlabService {

    private static final Logger logger = LoggerFactory.getLogger(BlabService.class);

    @Autowired
    private IBlabRepository blabRepository;

    @Override
    public Collection<Blab> search(Blab blab, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabService.search");
            logger.debug("params blab:" + blab);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            return this.blabRepository.search(blab, sortProperty, direction);
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
    public Collection<Blab> pageableSearch(Blab blab, Integer page, Integer size, String sortProperty,
            Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabService.search");
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            return this.blabRepository.pageableSearch(blab, page, size, sortProperty, direction);
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
    public Long count(Blab blab) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabService.count");
            logger.debug("params blab:" + blab);
        }
        try {
            if (!StringUtils.isEmpty(blab.getNickname())) {
                Blab search = new Blab();
                search.setNickname(blab.getNickname());
                Collection<Blab> blabs = blabRepository.pageableSearch(search, 1, 1, TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY,
                        Direction.DESC);
                if (blabs.isEmpty()) {
                    return 0L;
                }
                blab.setUid(blabs.iterator().next().getUid());
                blab.setNickname(null);
            }
            return this.blabRepository.count(blab);
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
