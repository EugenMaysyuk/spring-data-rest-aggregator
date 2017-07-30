package com.may.hibernate.audit;

import org.hibernate.cfg.Configuration;

/**
 * This class holds the {@link org.hibernate.cfg.Configuration} object that will be used
 * by {@link AuditIntegrator} at runtime.
 *
 * @author Eugen Maysyuk
 */
public abstract class HibernateConfigurationHolder {

    private static Configuration configuration;

    public static void collectConfiguration(Object config) {
        configuration = (Configuration) config;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
