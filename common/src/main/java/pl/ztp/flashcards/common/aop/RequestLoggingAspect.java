package pl.ztp.flashcards.common.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pl.ztp.flashcards.common.utils.CommonUtils;
import pl.ztp.flashcards.common.utils.StopWatch;
import reactor.core.publisher.Mono;

@Aspect
@Component
@Log4j2
public class RequestLoggingAspect {

    private static void logRequestAndResponse(ProceedingJoinPoint joinPoint, Object o, StopWatch stopwatch) {
        String response = CommonUtils.getStringOrEmptyIfNull(o.toString());

        log.info("Request: {}.{}() with argument[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                joinPoint.getArgs());
        log.info("Response: {}.{}() had arguments = {}, with result = {}, Execution time = {} ms",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                joinPoint.getArgs()[0],
                response, stopwatch.stop());
    }

    @Around("@annotation(Loggable)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopwatch = StopWatch.start();
        var result = joinPoint.proceed();
        if (result instanceof Mono<?> monoResult)
            return monoResult
                    .doOnSuccess(o -> logRequestAndResponse(joinPoint, o, stopwatch));
        else
            return result;
    }
}