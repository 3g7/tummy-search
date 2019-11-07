package com.fayelau.tummy.search.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.CommonUtils;
import com.fayelau.tummy.search.core.constants.CommonConstants;
import com.fayelau.tummy.search.core.utils.BaseSecurity;
import com.fayelau.tummy.search.entity.AnonymousAccess;
import com.fayelau.tummy.search.entity.Passport;
import com.fayelau.tummy.search.entity.Session;
import com.fayelau.tummy.search.inter.service.IAuthenticateService;
import com.fayelau.tummy.search.repository.IAnonymousAccessRepository;
import com.fayelau.tummy.search.repository.IPassportRepository;
import com.fayelau.tummy.search.repository.ISessionRepository;

/**
 * 认证业务层实现
 * 
 * @author 3g7 2019-10-12 09:47:39
 * @version 0.0.1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class AuthenticateService implements IAuthenticateService {

    public static final Logger logger = LoggerFactory.getLogger(AuthenticateService.class);

    @Autowired
    private IPassportRepository passportRepository;

    @Autowired
    private ISessionRepository sessionRepository;

    @Autowired
    private IAnonymousAccessRepository anonymousAccessRepository;

    @Value("${tummy.search.security.expiredInterval}")
    private Long expiredInterval;

    private static Long lastUpdateTimestamp = 0L;
    
    @Override
    @Transactional(readOnly = false)
    public Session authenticate(Passport passport) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AuthenticateService.authenticate");
            logger.debug("The parameter is passport:" + passport);
        }
        try {
            if (StringUtils.isEmpty(passport.getUsername())) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            if (StringUtils.isEmpty(passport.getPassword())) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            passport.setPassword(BaseSecurity.MD5(passport.getPassword(), passport.getUsername()));
            Example<Passport> passportExample = Example.of(passport);
            Optional<Passport> passportOptional = passportRepository.findOne(passportExample);
            if (!passportOptional.isPresent()) {
                throw TummyException.getException(TummyExCode.USERNAME_OR_PASSWORD_ERROR);
            }
            passport = passportOptional.get();
            if (CommonConstants.NO.equalsIgnoreCase(passport.getEnable())) {
                throw TummyException.getException(TummyExCode.DATA_NOT_ENABLE);
            }
            Session session = new Session();
            session.setPassportId(passport.getId());
            session.setToken(CommonUtils.uuid32());
            session.setExpiredTime(CommonUtils.currentMillis() + expiredInterval);
            session.setClientBrowser(BaseSecurity.getClientInfo(CommonConstants.CLIENT_INFO_BROWSER));
            session.setClientIp(BaseSecurity.getClientInfo(CommonConstants.CLIENT_INFO_IP));
            session.setClientOs(BaseSecurity.getClientInfo(CommonConstants.CLIENT_INFO_OS));
            session.setCreated(CommonUtils.currentMillis());
            session.setCreateId(BaseSecurity.currentPassportId());
            session.setEnable(CommonConstants.YES);
            session.setLocking(CommonConstants.NO);
            sessionRepository.save(session);
            return session;
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public Boolean isBelongAnonymousAccess(String requestUri, String requestMethod) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AuthenticateService.isBelongAnonymousAccess");
            logger.debug("The parameter is requestUri:" + requestUri);
            logger.debug("The parameter is requestMethod:" + requestMethod);
        }
        try {
            AnonymousAccess anonymousAccessSearch = new AnonymousAccess();
            anonymousAccessSearch.setEnable(CommonConstants.YES);
            Example<AnonymousAccess> anonymousAccessExample = Example.of(anonymousAccessSearch);
            Collection<AnonymousAccess> anonymousAccesses = anonymousAccessRepository.findAll(anonymousAccessExample);
            for (AnonymousAccess anonymousAccess : anonymousAccesses) {
                Boolean requestMethodSuccess = anonymousAccess.getRequestMethod().equalsIgnoreCase(requestMethod);
                Boolean requestUriSuccess = false;
                if (anonymousAccess.getMatchType().equals(CommonConstants.ANONYMOUS_ACCESS_MATCH_TYPE_EXACT)) {
                    requestUriSuccess = anonymousAccess.getRequestUri().equals(requestUri);
                } else if (anonymousAccess.getMatchType().equals(CommonConstants.ANONYMOUS_ACCESS_MATCH_TYPE_FUZZY)) {
                    requestUriSuccess = requestUri.contains(anonymousAccess.getRequestUri());
                }
                return requestUriSuccess && requestMethodSuccess;
            }
            return false;
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Session verifyToken(String token) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AuthenticateService.verifyToken");
            logger.debug("The parameter is token:" + token);
        }
        try {
            if (StringUtils.isEmpty(token)) {
                throw TummyException.getException(TummyExCode.PARAMETER_NULL);
            }
            Session sessionSearch = new Session();
            sessionSearch.setToken(token);
            Example<Session> sessionExample = Example.of(sessionSearch);
            Optional<Session> sessionOptional = sessionRepository.findOne(sessionExample);
            if (!sessionOptional.isPresent()) {
                throw TummyException.getException(TummyExCode.TOKEN_NOT_FOUND);
            }
            Session session = sessionOptional.get();
            if (CommonUtils.currentMillis() > session.getExpiredTime()) {
                // sessionRepository.delete(session); 会发生异常回滚 这句话无效
                throw TummyException.getException(TummyExCode.TOKEN_EXPIRE);
            }
            if (CommonUtils.currentMillis() - lastUpdateTimestamp > expiredInterval / 2) {
                session.setExpiredTime(CommonUtils.currentMillis() + expiredInterval);
                session = sessionRepository.save(session);
                lastUpdateTimestamp = CommonUtils.currentMillis();
            }
            return session;
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

    @Override
    public Passport getPassport(String token) throws TummyException {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AuthenticateService.verifyToken");
            logger.debug("The parameter is token:" + token);
        }
        try {
            Session session = this.verifyToken(token);
            if (CommonUtils.currentMillis() > session.getExpiredTime()) {
                // sessionRepository.delete(session); 会发生异常回滚 这句话无效
                throw TummyException.getException(TummyExCode.TOKEN_EXPIRE);
            }
            Optional<Passport> passportOptional = passportRepository.findById(session.getPassportId());
            if (!passportOptional.isPresent()) {
                throw TummyException.getException(TummyExCode.TOKEN_EXPIRE);
            }
            return passportOptional.get();
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            throw e;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            throw TummyException.getException(e, e.getMessage());
        }
    }

}
