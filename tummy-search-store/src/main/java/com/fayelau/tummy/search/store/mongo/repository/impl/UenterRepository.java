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
import com.fayelau.tummy.search.store.mongo.repository.IUenterRepository;
import com.fayelau.tummy.store.entity.Rss;
import com.fayelau.tummy.store.entity.Uenter;

/**
 * 进场消息持久化查询实现
 * 
 * @author 3g7 2019-09-09 10:54:15
 * @version 0.0.1
 *
 */
@Repository
public class UenterRepository extends BaseRepository implements IUenterRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(UenterRepository.class);

    @Autowired
    private MongoTemplate mongoTemplate;
    
    private static final String COLLECTION_NAME = "uenter";

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Uenter> search(Uenter uenter, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterRepository.search");
            logger.debug("params uenter:" + uenter);
        }
        try {
            Query query = buildQuery(uenter);
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Uenter.class, COLLECTION_NAME);
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
    public Collection<Uenter> pageableSearch(Uenter uenter, Integer page, Integer size, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterRepository.pageableSearch");
            logger.debug("params uenter:" + uenter);
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Query query = buildQuery(uenter);
            query.with(PageRequest.of(page, size));
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Uenter.class, COLLECTION_NAME);
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
    public Long count(Uenter uenter) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run UenterRepository.count");
            logger.debug("params uenter:" + uenter);
        }
        try {
            Query query = buildQuery(uenter);
            return mongoTemplate.count(query, Rss.class, COLLECTION_NAME);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
