package org.librae.circulacion.model.pib;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

import org.librae.adminconfig.model.Biblioteca;
import org.hibernate.annotations.Cascade;

/**
 * Cada fila es una petición realizada a una biblioteca (por Mi Biblioteca) o
 * recibida de una biblioteca (para que la sirva Mi Biblioteca).<br>
 * Cada fila representa también los datos de un workflow que va avanzando en su
 * tramitación.
 * 
 * @author cyague
 */
@Entity(name = PeticionPIB.ENTITY_NAME)
@Table(name = PeticionPIB.TABLE_NAME)
public class PeticionPIB extends AbstractPeticionPIB {

    /**
     * BaseObject is Serializable, so PeticionPIB needs a Serial Version UID
     */
    private static final long         serialVersionUID                          = -1705894644105847970L;

    public static final String        ENTITY_NAME                               = "org.librae.circulacion.model.PeticionPIB";
    public static final String        TABLE_NAME                                = "CIR_PIB_PETICIONES";

    public static final String        ID_GENERATOR_NAME                         = "generator_cir_pib_peticiones";
    private static final String       ID_SEQUENCE_NAME                          = "SEQ_CIR_PIB_PETICIONES";

    private static final String       COLUMN_NAME_WORKFLOW_ID_BORROWER          = "ID_X_WORKFLOW_BORROWER";
    private static final String       COLUMN_NAME_ACTIVIDAD_WORKFLOW_BORROWER   = "ACTIVIDAD_X_WORKFLOW_BORROWER";
    private static final String       COLUMN_NAME_ACCION_WORKFLOW_BORROWER      = "ACCION_X_WORKFLOW_BORROWER";
    private static final String       COLUMN_NAME_WORKFLOW_ID_LENDER            = "ID_X_WORKFLOW_LENDER";
    private static final String       COLUMN_NAME_ACTIVIDAD_WORKFLOW_LENDER     = "ACTIVIDAD_X_WORKFLOW_LENDER";
    private static final String       COLUMN_NAME_ACCION_WORKFLOW_LENDER        = "ACCION_X_WORKFLOW_LENDER";

    private static final String       COLUMN_NAME_ACCION_TIPO_PETICION_WORKFLOW = "TIPO_PETICION_WORKFLOW";

    public static final String        PROPERTY_NAME_WORKFLOW_ID_BORROWER        = "workflowIdBorrower";
    public static final String        PROPERTY_NAME_ACTIVIDAD_WORKFLOW_BORROWER = "actividadWorkflowBorrower";
    public static final String        PROPERTY_NAME_ACCION_WORKFLOW_BORROWER    = "accionWorkflowBorrower";

    public static final String        PROPERTY_NAME_WORKFLOW_ID_LENDER          = "workflowIdLender";
    public static final String        PROPERTY_NAME_ACTIVIDAD_WORKFLOW_LENDER   = "actividadWorkflowLender";
    public static final String        PROPERTY_NAME_ACCION_WORKFLOW_LENDER      = "accionWorkflowLender";

    public static final String        PROPERTY_NAME_TIPO_PETICION_WORKFLOW      = "tipoPeticionWorkflow";

    /**
     * Workflows Utilizados en Circulación - PIB (Préstamo InterBibliotecario)
     */
    public static final String        CIRC_PIB_WORKFLOW_NAME_BORROWER           = "PIB-BORROWER";
    public static final String        CIRC_PIB_WORKFLOW_NAME_LENDER             = "PIB-LENDER";

    /**
     * Tipos de petición contemplados en los Workflows de PIB (Préstamo
     * InterBibliotecario)
     */
    public static final int           TIPO_PETICION_WORKFLOW_BORROWER           = 0;
    public static final int           TIPO_PETICION_WORKFLOW_LENDER             = 1;

    /**
     * combo Tipos de petición del workflow
     */
    public static final Integer[]     TIPO_PETICION_WORKFLOW_VALUES             = {
            TIPO_PETICION_WORKFLOW_BORROWER, TIPO_PETICION_WORKFLOW_LENDER     };
    public static final String[]      TIPO_PETICION_WORKFLOW_KEYS               = {
            "peticionPIB.select.tipoPeticionWorkflow.borrower",
            "peticionPIB.select.tipoPeticionWorkflow.lender"                   };

    /**
     * Clave primaria artificial.<br>
     * Identificador único de petición para el bibliotecario y el lector
     */
    protected Long                    id;

    /**
     * Identificador del WorkflowId
     */
    protected Long                    workflowIdBorrower                        = null;

    /**
     * Identificador del WorkflowId
     */
    protected Long                    workflowIdLender                          = null;

    /**
     * Tipo de petición a nivel de Workflow<br>
     * 0 - BORROWER<br>
     * 1 - LENDER
     */
    protected Integer                 tipoPeticionWorkflow;

    /**
     * Actividad actual del Workflow (StepID)
     */
    protected Integer                 actividadWorkflowBorrower                 = null;

    /**
     * Actividad actual del Workflow (StepID)
     */
    protected Integer                 actividadWorkflowLender                   = null;

    /**
     * Accion actual del Workflow (ActionID)
     */
    protected Integer                 accionWorkflowBorrower                    = null;

    /**
     * Accion actual del Workflow (ActionID)
     */
    protected Integer                 accionWorkflowLender                      = null;

    /**
     * Nombre de la Actividad Actual<br>
     * Campo <i>Transient</i> que deberá establecerse en el manager
     */
    protected String                  actividadActualBorrower;

    /**
     * Nombre de la Actividad Actual<br>
     * Campo <i>Transient</i> que deberá establecerse en el manager
     */
    protected String                  actividadActualLender;

    /**
     * Lista de eventos de la peticion
     */
    protected List<EventoPIB>         eventos;

    /**
     * Lista de eventos de la peticion
     */
    protected List<AccionPrestamoPIB> accionPrestamos;

    /**
     * @param id
     *            the id to set
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * constructors<br>
     * ============
     */

    protected PeticionPIB() {
        super();
    }

    public PeticionPIB(Biblioteca bibliotecaBorrower) {
        super();
        this.bibliotecaBorrower = bibliotecaBorrower;
    }

    public PeticionPIB(BibliotecaExterna bibliotecaExternaBorrower) {
        super();
        this.bibliotecaExternaBorrower = bibliotecaExternaBorrower;
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
     * @return the workflowId
     */
    @Column(name = COLUMN_NAME_WORKFLOW_ID_BORROWER)
    public Long getWorkflowIdBorrower() {
        return workflowIdBorrower;
    }

    /**
     * @return the workflowId
     */
    @Column(name = COLUMN_NAME_WORKFLOW_ID_LENDER)
    public Long getWorkflowIdLender() {
        return workflowIdLender;
    }

    @Transient
    public Long getWorkflowId() {
        Long result = null;

        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    result = getWorkflowIdBorrower();
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    result = getWorkflowIdLender();
                    break;
            }
        }

        return result;
    }

    /**
     * @param workflowId
     *            the workflowId to set
     */
    public void setWorkflowIdBorrower(Long workflowIdBorrower) {
        this.workflowIdBorrower = workflowIdBorrower;
    }

    /**
     * @param workflowIdLender
     *            the workflowIdLender to set
     */
    public void setWorkflowIdLender(Long workflowIdLender) {
        this.workflowIdLender = workflowIdLender;
    }

    /**
     * @param workflowId
     *            the workflowId to set
     */
    @Transient
    public void setWorkflowId(Long workflowId) {

        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    setWorkflowIdBorrower(workflowId);
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    setWorkflowIdLender(workflowId);
                    break;
            }
        }
    }

    /**
     * @return the actividadWorkflow
     */
    @Column(name = COLUMN_NAME_ACTIVIDAD_WORKFLOW_BORROWER)
    public Integer getActividadWorkflowBorrower() {
        return actividadWorkflowBorrower;
    }

    /**
     * @return the actividadWorkflow
     */
    @Column(name = COLUMN_NAME_ACTIVIDAD_WORKFLOW_LENDER)
    public Integer getActividadWorkflowLender() {
        return actividadWorkflowLender;
    }

    /**
     * @return the actividadWorkflow
     */
    @Transient
    public Integer getActividadWorkflow() {
        Integer result = null;

        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    result = getActividadWorkflowBorrower();
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    result = getActividadWorkflowLender();
                    break;
            }
        }

        return result;
    }

    /**
     * @param actividadWorkflowBorrower
     *            the actividadWorkflowBorrower to set
     */
    public void setActividadWorkflowBorrower(Integer actividadWorkflowBorrower) {
        this.actividadWorkflowBorrower = actividadWorkflowBorrower;
    }

    /**
     * @param actividadWorkflowLender
     *            the actividadWorkflowLender to set
     */
    public void setActividadWorkflowLender(Integer actividadWorkflowLender) {
        this.actividadWorkflowLender = actividadWorkflowLender;
    }

    /**
     * @param actividadWorkflow
     *            the actividadWorkflow to set
     */
    @Transient
    public void setActividadWorkflow(Integer actividadWorkflow) {
        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    setActividadWorkflowBorrower(actividadWorkflow);
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    setActividadWorkflowLender(actividadWorkflow);
                    break;
            }
        }
    }

    /**
     * @return the accionWorkflowBorrower
     */
    @Column(name = COLUMN_NAME_ACCION_WORKFLOW_BORROWER)
    public Integer getAccionWorkflowBorrower() {
        return accionWorkflowBorrower;
    }

    /**
     * @return the accionWorkflowLender
     */
    @Column(name = COLUMN_NAME_ACCION_WORKFLOW_LENDER)
    public Integer getAccionWorkflowLender() {
        return accionWorkflowLender;
    }

    /**
     * @return the accionWorkflow
     */
    @Transient
    public Integer getAccionWorkflow() {
        Integer result = null;

        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    result = getAccionWorkflowBorrower();
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    result = getAccionWorkflowLender();
                    break;
            }
        }

        return result;
    }

    @OneToMany(targetEntity = AccionPrestamoPIB.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = AccionPrestamoPIB.COLUMN_NAME_PETICION)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<AccionPrestamoPIB> getAccionPrestamos() {
        return accionPrestamos;
    }

    public void setAccionPrestamos(List<AccionPrestamoPIB> accionPrestamos) {
        this.accionPrestamos = accionPrestamos;
    }

    /**
     * @param accionWorkflowBorrower
     *            the accionWorkflowBorrower to set
     */
    public void setAccionWorkflowBorrower(Integer accionWorkflowBorrower) {
        this.accionWorkflowBorrower = accionWorkflowBorrower;
    }

    /**
     * @param accionWorkflowLender
     *            the accionWorkflowLender to set
     */
    public void setAccionWorkflowLender(Integer accionWorkflowLender) {
        this.accionWorkflowLender = accionWorkflowLender;
    }

    /**
     * @param accionWorkflow
     *            the accionWorkflow to set
     */
    @Transient
    public void setAccionWorkflow(Integer accionWorkflow) {
        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    setAccionWorkflowBorrower(accionWorkflow);
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    setAccionWorkflowLender(accionWorkflow);
                    break;
            }
        }
    }

    /**
     * @return the actividadActualBorrower
     */
    @Transient
    public String getActividadActualBorrower() {
        return actividadActualBorrower;
    }

    /**
     * @return the actividadActualLender
     */
    @Transient
    public String getActividadActualLender() {
        return actividadActualLender;
    }

    /**
     * @return the actividadActual
     */
    @Transient
    public String getActividadActual() {
        String result = null;

        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    result = getActividadActualBorrower();
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    result = getActividadActualLender();
                    break;
            }
        }

        return result;
    }

    /**
     * @param actividadActualBorrower
     *            the actividadActualBorrower to set
     */
    @Transient
    public void setActividadActualBorrower(String actividadActualBorrower) {
        this.actividadActualBorrower = actividadActualBorrower;
    }

    /**
     * @param actividadActual
     *            the actividadActual to set
     */
    @Transient
    public void setActividadActualLender(String actividadActualLender) {
        this.actividadActualLender = actividadActualLender;
    }

    /**
     * @param actividadActual
     *            the actividadActual to set
     */
    @Transient
    public void setActividadActual(String actividadActual) {
        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    setActividadActualBorrower(actividadActual);
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    setActividadActualLender(actividadActual);
                    break;
            }
        }

    }

    /**
     * @return the tipoPeticionWorkflow
     */
    @Column(name = COLUMN_NAME_ACCION_TIPO_PETICION_WORKFLOW)
    public Integer getTipoPeticionWorkflow() {
        return tipoPeticionWorkflow;
    }

    /**
     * @param tipoPeticionWorkflow
     *            the tipoPeticionWorkflow to set
     */
    public void setTipoPeticionWorkflow(Integer tipoPeticionWorkflow) {
        this.tipoPeticionWorkflow = tipoPeticionWorkflow;
    }

    @Transient
    public String getWorkflowName() {
        return getWorkflowName(tipoPeticionWorkflow);
    }

    @Transient
    public String getWorkflowName(Integer tipoPeticionWorkflow) {
        String result = null;
        if (tipoPeticionWorkflow != null) {
            switch (tipoPeticionWorkflow.intValue()) {
                case TIPO_PETICION_WORKFLOW_BORROWER:
                    result = CIRC_PIB_WORKFLOW_NAME_BORROWER;
                    break;
                case TIPO_PETICION_WORKFLOW_LENDER:
                    result = CIRC_PIB_WORKFLOW_NAME_LENDER;
                    break;
            }
        }

        return result;
    }

    @OneToMany(targetEntity = EventoPIB.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = EventoPIB.COLUMN_NAME_PETICION)
    public List<EventoPIB> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoPIB> eventos) {
        this.eventos = eventos;
    }

    @Override
    public boolean equals(Object obj) {
        // if they are the same instance
        if (this == obj) {
            return true;
        }

        // if they are classify in different classes
        if (!(obj instanceof PeticionPIB)) {
            return false;
        }

        final PeticionPIB other = (PeticionPIB) obj;

        return super.equals(other);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;

        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());

        return result;
    }

}