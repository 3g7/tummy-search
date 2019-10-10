package com.fayelau.tummy.search.service.impl.store;

import java.util.Collection;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.core.constants.TummySearchCommonConstants;
import com.fayelau.tummy.search.dubbo.inter.store.IUenterDubboService;
import com.fayelau.tummy.search.inter.service.store.IUenterService;
import com.fayelau.tummy.search.store.mongo.repository.IUenterRepository;
import com.fayelau.tummy.store.entity.Uenter;

/**
 * 入场业务层接口
 * 
 * @author 3g7 2019-09-09 13:01:01
 * @version 0.0.1
 *
 */
@Service
public class UenterService implements IUenterDubboService, IUenterService {

    private static final Logger logger = LoggerFactory.getLogger(UenterService.class);

    @Autowired
    private IUenterRepository uenterRepository;

    @Override
    public Collection<Uenter> search(Uenter uenter, String sortProperty, String direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterService.search");
            logger.debug("params uenter:" + uenter);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Direction d = Direction.DESC;
            if (direction.equals(TummySearchCommonConstants.DIRECTION_ASC)) {
                d = Direction.ASC;
            }
            return this.uenterRepository.search(uenter, sortProperty, d);
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
    public Collection<Uenter> pageableSearch(Uenter uenter, Integer page, Integer size, String sortProperty,
            String direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterService.search");
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Direction d = Direction.DESC;
            if (direction.equals(TummySearchCommonConstants.DIRECTION_ASC)) {
                d = Direction.ASC;
            }
            return this.uenterRepository.pageableSearch(uenter, page, size, sortProperty, d);
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
    public Long count(Uenter uenter) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterService.count");
            logger.debug("params uenter:" + uenter);
        }
        try {
            return this.uenterRepository.count(uenter);
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
