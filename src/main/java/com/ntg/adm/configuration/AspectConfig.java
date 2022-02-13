package com.ntg.adm.configuration;



import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {
	Logger logger = LoggerFactory.getLogger(AspectConfig.class);
	
	@Pointcut("execution(* com.ntg.adm.model.*.get*())")
	private void getter() {}
	
	@Pointcut("execution(public void com.ntg.adm.model.*.set*(..))")
	private void setter() {}
	
	@Pointcut("!(getter() && setter())")
	private void noGetterAndSetter() {}
	
	
	@Pointcut("execution(* com.ntg.adm.*.*(..))")
	private void logMethodExecution() {}
	
	@Around("execution(* com.ntg.adm..*(..))")
	public Object logMethodExecution(ProceedingJoinPoint pjp) throws Throwable  {
		long begin = System.currentTimeMillis();

		Object output = pjp.proceed();
		
		logDetails(pjp, begin);
		
		return output;
	}
	
	
	private StringBuilder logDetails(ProceedingJoinPoint pjp, long begin) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		
		StringBuilder stringBuilder = new StringBuilder(">> [audit] method name[" + pjp.getSignature().getName() + "]");
		
		stringBuilder.append("\tparameter names");
		Arrays.stream(signature.getParameterNames()).forEach(p -> stringBuilder.append("[").append(p).append("]"));
		
		stringBuilder.append("\tparameter types");
		Arrays.stream(signature.getParameterTypes()).forEach(t -> stringBuilder.append("[").append(t).append("]"));
		
		stringBuilder.append("\tparameter values");
		Arrays.stream(pjp.getArgs()).forEach(o -> stringBuilder.append("[").append(o.toString()).append("]"));
		
		stringBuilder.append("\ttook [" + (System.currentTimeMillis() - begin) + "] milliseconds.");
		logger.info(stringBuilder.toString());
		return stringBuilder;
	}
}
