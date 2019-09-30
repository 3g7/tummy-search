package com.fayelau.tummy.search.service.impl.store;

import java.util.Collection;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.dubbo.inter.store.INewblackresDubboService;
import com.fayelau.tummy.search.inter.service.store.INewblackresService;
import com.fayelau.tummy.search.store.mongo.entity.Newblackres;
import com.fayelau.tummy.search.store.mongo.repository.INewblackresRepository;

/**
 * 黑名单回执业务层接口
 * 
 * @author 3g7 2019-09-09 12:58:44
 * @version 0.0.1
 *
 */
@Service
public class NewblackresService implements INewblackresDubboService, INewblackresService {

    private static final Logger logger = LoggerFactory.getLogger(NewblackresService.class);

    @Autowired
    private INewblackresRepository newblackresRepository;

    @Override
    public Collection<Newblackres> search(Newblackres newblackres, String sortProperty, Direction direction)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresService.search");
            logger.debug("params newblackres:" + newblackres);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            return this.newblackresRepository.search(newblackres, sortProperty, direction);
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
    public Collection<Newblackres> pageableSearch(Newblackres newblackres, Integer page, Integer size,
            String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresService.search");
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            return this.newblackresRepository.pageableSearch(newblackres, page, size, sortProperty, direction);
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
    public Long count(Newblackres newblackres) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresService.count");
            logger.debug("params newblackres:" + newblackres);
        }
        try {
            return this.newblackresRepository.count(newblackres);
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
