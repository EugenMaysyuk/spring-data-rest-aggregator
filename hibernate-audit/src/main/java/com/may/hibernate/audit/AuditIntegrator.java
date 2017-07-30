package com.may.hibernate.audit;

import org.hibernate.boot.Metadata;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class AuditIntegrator implements Integrator {

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        Configuration configuration = HibernateConfigurationHolder.getConfiguration();

        if (configuration == null) { // HibernateConfigurationAspect doesn't work if AspectJ LTW is turned off
            throw new java.lang.UnsupportedOperationException("Please, check that AspectJ load-time-weaving is enabled in your project");
        }

        final EventListenerRegistry listenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        listenerRegistry.appendListeners(EventType.POST_INSERT, new AuditListener());
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // do nothing
    }

}
