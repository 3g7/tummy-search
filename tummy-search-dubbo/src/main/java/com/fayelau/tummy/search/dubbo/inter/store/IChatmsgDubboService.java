package com.fayelau.tummy.search.dubbo.inter.store;

import java.util.Collection;

import org.springframework.data.domain.Sort.Direction;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.store.mongo.entity.Chatmsg;

/**
 * 弹幕信息业务层接口
 * 
 * @author 3g7 2019-09-09 11:58:33
 * @version 0.0.1
 *
 */
public interface IChatmsgDubboService {

    /**
     * 分页查询实体集合
     * 
     * @param chatmsg
     * @param page
     * @param size
     * @return
     * @throws TummyException
     */
    public Collection<Chatmsg> pageableSearch(Chatmsg chatmsg, Integer page, Integer size, String sortProperty,
            Direction direction) throws TummyException;

    /**
     * 查询数据条数根据条件
     * 
     * @param chatmsg
     * @return
     * @throws TummyException
     */
    public Long count(Chatmsg chatmsg) throws TummyException;

}
