package com.fayelau.tummy.search.store.mongo.repository.impl;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.store.mongo.entity.Dgb;
import com.fayelau.tummy.search.store.mongo.repository.IDgbRepository;

/**
 * 礼物持久化查询实现
 * 
 * @author 3g7 2019-09-09 10:42:04
 * @version 0.0.1
 *
 */
@Repository
public class DgbRepository extends BaseRepository implements IDgbRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(DgbRepository.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Dgb> search(Dgb dgb, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbRepository.search");
            logger.debug("params dgb:" + dgb);
        }
        try {
            Query query = buildQuery(dgb);
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Dgb.class);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Dgb> pageableSearch(Dgb dgb, Integer page, Integer size, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbRepository.pageableSearch");
            logger.debug("params dgb:" + dgb);
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Query query = buildQuery(dgb);
            query.with(PageRequest.of(page, size));
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Dgb.class);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long count(Dgb dgb) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbRepository.count");
            logger.debug("params dgb:" + dgb);
        }
        try {
            Query query = buildQuery(dgb);
            return mongoTemplate.count(query, Dgb.class);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
