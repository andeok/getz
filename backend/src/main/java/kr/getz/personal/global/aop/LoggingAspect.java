package kr.getz.personal.global.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* kr.getz.personal..controller..*(..))")
	public void controllerPointcut() {
	}

	@Around("controllerPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = (attributes != null) ? attributes.getRequest() : null;

		String uri = (request != null) ? request.getRequestURI() : "Unknown URI";
		String method = (request != null) ? request.getMethod() : "Unknown Method";
		String ip = (request != null) ? request.getRemoteAddr() : "Unknown IP";

		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getName();

		long startTime = System.currentTimeMillis();

		log.info("[REQ] {} {} | IP: {} | Method: {}.{} | Params: {}",
			method, uri, ip, className, methodName, Arrays.toString(joinPoint.getArgs()));

		Object result;
		try {
			result = joinPoint.proceed();
		} catch (Throwable throwable) {
			log.error("[ERR] {} {} | {} | Exception: {}", method, uri,
				(System.currentTimeMillis() - startTime) + "ms", throwable.getMessage());
			throw throwable;
		}

		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		log.info("[RES] {} {} | Time: {}ms | Result: {}",
			method, uri, executionTime, result);

		return result;
	}
}
