package com.may.hibernate.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * This aspect needs to catch {@link org.hibernate.cfg.Configuration} object before session factory is built
 * since Hibernate migrated to the new bootstrap API and Hibernate doesn't provide
 * {@link org.hibernate.cfg.Configuration} object to {@link AuditIntegrator} object as parameter.
 *
 * @author Eugen Maysyuk
 */
@Aspect
public class HibernateConfigurationAspect {

    /**
     * Pointcut
     */
    @Pointcut("execution(* org.hibernate.cfg.Configuration.buildSessionFactory(org.hibernate.service.ServiceRegistry))")
    public void buildSessionFactory() {
    }

    /**
     * Advice
     */
    @Around("buildSessionFactory()")
    public Object beforeAdvice(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object configuration = thisJoinPoint.getThis(); // org.hibernate.cfg.Configuration
        HibernateConfigurationHolder.collectConfiguration(configuration);
        return thisJoinPoint.proceed(thisJoinPoint.getArgs());
    }
}
