package com.fayelau.tummy.search.store.mongo.repository.impl;

import java.util.Set;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.fayelau.tummy.base.core.utils.CommonUtils;
import com.fayelau.tummy.search.core.constants.TummySearchDefaultConstants;
import com.mongodb.BasicDBObject;

/**
 * 通用持久化类
 * 
 * @author 3g7 2019-09-09 10:32:24
 * @version
 *
 */
public class BaseRepository {

    protected Query buildQuery(Object object) {
        Query query = new Query();
        Set<String> notNullProperties = CommonUtils.getNotNullProperties(object);
        for (String property : notNullProperties) {
            Criteria criteria = Criteria.where(property).is(CommonUtils.getFieldValueByFieldName(property, object));
            query.addCriteria(criteria);
        }
        return query;
    }
    
    protected Query buildTime(Query query, Long start, Long end) {
        Criteria criteria = Criteria.where(TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY).gte(start).lte(end);
        query.addCriteria(criteria);
        return query;
    }
    
    protected BasicDBObject buildDBQuery(Object object) {
        BasicDBObject query = new BasicDBObject();
        Set<String> notNullProperties = CommonUtils.getNotNullProperties(object);
        for (String property : notNullProperties) {
            query.put(property, CommonUtils.getFieldValueByFieldName(property, object));
        }
        return query;
    }

}
