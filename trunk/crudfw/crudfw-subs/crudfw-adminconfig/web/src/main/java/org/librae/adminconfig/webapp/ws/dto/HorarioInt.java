//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, vhudson-jaxb-ri-2.1-520
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2009.12.01 at 12:50:36 PM CET
//

package org.librae.adminconfig.webapp.ws.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for HorarioInt complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;HorarioInt&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;fechaInicio&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}date&quot;/&gt;
 *         &lt;element name=&quot;fechaFin&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}date&quot;/&gt;
 *         &lt;element name=&quot;horaInicio1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaInicio2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaInicio3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaInicio4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaInicio5&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaFin1&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaFin2&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaFin3&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaFin4&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;horaFin5&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}time&quot;/&gt;
 *         &lt;element name=&quot;diaSemana&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HorarioInt", propOrder = { "fechaInicio", "fechaFin",
        "horaInicio1", "horaInicio2", "horaInicio3", "horaInicio4",
        "horaInicio5", "horaFin1", "horaFin2", "horaFin3", "horaFin4",
        "horaFin5", "diaSemana" })
@XmlRootElement
public class HorarioInt {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFin;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaInicio1;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaInicio2;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaInicio3;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaInicio4;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaInicio5;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaFin1;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaFin2;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaFin3;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaFin4;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar horaFin5;
    @XmlElement(required = true)
    protected String               diaSemana;

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        fechaInicio = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setFechaFin(XMLGregorianCalendar value) {
        fechaFin = value;
    }

    /**
     * Gets the value of the horaInicio1 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraInicio1() {
        return horaInicio1;
    }

    /**
     * Sets the value of the horaInicio1 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraInicio1(XMLGregorianCalendar value) {
        horaInicio1 = value;
    }

    /**
     * Gets the value of the horaInicio2 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraInicio2() {
        return horaInicio2;
    }

    /**
     * Sets the value of the horaInicio2 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraInicio2(XMLGregorianCalendar value) {
        horaInicio2 = value;
    }

    /**
     * Gets the value of the horaInicio3 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraInicio3() {
        return horaInicio3;
    }

    /**
     * Sets the value of the horaInicio3 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraInicio3(XMLGregorianCalendar value) {
        horaInicio3 = value;
    }

    /**
     * Gets the value of the horaInicio4 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraInicio4() {
        return horaInicio4;
    }

    /**
     * Sets the value of the horaInicio4 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraInicio4(XMLGregorianCalendar value) {
        horaInicio4 = value;
    }

    /**
     * Gets the value of the horaInicio5 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraInicio5() {
        return horaInicio5;
    }

    /**
     * Sets the value of the horaInicio5 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraInicio5(XMLGregorianCalendar value) {
        horaInicio5 = value;
    }

    /**
     * Gets the value of the horaFin1 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraFin1() {
        return horaFin1;
    }

    /**
     * Sets the value of the horaFin1 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraFin1(XMLGregorianCalendar value) {
        horaFin1 = value;
    }

    /**
     * Gets the value of the horaFin2 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraFin2() {
        return horaFin2;
    }

    /**
     * Sets the value of the horaFin2 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraFin2(XMLGregorianCalendar value) {
        horaFin2 = value;
    }

    /**
     * Gets the value of the horaFin3 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraFin3() {
        return horaFin3;
    }

    /**
     * Sets the value of the horaFin3 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraFin3(XMLGregorianCalendar value) {
        horaFin3 = value;
    }

    /**
     * Gets the value of the horaFin4 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraFin4() {
        return horaFin4;
    }

    /**
     * Sets the value of the horaFin4 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraFin4(XMLGregorianCalendar value) {
        horaFin4 = value;
    }

    /**
     * Gets the value of the horaFin5 property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getHoraFin5() {
        return horaFin5;
    }

    /**
     * Sets the value of the horaFin5 property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     */
    public void setHoraFin5(XMLGregorianCalendar value) {
        horaFin5 = value;
    }

    /**
     * Gets the value of the diaSemana property.
     * 
     * @return possible object is {@link String }
     */
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * Sets the value of the diaSemana property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setDiaSemana(String value) {
        diaSemana = value;
    }

}
