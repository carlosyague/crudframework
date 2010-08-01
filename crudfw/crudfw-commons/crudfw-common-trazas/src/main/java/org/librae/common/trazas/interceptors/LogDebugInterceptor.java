/*
 * Empresa desarrolladora: INGENIA S.A. Autor: Junta de Andalucía Derechos de
 * explotación propiedad de la Junta de Andalucía. Éste programa es software
 * libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los términos
 * de la Licencia EUPL European Public License publicada por el organismo IDABC
 * de la Comisión Europea, en su versión 1.0. o posteriores. Éste programa se
 * distribuye de buena fe, pero SIN NINGUNA GARANTÍA, incluso sin las presuntas
 * garantías implícitas de USABILIDAD o ADECUACIÓN A PROPÓSITO CONCRETO. Para
 * mas información consulte la Licencia EUPL European Public License. Usted
 * recibe una copia de la Licencia EUPL European Public License junto con este
 * programa, si por algún motivo no le es posible visualizarla, puede
 * consultarla en la siguiente URL:
 * http://ec.europa.eu/idabc/servlets/Doc?id=31099 You should have received a
 * copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096 Vous devez avoir reçu une
 * copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=31205 Sie sollten eine Kopie der
 * EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden
 * Sie da http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */
package org.librae.common.trazas.interceptors;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.librae.common.trazas.annotations.LogDebug;
import org.librae.common.trazas.annotations.spring.Bean;
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

    @Before("@annotation(org.librae.common.trazas.annotations.LogDebug)")
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

    @AfterReturning(pointcut = "@annotation(org.librae.common.trazas.annotations.LogDebug)", returning = "retVal")
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