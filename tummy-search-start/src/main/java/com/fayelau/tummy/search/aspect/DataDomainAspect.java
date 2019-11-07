package com.fayelau.tummy.search.aspect;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fayelau.tummy.base.core.exception.TummyExCode;
import com.fayelau.tummy.base.core.exception.TummyException;
import com.fayelau.tummy.search.core.utils.BaseSecurity;
import com.fayelau.tummy.search.entity.DataDomain;
import com.fayelau.tummy.search.entity.PassportDataDomainRelation;
import com.fayelau.tummy.search.inter.service.IDataDomainService;
import com.fayelau.tummy.search.inter.service.IPassportDataDomainRelationService;

/**
 * 数据分域切面
 * 
 * @author 3g7 2019-10-12 15:30:03
 * @version 0.0.1
 *
 */
@Aspect
@Component
public class DataDomainAspect {

    @Autowired
    private IDataDomainService dataDomainService;

    @Autowired
    private IPassportDataDomainRelationService passportDataDomainRelationService;

//    @Pointcut("execution(* com.*.*.*.service.impl.store..*.*(..))")
//    public void serviceStore() {
//
//    }
//    
//    @Pointcut("execution(* com.*.*.*.service.impl.business..*.*(..))")
//    public void serviceBusiness() {
//
//    }
    
    @Pointcut("execution(* com.*.*.*.store.mongo.repository.impl..*.*(..))")
    public void responseStore() {

    }

//    @Around("serviceStore() || serviceBusiness()")
//    public Object dataDomainAspectAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        if (args == null) {
//            return joinPoint.proceed(args);
//        }
//        Object businessObject = args[0];
//        if (businessObject == null) {
//            return joinPoint.proceed(args);
//        }
//        String target = businessObject.getClass().getName();
//        PassportDataDomainRelation passportDataDomainRelationSearch = new PassportDataDomainRelation();
//        passportDataDomainRelationSearch.setPassportId(BaseSecurity.currentPassportId());
//        passportDataDomainRelationSearch.setTarget(target);
//        Collection<PassportDataDomainRelation> passportDataDomainRelations = passportDataDomainRelationService
//                .search(passportDataDomainRelationSearch);
//        if (!passportDataDomainRelations.isEmpty()) {
//            for (PassportDataDomainRelation passportDataDomainRelation : passportDataDomainRelations) {
//                try {
//                    DataDomain dataDomain = dataDomainService.getById(passportDataDomainRelation.getDataDomainId());
//                    if (dataDomain == null) continue;
//                    Field field = businessObject.getClass().getSuperclass().getDeclaredField(dataDomain.getDomainField());
//                    field.setAccessible(true);
//                    field.set(businessObject, dataDomain.getDomainValue());
//                    args[0] = businessObject;
//                } catch (Throwable e) {
//                    e.printStackTrace();
//                    throw TummyException.getException(TummyExCode.DATA_DOMAIN_ASPECT_ERROR);
//                }
//            }
//        }
//        return joinPoint.proceed(args);
//    }
    
    @Around("responseStore()")
    public Object dataDomainAspectAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return joinPoint.proceed(args);
        }
        Object businessObject = args[0];
        if (businessObject == null) {
            return joinPoint.proceed(args);
        }
        String target = businessObject.getClass().getName();
        PassportDataDomainRelation passportDataDomainRelationSearch = new PassportDataDomainRelation();
        passportDataDomainRelationSearch.setPassportId(BaseSecurity.currentPassportId());
        passportDataDomainRelationSearch.setTarget(target);
        Collection<PassportDataDomainRelation> passportDataDomainRelations = passportDataDomainRelationService
                .search(passportDataDomainRelationSearch);
        if (!passportDataDomainRelations.isEmpty()) {
            Map<String, Object> domainParams = new HashMap<>();
            for (PassportDataDomainRelation passportDataDomainRelation : passportDataDomainRelations) {
                try {
                    DataDomain dataDomain = dataDomainService.getById(passportDataDomainRelation.getDataDomainId());
                    if (dataDomain == null) continue;
                    domainParams.put(dataDomain.getDomainField(), dataDomain.getDomainValue());
                } catch (Throwable e) {
                    e.printStackTrace();
                    throw TummyException.getException(TummyExCode.DATA_DOMAIN_ASPECT_ERROR);
                }
            }
            args[args.length - 1] = domainParams;
        }
        return joinPoint.proceed(args);
    }

}
