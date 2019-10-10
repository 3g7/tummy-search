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
import com.fayelau.tummy.search.store.mongo.repository.INewblackresRepository;
import com.fayelau.tummy.store.entity.Newblackres;

/**
 * 
 * @author 3g7 2019-09-09 10:46:47
 * @version 0.0.1
 *
 */
@Repository
public class NewblackresRepository extends BaseRepository implements INewblackresRepository {

    private static final Logger logger = LoggerFactory.getLogger(NewblackresRepository.class);

    @Autowired
    private MongoTemplate mongoTemplate;
    
    private static final String COLLECTION_NAME = "newblackres";

    @Override
    public Collection<Newblackres> search(Newblackres newblackres, String sortProperty, Direction direction)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresRepository.search");
            logger.debug("params newblackres:" + newblackres);
        }
        try {
            Query query = buildQuery(newblackres);
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Newblackres.class, COLLECTION_NAME);
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
    public Collection<Newblackres> pageableSearch(Newblackres newblackres, Integer page, Integer size,
            String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresRepository.pageableSearch");
            logger.debug("params newblackres:" + newblackres);
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Query query = buildQuery(newblackres);
            query.with(PageRequest.of(page, size));
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            return mongoTemplate.find(query, Newblackres.class, COLLECTION_NAME);
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
    public Long count(Newblackres newblackres) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run NewblackresRepository.count");
            logger.debug("params newblackres:" + newblackres);
        }
        try {
            Query query = buildQuery(newblackres);
            return mongoTemplate.count(query, Newblackres.class, COLLECTION_NAME);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
