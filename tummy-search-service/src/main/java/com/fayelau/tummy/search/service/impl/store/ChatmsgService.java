package com.fayelau.tummy.search.service.impl.store;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.core.constants.TummySearchDefaultConstants;
import com.fayelau.tummy.search.core.utils.TimeUtils;
import com.fayelau.tummy.search.dubbo.inter.store.IChatmsgDubboService;
import com.fayelau.tummy.search.inter.service.store.IChatmsgService;
import com.fayelau.tummy.search.store.mongo.entity.Chatmsg;
import com.fayelau.tummy.search.store.mongo.repository.IChatmsgRepository;

/**
 * 弹幕业务层实现
 * 
 * @author 3g7 2019-09-09 12:55:02
 * @version 0.0.1
 *
 */
@Service
public class ChatmsgService implements IChatmsgDubboService, IChatmsgService {

    private static final Logger logger = LoggerFactory.getLogger(ChatmsgService.class);

    @Autowired
    private IChatmsgRepository chatmsgRepository;

    @Override
    public Collection<Chatmsg> search(Chatmsg chatmsg, String sortProperty, Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgService.search");
            logger.debug("params chatmsg:" + chatmsg);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            return this.chatmsgRepository.search(chatmsg, sortProperty, direction);
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
    public Collection<Chatmsg> pageableSearch(Chatmsg chatmsg, Integer page, Integer size, String sortProperty,
            Direction direction) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgService.search");
            logger.debug("params page:" + page);
            logger.debug("params size:" + size);
            logger.debug("params sortProperty:" + sortProperty);
            logger.debug("params direction:" + direction);
        }
        try {
            return this.chatmsgRepository.pageableSearch(chatmsg, page, size, sortProperty, direction);
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
    public Long count(Chatmsg chatmsg) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgService.count");
            logger.debug("params chatmsg:" + chatmsg);
        }
        try {
            if (!StringUtils.isEmpty(chatmsg.getNickname())) {
                Chatmsg search = new Chatmsg();
                search.setNickname(chatmsg.getNickname());
                Collection<Chatmsg> chatmsgs = chatmsgRepository.pageableSearch(search, 1, 1, TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY,
                        Direction.DESC);
                if (chatmsgs.isEmpty()) {
                    return 0L;
                }
                chatmsg.setUid(chatmsgs.iterator().next().getUid());
                chatmsg.setNickname(null);
            }
            return this.chatmsgRepository.count(chatmsg);
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
    public Long countByTime(Chatmsg chatmsg, String start, String end) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgService.count");
            logger.debug("params chatmsg:" + chatmsg);
        }
        try {
            if (!StringUtils.isEmpty(chatmsg.getNickname())) {
                Chatmsg search = new Chatmsg();
                search.setNickname(chatmsg.getNickname());
                Collection<Chatmsg> chatmsgs = chatmsgRepository.pageableSearch(search, 1, 1, TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY,
                        Direction.DESC);
                if (chatmsgs.isEmpty()) {
                    return 0L;
                }
                chatmsg.setUid(chatmsgs.iterator().next().getUid());
                chatmsg.setNickname(null);
            }
            Long startTime = TimeUtils.dateStr2TimeStamp(start);
            Long endTime = TimeUtils.dateStr2TimeStamp(end);
            return this.chatmsgRepository.countByTime(chatmsg, startTime, endTime);
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
