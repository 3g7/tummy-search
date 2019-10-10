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
import com.fayelau.tummy.base.core.utils.CommonConstants;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.core.constants.TummySearchDefaultConstants;
import com.fayelau.tummy.search.core.utils.TimeUtils;
import com.fayelau.tummy.search.inter.service.store.IChatmsgService;
import com.fayelau.tummy.store.entity.Chatmsg;

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

    @GetMapping
    public ResponseRange<Chatmsg> search(Chatmsg chatmsg) {
        if (logger.isDebugEnabled()) {
            logger.debug("run ChatmsgRest.search");
            logger.debug("params chatmsg:" + chatmsg);
        }
        ResponseRange<Chatmsg> responseRange = new ResponseRange<>();
        try {
            Collection<Chatmsg> chatmsgs = this.chatmsgService.search(chatmsg,
                    TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY, CommonConstants.DIRECTION_DESC);
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
    public ResponseRange<Chatmsg> pageableSearch(Chatmsg chatmsg, Integer page, Integer size) {
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
            Collection<Chatmsg> chatmsgs = this.chatmsgService.pageableSearch(chatmsg, page, size,
                    TummySearchDefaultConstants.DEFAULT_SORT_PROPERTY, CommonConstants.DIRECTION_DESC);
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
            logger.debug("run BlabRest.count");
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

}
