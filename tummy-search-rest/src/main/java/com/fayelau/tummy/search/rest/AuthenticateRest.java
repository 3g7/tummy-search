package com.fayelau.tummy.search.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.entity.Passport;
import com.fayelau.tummy.search.entity.Session;
import com.fayelau.tummy.search.inter.service.IAuthenticateService;

/**
 * 认证请求
 * 
 * @author 3g7 2019-10-12 14:09:39
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("auth")
public class AuthenticateRest {
    
    public static final Logger logger = LoggerFactory.getLogger(AuthenticateRest.class);

    @Autowired
    private IAuthenticateService authenticateService;
    
    @PostMapping
    public ResponseRange<Session> authenticate(Passport passport) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AuthenticateRest.authenticate");
            logger.debug("The parameter is passport:" + passport);
        }
        ResponseRange<Session> responseRange = new ResponseRange<>();
        try {
            passport.setUsername(passport.getUsername().replace(" ", ""));
            passport.setPassword(passport.getPassword().replace(" ", ""));
            Session session = authenticateService.authenticate(passport);
            responseRange.setOneData(session);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }
    
    @GetMapping("get_passport")
    public ResponseRange<Passport> getPassport(String token) {
        if (logger.isDebugEnabled()) {
            logger.debug("Currently executing function AuthenticateRest.get_passport");
            logger.debug("The parameter is token:" + token);
        }
        ResponseRange<Passport> responseRange = new ResponseRange<>();
        try {
            Passport passport = authenticateService.getPassport(token);
            responseRange.setOneData(passport);
        } catch (TummyException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            responseRange.setException(e);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
            responseRange.setException(e);
        }
        return responseRange;
    }


}
