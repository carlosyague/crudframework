package org.librae.common.webapp.jsf.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * The OpenSessionInViewPhaseListener ensures that a transaction is active
 * during the JSF lifecycle. A new transaction will be created if required
 * before entering the RESTORE_VIEW phase and will be committed or rolled back
 * after the INVOKE_APPLICATION phase. Another read only transaction will be
 * created before the RENDER_VIEW phase and will be commited after the phase.
 * The structural code was strongly inspired by JBoss's
 * TransactionalSeamPhaseListener written by Gavin King. (except for the
 * transaction management part, which is Spring speficic)
 * 
 * @author Vincent Gigu�re - mailto:vincent.giguere@gmail.com
 * @since February 20th 2007
 */
public class OpenEntityManagerInViewPhaseListener implements PhaseListener {

    private static final long      serialVersionUID        = 3742147752036645123L;

    private final static Logger    logger                  = Logger
                                                                   .getLogger(OpenEntityManagerInViewPhaseListener.class);

    ThreadLocal<TransactionStatus> syncedTransactionStatus = new ThreadLocal<TransactionStatus>();

    // Spring support for transaction management
    PlatformTransactionManager     transactionManager;

    EntityManagerFactory           entityManagerFactory;

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(
            PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * This method will create a new transaction if none is active. If the JSF
     * cycle is in a RENDER_RESPONSE phase, the transaction will be read-only.
     * 
     * @param phaseId
     *            The JSF lifecycle phase.
     */
    private void beginTransactionIfNoneActive(PhaseId phaseId) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            if (logger.isDebugEnabled()) {
                logger.debug("A Transaction is already active. Before phase"
                        + phaseId);
            }
        } else {
            final boolean readOnlyTransaction = (phaseId == PhaseId.RENDER_RESPONSE);

            if (logger.isDebugEnabled()) {
                logger.debug("Beginin a new "
                        + (readOnlyTransaction ? "read-only" : "write")
                        + " Transaction.  Phase: " + phaseId);
            }

            final DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def
                    .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            def.setReadOnly(readOnlyTransaction);
            TransactionSynchronizationManager.bindResource(
                    entityManagerFactory, new EntityManagerHolder(
                            entityManagerFactory.createEntityManager()));
            syncedTransactionStatus.set(transactionManager.getTransaction(def));
        }
    }

    /**
     * This method will commit or rollback the current transaction, depending on
     * its state. If an exception is thrown, it will be swallowed.
     * 
     * @param phaseId
     *            The phase ID in which this is taking place.
     */
    void commitOrRollbackActiveTransaction(PhaseId phaseId) {

        final TransactionStatus status = syncedTransactionStatus.get();
        if (status != null) {
            try {
                if (status.isCompleted()
                        || status.isRollbackOnly()
                        || TransactionSynchronizationManager
                                .isCurrentTransactionReadOnly()) {
                    if (status.isRollbackOnly()
                            || TransactionSynchronizationManager
                                    .isCurrentTransactionReadOnly()) {
                        logger.debug("rolling back transaction after phase: "
                                + phaseId);
                        transactionManager.rollback(status);
                    } else {
                        logger.debug("Committing transaction. phase: "
                                + phaseId);
                        transactionManager.commit(status);
                    }
                }
            } catch (TransactionException e) {
                logger.error(e.getMessage());
                throw e;
            } finally {
                TransactionSynchronizationManager
                        .unbindResource(entityManagerFactory);
            }
        }
    }

    public void beforePhase(PhaseEvent event) {

        final PhaseId phaseId = event.getPhaseId();
        logger.debug("Before phase: " + phaseId);

        try {
            if (phaseId == PhaseId.RESTORE_VIEW
                    || phaseId == PhaseId.RENDER_RESPONSE) {
                beginTransactionIfNoneActive(phaseId);
            }
        } catch (Exception e) {
            logger.error("Exception thrown before phase:" + event.getPhaseId(),
                    e);
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * This method will invoke a transaction commit or rollback in the phase
     * INVOKE_APPLICATION or RENDER_RESPONSE is finished or if there was an
     * error rendering the view.
     */
    public void afterPhase(PhaseEvent event) {
        logger.debug("After phase: " + event.getPhaseId());

        try {
            if (event.getPhaseId() == PhaseId.INVOKE_APPLICATION
                    || event.getFacesContext().getRenderResponse()
                    || event.getFacesContext().getResponseComplete()
                    || (event.getPhaseId() == PhaseId.RENDER_RESPONSE)) {
                commitOrRollbackActiveTransaction(event.getPhaseId());
            }

        } catch (Exception e) {
            logger.error("Exception thrown after phase:" + event.getPhaseId(),
                    e);
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(
            EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

}