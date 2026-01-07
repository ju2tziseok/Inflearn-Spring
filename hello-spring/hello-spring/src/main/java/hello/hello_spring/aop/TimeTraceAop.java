package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect

public class TimeTraceAop {
    @Around("execution(* hello.hello_spring..*(..))") // 공통 관심사항 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("Start: " + joinPoint.toString());
        try{
            Object result = joinPoint.proceed();
            return result;
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("END: " + joinPoint.toString()+" " + timeMs+"ms");
        }

    }
}
