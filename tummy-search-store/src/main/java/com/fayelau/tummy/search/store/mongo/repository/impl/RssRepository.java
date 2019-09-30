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
import com.fayelau.tummy.search.store.mongo.entity.Rss;
import com.fayelau.tummy.search.store.mongo.repository.IRssRepository;

/**
 * 开关播消息持久化查询实现
 * 
 * @author 3g7 2019-09-09 10:51:16
 * @version 0.0.1
 *
 */
@Repository
public class RssRepository extends BaseRepository implements IRssRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(RssRepository.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Rss> search(Rss rss, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssRepository.search");
            logger.debug("params rss:" + rss);
        }
        try {
            Query query = buildQuery(rss);
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Rss.class);
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
    public Collection<Rss> pageableSearch(Rss rss, Integer page, Integer size, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssRepository.pageableSearch");
            logger.debug("params rss:" + rss);
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Query query = buildQuery(rss);
            query.with(PageRequest.of(page, size));
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Rss.class);
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
    public Long count(Rss rss) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run RssRepository.count");
            logger.debug("params rss:" + rss);
        }
        try {
            Query query = buildQuery(rss);
            return mongoTemplate.count(query, Rss.class);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
