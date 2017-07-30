package com.may.hibernate.audit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class AuditListener implements PostInsertEventListener {

    private static final Log logger = LogFactory.getLog(AuditListener.class);

    public void onPostInsert(PostInsertEvent event) {
        logger.debug(event.getPersister().getEntityName());
    }


    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return persister.getEntityName().toLowerCase().contains("audit");
    }
}