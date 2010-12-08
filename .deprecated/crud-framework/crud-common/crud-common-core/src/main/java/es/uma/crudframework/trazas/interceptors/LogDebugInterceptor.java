package es.uma.crudframework.trazas.interceptors;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import es.uma.crudframework.trazas.annotations.LogDebug;
import es.uma.crudframework.trazas.annotations.spring.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Bean(name = "logDebug")
/**
 *
 */
public class LogDebugInterceptor {
    @SuppressWarnings("finally")
    protected Method getMethod(JoinPoint jp) {
        Method invoked = null;
        try {
            final MethodSignature met = (MethodSignature) jp.getSignature();
            invoked = jp.getSourceLocation().getWithinType().getMethod(
                    met.getMethod().getName(),
                    met.getMethod().getParameterTypes());
        } catch (NoSuchMethodException e) {
            final Logger logger = this.getLog(jp);
            logger.debug("invoked: " + invoked);
        } finally {
            final Logger logger = this.getLog(jp);
            logger.debug("invoked: " + invoked);
        }
        return invoked;
    }

    @SuppressWarnings("unchecked")
    /**
     *
     */
    protected Logger getLog(JoinPoint jp) {
        Logger logger = null;
        try {
            final LogDebug logdebug = this.getMethod(jp).getAnnotation(
                    LogDebug.class);
            Class clazz = logdebug.loggerClass();
            if (clazz == null) {
                clazz = LogDebugInterceptor.class;
            }
            logger = LoggerFactory.getLogger(clazz);
        } finally {
            if (logger == null) {
                logger = LoggerFactory.getLogger(LogDebugInterceptor.class);
            }
        }
        return logger;
    }

    @Before("@annotation(es.uma.crudframework.trazas.annotations.LogDebug)")
    /**
     *
     */
    public void beforeLog(JoinPoint jp) {
        final Logger logger = this.getLog(jp);
        if (logger.isDebugEnabled()) {
            logger.debug("{}", this.getMethod(jp));
            for (Object o : jp.getArgs()) {
                if (o != null) {
                    logger.debug("PARAMETER " + o.getClass().getSimpleName());
                    logger.debug(o.toString());
                }
            }
        }
    }

    @AfterReturning(pointcut = "@annotation(es.uma.crudframework.trazas.annotations.LogDebug)", returning = "retVal")
    /**
     *
     */
    public void afterLog(JoinPoint jp, Object retVal) {
        final Logger logger = this.getLog(jp);
        if (logger.isDebugEnabled()) {
            logger.debug("{}", retVal);
        }
    }
}