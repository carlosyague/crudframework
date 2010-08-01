package org.librae.circulacion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.librae.circulacion.model.pib.PeticionPIB;
import org.librae.common.model.BaseObject;

@Entity(name = HistoryStep.ENTITY_NAME)
@Table(name = HistoryStep.TABLE_NAME)
public class HistoryStep extends BaseObject {

    /**
     * 
     */
    private static final long   serialVersionUID             = 414548709867632307L;

    public static final String  ENTITY_NAME                  = "org.librae.circulacion.model.HistoryStep";
    public static final String  TABLE_NAME                   = "CIR_HISTORY_STEPS";

    public static final String  ID_GENERATOR_NAME            = "generator_cir_history_steps";
    private static final String ID_SEQUENCE_NAME             = "SEQ_CIR_HISTORY_STEPS";

    /**
     * column names
     */
    private static final String COLUMN_NAME_ID               = "X_HISTORY_STEP";
    private static final String COLUMN_NAME_ACTIVIDAD_ID     = "C_ACTIVIDAD";
    private static final String COLUMN_NAME_ACTIVIDAD_NAME   = "T_NOMBRE_ACTIVIDAD";
    private static final String COLUMN_NAME_WORKFLOW_ID      = "C_WORKFLOW";
    private static final String COLUMN_NAME_WORKFLOW_NAME    = "T_NOMBRE_WORKFLOW";
    private static final String COLUMN_NAME_ENTITY_ID        = "C_ENTIDAD";
    private static final String COLUMN_NAME_ENTITY_NAME      = "T_NOMBRE_ENTIDAD";
    private static final String COLUMN_NAME_FECHA            = "F_EJECUCION";

    /**
     * property names
     */
    public static final String  PROPERTY_NAME_ID             = "id";
    public static final String  PROPERTY_NAME_ACTIVIDAD_ID   = "stepId";
    public static final String  PROPERTY_NAME_ACTIVIDAD_NAME = "stepName";
    public static final String  PROPERTY_NAME_WORKFLOW_ID    = "workflowId";
    public static final String  PROPERTY_NAME_WORKFLOW_NAME  = "workflowName";
    public static final String  PROPERTY_NAME_ENTITY_ID      = "registeredEntityId";
    public static final String  PROPERTY_NAME_ENTITY_NAME    = "registeredEntityName";
    public static final String  PROPERTY_NAME_FECHA          = "executionDate";

    private Long                id;

    private Integer             stepId;

    private String              stepName;

    private Long                workflowId;

    private String              workflowName;

    private Long                registeredEntityId;

    private String              registeredEntityName;

    private Date                executionDate;

    /**
     * constructors<br>
     * ============
     */
    protected HistoryStep() {
        super();
    }

    public HistoryStep(Integer stepId, Long workflowId) {
        this.setStepId(stepId);
        this.setWorkflowId(workflowId);
    }

    public HistoryStep(PeticionPIB peticion, Integer stepId, String stepName,
            Long workflowId, String workflowName) {

        if (peticion != null) {
            this.setRegisteredEntityId(peticion.getId());
            this.setRegisteredEntityName(peticion.getClass().getName());
        }
        this.setStepId(stepId);
        this.setStepName(stepName);
        this.setWorkflowId(workflowId);
        this.setWorkflowName(workflowName);
    }

    /**
     * getter & setters<br>
     * ================
     */

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = ID_GENERATOR_NAME)
    @SequenceGenerator(name = ID_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME)
    @Column(name = COLUMN_NAME_ID)
    public Long getId() {
        return id;
    }

    /**
     * @return the stepId
     */
    @Column(name = COLUMN_NAME_ACTIVIDAD_ID)
    public Integer getStepId() {
        return stepId;
    }

    /**
     * @return the stepName
     */
    @Column(name = COLUMN_NAME_ACTIVIDAD_NAME)
    public String getStepName() {
        return stepName;
    }

    /**
     * @return the workflowId
     */
    @Column(name = COLUMN_NAME_WORKFLOW_ID)
    public Long getWorkflowId() {
        return workflowId;
    }

    /**
     * @return the workflowName
     */
    @Column(name = COLUMN_NAME_WORKFLOW_NAME)
    public String getWorkflowName() {
        return workflowName;
    }

    /**
     * @return the registeredEntityId
     */
    @Column(name = COLUMN_NAME_ENTITY_ID)
    public Long getRegisteredEntityId() {
        return registeredEntityId;
    }

    /**
     * @return the registeredEntityName
     */
    @Column(name = COLUMN_NAME_ENTITY_NAME)
    public String getRegisteredEntityName() {
        return registeredEntityName;
    }

    /**
     * @return the executionDate
     */
    @Column(name = COLUMN_NAME_FECHA)
    public Date getExecutionDate() {
        return executionDate;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * @param stepId
     *            the stepId to set
     */
    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    /**
     * @param stepName
     *            the stepName to set
     */
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    /**
     * @param workflowId
     *            the workflowId to set
     */
    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * @param workflowName
     *            the workflowName to set
     */
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    /**
     * @param registeredEntityId
     *            the registeredEntityId to set
     */
    public void setRegisteredEntityId(Long registeredEntityId) {
        this.registeredEntityId = registeredEntityId;
    }

    /**
     * @param registeredEntityName
     *            the registeredEntityName to set
     */
    public void setRegisteredEntityName(String registeredEntityName) {
        this.registeredEntityName = registeredEntityName;
    }

    /**
     * @param executionDate
     *            the executionDate to set
     */
    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    /**
     * transient methods<br>
     * =================
     */

    /**
     * override methods<br>
     * =================
     */

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof HistoryStep)) {
            return false;
        }

        final HistoryStep other = (HistoryStep) obj;

        // equals de un pojo no debe comparar los ids
        /*
         * if (!getId().equals(other.getId())) { return false; }
         */
        if (!getExecutionDate().equals(other.getExecutionDate())) {
            return false;
        }

        if (!getRegisteredEntityId().equals(other.getRegisteredEntityId())) {
            return false;
        }

        if (!getWorkflowId().equals(other.getWorkflowId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        result += ((id == null) ? 0 : getId().hashCode());
        result = prime
                * result
                + ((getExecutionDate() == null) ? 0 : getExecutionDate()
                        .hashCode());
        result = prime
                * result
                + ((getRegisteredEntityId() == null) ? 0
                        : getRegisteredEntityId().hashCode());

        result = prime * result
                + ((getWorkflowId() == null) ? 0 : getWorkflowId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(PROPERTY_NAME_ID, getId())
                .append(PROPERTY_NAME_FECHA, getExecutionDate()).append(
                        PROPERTY_NAME_ENTITY_ID, getRegisteredEntityId())
                .append(PROPERTY_NAME_WORKFLOW_ID, getWorkflowId()).toString();
    }

}
