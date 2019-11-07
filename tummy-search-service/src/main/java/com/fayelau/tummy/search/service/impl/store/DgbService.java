package com.fayelau.tummy.search.service.impl.store;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.core.constants.CommonConstants;
import com.fayelau.tummy.search.core.constants.DefaultConstants;
import com.fayelau.tummy.search.dubbo.inter.store.IDgbDubboService;
import com.fayelau.tummy.search.inter.service.store.IDgbService;
import com.fayelau.tummy.search.store.mongo.repository.IDgbRepository;
import com.fayelau.tummy.store.entity.Dgb;

/**
 * 礼物业务层接口
 * 
 * @author 3g7 2019-09-09 12:57:37
 * @version 0.0.1
 *
 */
@Service
public class DgbService implements IDgbDubboService, IDgbService {
    
    private static final Logger logger = LoggerFactory.getLogger(DgbService.class);

    @Autowired
    private IDgbRepository dgbRepository;

    @Override
    public Collection<Dgb> search(Dgb dgb, String sortProperty, String direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbService.search");
            logger.debug("params dgb:" + dgb);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Direction d = Direction.DESC;
            if (direction.equals(CommonConstants.DIRECTION_ASC)) {
                d = Direction.ASC;
            }
            return this.dgbRepository.search(dgb, sortProperty, d, null);
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
    public Collection<Dgb> pageableSearch(Dgb dgb, Integer page, Integer size, String sortProperty,
            String direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbService.search");
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            Direction d = Direction.DESC;
            if (direction.equals(CommonConstants.DIRECTION_ASC)) {
                d = Direction.ASC;
            }
            return this.dgbRepository.pageableSearch(dgb, page, size, sortProperty, d, null);
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
    public Long count(Dgb dgb) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run DgbService.count");
            logger.debug("params dgb:" + dgb);
        }
        try {
            if (!StringUtils.isEmpty(dgb.getNickname())) {
                Dgb search = new Dgb();
                search.setNickname(dgb.getNickname());
                Collection<Dgb> dgbs = dgbRepository.pageableSearch(search, 1, 1, DefaultConstants.DEFAULT_SORT_PROPERTY,
                        Direction.DESC, null);
                if (dgbs.isEmpty()) {
                    return 0L;
                }
                dgb.setUid(dgbs.iterator().next().getUid());
                dgb.setNickname(null);
            }
            return this.dgbRepository.count(dgb, null);
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
