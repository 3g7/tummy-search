package com.fayelau.tummy.search.inter.service;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.entity.Passport;
import com.fayelau.tummy.search.entity.Session;

/**
 * 认证接口
 * 
 * @author 3g7 2019-10-12 09:41:37
 * @version 0.0.1
 *
 */
public interface IAuthenticateService {

    /**
     * 身份认证
     * 
     * @param passport
     * @return
     * @throws TummyException
     */
    public Session authenticate(Passport passport) throws TummyException;

    /**
     * 是否属于匿名访问
     * 
     * @param anonymousAccess
     * @return
     * @throws TummyException
     */
    public Boolean isBelongAnonymousAccess(String requestUri, String requestMethod) throws TummyException;

    /**
     * 验证Token，验证成功返回会话信息，验证失败返回null
     * 
     * @param token
     * @return
     * @throws TummyException
     */
    public Session verifyToken(String token) throws TummyException;
    
    /**
     * 获取通行证信息
     * @param token
     * @return
     * @throws TummyException
     */
    public Passport getPassport(String token) throws TummyException;

}
