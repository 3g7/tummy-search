package com.fayelau.tummy.search.store.mongo.repository.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.core.constants.DefaultConstants;
import com.fayelau.tummy.search.store.mongo.repository.IChatmsgRepository;
import com.fayelau.tummy.store.entity.Chatmsg;
import com.fayelau.tummy.store.pojo.Rank;

/**
 * 弹幕持久化查询实现
 * 
 * @author 3g7 2019-09-09 10:38:55
 * @version 0.0.1
 *
 */
@Repository
public class ChatmsgRepository extends BaseRepository implements IChatmsgRepository {

    private static final Logger logger = LoggerFactory.getLogger(ChatmsgRepository.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "chatmsg";

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Chatmsg> search(Chatmsg chatmsg, String sortProperty, Direction direction,
            Map<String, Object> domainParams) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRepository.search");
            logger.debug("params chatmsg:" + chatmsg);
        }
        try {
            Query query = buildQuery(chatmsg);
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            if (domainParams != null && !domainParams.isEmpty()) {
                query = buildQueryByMap(query, domainParams);
            }
            return mongoTemplate.find(query, Chatmsg.class, COLLECTION_NAME);
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
    public Collection<Chatmsg> pageableSearch(Chatmsg chatmsg, Integer page, Integer size, String sortProperty,
            Direction direction, Map<String, Object> domainParams) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRepository.pageableSearch");
            logger.debug("params chatmsg:" + chatmsg);
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Query query = buildQuery(chatmsg);
            query.with(PageRequest.of(page, size));
            if (StringUtils.isNotEmpty(sortProperty) && direction != null) {
                query.with(Sort.by(direction, sortProperty));
            }
            if (domainParams != null && !domainParams.isEmpty()) {
                query = buildQueryByMap(query, domainParams);
            }
            return mongoTemplate.find(query, Chatmsg.class, COLLECTION_NAME);
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
    public Long count(Chatmsg chatmsg, Map<String, Object> domainParams) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRepository.count");
            logger.debug("params chatmsg:" + chatmsg);
        }
        try {
            Query query = buildQuery(chatmsg);
            if (domainParams != null && !domainParams.isEmpty()) {
                query = buildQueryByMap(query, domainParams);
            }
            return mongoTemplate.count(query, Chatmsg.class, COLLECTION_NAME);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public Long countByTime(Chatmsg chatmsg, Long start, Long end, Map<String, Object> domainParams)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRepository.countByTime");
            logger.debug("params chatmsg:" + chatmsg);
            logger.debug("params start:" + start);
            logger.debug("params end:" + end);
        }
        try {
            Query query = buildTime(buildQuery(chatmsg), start, end);
            if (domainParams != null && !domainParams.isEmpty()) {
                query = buildQueryByMap(query, domainParams);
            }
            return mongoTemplate.count(query, Chatmsg.class, COLLECTION_NAME);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public Collection<Rank> rankByTime(Long start, Long end, Long limit, Map<String, Object> domainParams)
            throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRepository.countByTime");
            logger.debug("params start:" + start);
            logger.debug("params end:" + end);
            logger.debug("params limit:" + limit);
        }
        try {
            Criteria criteria = Criteria.where(DefaultConstants.DEFAULT_SORT_PROPERTY).gte(start).lte(end);
            if (domainParams != null && !domainParams.isEmpty()) {
                for (String property : domainParams.keySet()) {
                    criteria.and(property).is(domainParams.get(property));
                }
            }
            Aggregation aggregation = Aggregation
                    .newAggregation(Aggregation.match(criteria),
                            Aggregation.group("uid").last("uid").as("uid").last("nickname").as("nickname")
                                    .last("avatar").as("avatar").count().as("count"),
                            Aggregation.sort(Sort.by(Direction.DESC, "count")), Aggregation.limit(limit))
                    .withOptions(Aggregation.newAggregationOptions().allowDiskUse(true).build());
            AggregationResults<Rank> results = this.mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Rank.class);
            List<Rank> ranks = results.getMappedResults();
            for (int rankNum = 1; rankNum <= ranks.size(); rankNum++) {
                ranks.get(rankNum - 1).setRank(rankNum);
            }
            return ranks;  
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
