package com.fayelau.tummy.search.rest.store;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.core.constants.CommonConstants;
import com.fayelau.tummy.search.core.constants.DefaultConstants;
import com.fayelau.tummy.search.core.utils.TimeUtils;
import com.fayelau.tummy.search.inter.service.business.IRankService;
import com.fayelau.tummy.search.inter.service.store.IChatmsgService;
import com.fayelau.tummy.store.entity.Chatmsg;
import com.fayelau.tummy.store.pojo.Rank;

/**
 * 弹幕消息请求
 * 
 * @author 3g7 2019-09-09 14:05:06
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("chatmsg")
public class ChatmsgRest {

    private static final Logger logger = LoggerFactory.getLogger(ChatmsgRest.class);

    @Autowired
    private IChatmsgService chatmsgService;

    @Autowired
    private IRankService rankService;

    @GetMapping
    public ResponseRange<Chatmsg> search(Chatmsg chatmsg, String sortProperty, String direction) {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRest.search");
            logger.debug("params chatmsg:" + chatmsg);
        }
        ResponseRange<Chatmsg> responseRange = new ResponseRange<>();
        try {
            if (StringUtils.isEmpty(sortProperty)) {
                sortProperty = DefaultConstants.DEFAULT_SORT_PROPERTY;
            }
            if (StringUtils.isEmpty(direction)) {
                direction = CommonConstants.DIRECTION_DESC;
            }
            Collection<Chatmsg> chatmsgs = this.chatmsgService.search(chatmsg, sortProperty, direction);
            responseRange.setData(chatmsgs);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @GetMapping(params = { "pageable=true" })
    public ResponseRange<Chatmsg> pageableSearch(Chatmsg chatmsg, Integer page, Integer size, String sortProperty,
            String direction) {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRest.pageableSearch");
            logger.debug("params chatmsg:" + chatmsg);
        }
        ResponseRange<Chatmsg> responseRange = new ResponseRange<>();
        try {
            if (page == null) {
                page = 0;
            }
            if (size == null) {
                size = 20;
            }
            responseRange.openPage(page, size);
            if (StringUtils.isEmpty(sortProperty)) {
                sortProperty = DefaultConstants.DEFAULT_SORT_PROPERTY;
            }
            if (StringUtils.isEmpty(direction)) {
                direction = CommonConstants.DIRECTION_DESC;
            }
            Collection<Chatmsg> chatmsgs = this.chatmsgService.pageableSearch(chatmsg, page, size, sortProperty,
                    direction);
            responseRange.setData(chatmsgs);
            responseRange.setTotal(this.chatmsgService.count(chatmsg));
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @GetMapping("count")
    public ResponseRange<Long> count(Chatmsg chatmsg, String start, String end) {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRest.count");
            logger.debug("params chatmsg:" + chatmsg);
        }
        ResponseRange<Long> responseRange = new ResponseRange<>();
        try {
            Long count = 0L;
            if (!StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)) {
                if (!TimeUtils.isRightDateStr(start, TimeUtils.DEFAULT_FORMAT)) {
                    throw TummyException.getException(TummyExCode.PARSE_ERROR, start);
                }
                if (!TimeUtils.isRightDateStr(end, TimeUtils.DEFAULT_FORMAT)) {
                    throw TummyException.getException(TummyExCode.PARSE_ERROR, end);
                }
                count = this.chatmsgService.countByTime(chatmsg, start, end);
            } else {
                count = this.chatmsgService.count(chatmsg);
            }
            responseRange.setOneData(count);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

    @GetMapping("rank")
    public ResponseRange<Rank> rankByTime(Long start, Long end, Long limit) {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRest.rankByTime");
            logger.debug("params start:" + start);
            logger.debug("params end:" + end);
            logger.debug("params limit:" + limit);
        }
        ResponseRange<Rank> responseRange = new ResponseRange<>();
        try {
            Collection<Rank> ranks = this.rankService.rankByTime(start, end, limit, null);
            responseRange.setData(ranks);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        }
        return responseRange;
    }

}
