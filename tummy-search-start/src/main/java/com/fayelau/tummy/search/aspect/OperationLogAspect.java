package com.fayelau.tummy.search.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fayelau.tummy.base.core.utils.CommonUtils;
import com.fayelau.tummy.base.core.utils.ResponseRange;
import com.fayelau.tummy.search.core.constants.CommonConstants;
import com.fayelau.tummy.search.core.utils.BaseSecurity;
import com.fayelau.tummy.search.entity.OperationLog;
import com.fayelau.tummy.search.entity.Passport;
import com.fayelau.tummy.search.entity.Session;
import com.fayelau.tummy.search.inter.service.IOperationLogService;

@Aspect
@Component
public class OperationLogAspect {

	private OperationLog operationLog;
	
	@Autowired
	private IOperationLogService operationLogService;

	@Pointcut("execution(* com.*.*.*.rest..*.*(..))")
	public void pointCut() {

	}

	private boolean checkMethod(){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String method = request.getMethod();
		if ("GET".equalsIgnoreCase(method)){
			return false;
		}
		return true;
	}

	@Before("pointCut()")
	public void operationLogAspectBefore(JoinPoint joinPoint) {
		if (checkMethod()){
			operationLog = new OperationLog();
			operationLog.setOperationTimestamp(CommonUtils.currentMillis());
			String operator = "UNKNOWN";
			Session session = BaseSecurity.currentSession();
			if (session != null) {
				operationLog.setIp(session.getClientIp());
				operationLog.setBrowser(session.getClientBrowser());
				operationLog.setOs(session.getClientOs());
				operator = session.getNickname() + "(" + session.getUsername() + ")";
			} else {
				operationLog.setIp(BaseSecurity.getClientInfo(CommonConstants.CLIENT_INFO_IP));
				operationLog.setBrowser(BaseSecurity.getClientInfo(CommonConstants.CLIENT_INFO_BROWSER));
				operationLog.setOs(BaseSecurity.getClientInfo(CommonConstants.CLIENT_INFO_OS));
			}
			Signature signature = joinPoint.getSignature();
			if (signature.getName().equalsIgnoreCase("authenticate")){
				operator = "试图使用账号" + ((Passport)joinPoint.getArgs()[0]).getUsername() + "登录";
			}
//			if (signature.getName().equalsIgnoreCase("verify")){
//				operator = "试图使用" + ((Passport)joinPoint.getArgs()[0]).getUsername() + "进行Api鉴权";
//			}
			operationLog.setOperator(operator);
			operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
			String operationName = signature.getName();
			if (joinPoint.getArgs() != null) {
				String argsType = "";
				for (Object object: joinPoint.getArgs()){
					if (object.equals(joinPoint.getArgs()[joinPoint.getArgs().length -1 ])){
						argsType = object.getClass().getSimpleName();
					} else {
						argsType = object.getClass().getSimpleName() + "/";
					}
				}
				operationName = signature.getName() + "-" + argsType;
			}
			operationLog.setOperationName(operationName);
		}
	}

	@AfterReturning(returning = "ret", pointcut = "pointCut()")
	public void operationLogAspectAfterReturning(Object ret) throws Throwable {
		if (checkMethod()){
			if (ret instanceof ResponseRange){
				ResponseRange<?> responseRange = (ResponseRange<?>)ret;
				if(responseRange.isSuccess()){
					operationLog.setOperationType(CommonConstants.YES);
					operationLogService.save(operationLog);
				} else {
					operationLog.setOperationType(CommonConstants.NO);
					if (responseRange.getMessage().length() > 64){
						operationLog.setExceptionMessage(responseRange.getMessage().substring(0, 63));
					} else {
						operationLog.setExceptionMessage(responseRange.getMessage());
					}
					operationLogService.save(operationLog);
				}
			}

		}
	}
	
}
