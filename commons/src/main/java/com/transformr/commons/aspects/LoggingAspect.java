package com.transformr.commons.aspects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.transformr.commons.constants.CommonConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class LoggingAspect {

	@Pointcut(
		"@annotation(org.springframework.context.annotation.Bean) && " +
		"execution(* com.transformr.*.config.*.*(..))"
	)
	private void configurations() {
		// This method defines the pointcut for the Transformr services
	}

	@Before(value = "configurations()")
	public void logBeforeConfigurations(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		MDC.put(CommonConstants.JOIN_POINT, signature.getDeclaringType().getSimpleName());
		log.info("Initializing: [{}]", signature.getName());
		MDC.clear();
	}

	@Pointcut("execution(* com.transformr.*.controllers.*.*(..))")
	private void controllers() {
		// This method defines the pointcut for the Transformr services
	}

	@Before(value = "controllers()")
	public void logBeforeControllers(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		Signature signature = joinPoint.getSignature();

		MDC.put(CommonConstants.JOIN_POINT, signature.getDeclaringType().getSimpleName());
		String params = new ArrayList<>(Arrays.asList(joinPoint.getArgs())).stream().map(String::valueOf).collect(Collectors.joining(", "));
		log.info("[{}] called from IP Address [{}] with arguments [{}]", request.getRequestURL(), request.getAttribute("ipAddress"), params);
		MDC.clear();
	}

	@AfterReturning(value = "controllers()")
	public void logAfterControllersReturning(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		MDC.put(CommonConstants.JOIN_POINT, signature.getDeclaringType().getSimpleName());
		log.info("Exiting: [{}]", signature.getName());
		MDC.clear();
	}

	@Pointcut("execution(* com.transformr.*.services.*.*(..))")
	private void services() {
		// This method defines the pointcut for the Transformr services
	}

	@Before(value = "services()")
	public void logBeforeServices(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		MDC.put(CommonConstants.JOIN_POINT, signature.getDeclaringType().getSimpleName());
		String params = new ArrayList<>(Arrays.asList(joinPoint.getArgs())).stream().map(String::valueOf).collect(Collectors.joining(", "));
		log.info("Entering: [{}] with arguments [{}]", signature.getName(), params);
		MDC.clear();
	}

	@AfterReturning(value = "services()", returning = "result")
	public void logAfterServicesReturning(JoinPoint joinPoint, Object result) {
		Signature signature = joinPoint.getSignature();
		MDC.put(CommonConstants.JOIN_POINT, signature.getDeclaringType().getSimpleName());
		log.info("Exiting: [{}] with return value [{}]", signature.getName(), result);
		MDC.clear();
	}

	@AfterThrowing(value = "controllers() || services()", throwing = "ex")
	public void logAfterMethodsThrowing(JoinPoint joinPoint, Exception ex) {
		Signature signature = joinPoint.getSignature();
		MDC.put(CommonConstants.JOIN_POINT, signature.getDeclaringType().getSimpleName());
		log.error(ex.getMessage(), ex.getCause());
		MDC.clear();
	}
}
