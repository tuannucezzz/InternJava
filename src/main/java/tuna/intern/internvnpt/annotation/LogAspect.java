package tuna.intern.internvnpt.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Around("@annotation(Log)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String name = (String) proceedingJoinPoint.getArgs()[0];

        System.out.println(String.format("LOG: hello %s", name));

        return proceedingJoinPoint.proceed();
    }
}
