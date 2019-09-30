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
import com.fayelau.tummy.search.store.mongo.entity.Blab;
import com.fayelau.tummy.search.store.mongo.repository.IBlabRepository;

/**
 * 粉丝牌升级持久化查询实现
 * 
 * @author 3g7 2019-09-07 15:18:30
 * @version 0.0.1
 *
 */
@Repository
public class BlabRepository extends BaseRepository implements IBlabRepository {

    private static final Logger logger = LoggerFactory.getLogger(BlabRepository.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Blab> search(Blab blab, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabRepository.search");
            logger.debug("params blab:" + blab);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Query query = buildQuery(blab);
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Blab.class);
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
    public Collection<Blab> pageableSearch(Blab blab, Integer page, Integer size, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabRepository.pageableSearch");
            logger.debug("params blab:" + blab);
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Query query = buildQuery(blab);
            query.with(PageRequest.of(page, size));
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Blab.class);
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
    public Long count(Blab blab) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run BlabRepository.count");
            logger.debug("params blab:" + blab);
        }
        try {
            Query query = buildQuery(blab);
            return mongoTemplate.count(query, Blab.class);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
