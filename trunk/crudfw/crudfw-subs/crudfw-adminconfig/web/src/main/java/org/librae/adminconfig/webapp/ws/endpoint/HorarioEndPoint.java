package org.librae.adminconfig.webapp.ws.endpoint;

import java.util.ArrayList;

import javax.xml.soap.SAAJResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.librae.adminconfig.model.Horario;
import org.librae.adminconfig.model.HorarioInt;
import org.librae.adminconfig.webapp.ws.dto.Respuesta;
import org.librae.adminconfig.webapp.ws.dto.RespuestaElement;
import org.librae.adminconfig.webapp.ws.service.IHorarioService;
import org.librae.common.exception.LibraeException;
import org.librae.common.util.DateUtil;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.server.endpoint.AbstractDomPayloadEndpoint;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.xml.xpath.XPathExpression;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Endpoint
public class HorarioEndPoint extends AbstractDomPayloadEndpoint {

    private IHorarioService horarioService;
    protected final Log     logger = LogFactory.getLog(getClass());

    private XPathExpression codBibliotecaXPath;
    private Jaxb2Marshaller jaxb2Marshaller;

    @Override
    protected Element invokeInternal(Element req, Document respDoc)
            throws Exception {
        final Respuesta respuesta = new Respuesta();

        // Extraer datos de request:
        final String codBiblioteca = codBibliotecaXPath.evaluateAsString(req);

        try {
            // invocar método de servicio:
            if (codBiblioteca == null || codBiblioteca.equals("")) {
                final org.librae.adminconfig.webapp.ws.dto.Error error = new org.librae.adminconfig.webapp.ws.dto.Error();
                error.setCodigo("98");
                error
                        .setDescripcion("Falta alguno de los parámetros obligatorios: codBiblioteca");

                respuesta.setError(error);

            } else {
                final Horario horarioModel = horarioService
                        .getHorarioByCod(codBiblioteca);

                if (horarioModel == null) {
                    final org.librae.adminconfig.webapp.ws.dto.Error error = new org.librae.adminconfig.webapp.ws.dto.Error();
                    error.setCodigo("02");
                    error.setDescripcion("Biblioteca sin horario");

                    respuesta.setError(error);
                } else {

                    final org.librae.adminconfig.webapp.ws.dto.Horario horarioWs = horarioModelToHorarioWS(horarioModel);
                    respuesta.setHorario(horarioWs);
                }
            }
        } catch (final LibraeException le) {

            if (le.getCodigo().equals("01")) {
                final org.librae.adminconfig.webapp.ws.dto.Error error = new org.librae.adminconfig.webapp.ws.dto.Error();
                error.setCodigo("01");
                error.setDescripcion("Codigo de biblioteca incorrecto");

                respuesta.setError(error);
            } else {
                final org.librae.adminconfig.webapp.ws.dto.Error error = new org.librae.adminconfig.webapp.ws.dto.Error();
                error.setCodigo("99");
                error.setDescripcion("Error general");

                respuesta.setError(error);

                logger.error(le);
            }
        } catch (final Exception e) {
            final org.librae.adminconfig.webapp.ws.dto.Error error = new org.librae.adminconfig.webapp.ws.dto.Error();
            error.setCodigo("99");
            error.setDescripcion("Error general");

            respuesta.setError(error);

            logger.error(e);
        }

        final RespuestaElement respuestaElement = new RespuestaElement(
                respuesta);
        // Preparar respuesta:
        final SAAJResult result = new SAAJResult();
        jaxb2Marshaller.marshal(respuestaElement, result);

        final Element resp = (Element) result.getResult();

        return resp;

    }

    private org.librae.adminconfig.webapp.ws.dto.Horario horarioModelToHorarioWS(
            Horario horarioModel) {
        final org.librae.adminconfig.webapp.ws.dto.Horario horarioWs = new org.librae.adminconfig.webapp.ws.dto.Horario();

        final ArrayList<org.librae.adminconfig.webapp.ws.dto.HorarioInt> horariosIntWs = new ArrayList<org.librae.adminconfig.webapp.ws.dto.HorarioInt>();

        for (final HorarioInt horarioInt : horarioModel.getHorariosInt()) {
            final org.librae.adminconfig.webapp.ws.dto.HorarioInt horarioIntWs = new org.librae.adminconfig.webapp.ws.dto.HorarioInt();
            horarioIntWs.setFechaInicio(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getFechaInicio()));
            horarioIntWs.setFechaFin(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getFechaFin()));
            horarioIntWs.setHoraInicio1(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraInicio1()));
            horarioIntWs.setHoraInicio2(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraInicio2()));
            horarioIntWs.setHoraInicio3(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraInicio3()));
            horarioIntWs.setHoraInicio4(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraInicio4()));
            horarioIntWs.setHoraInicio5(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraInicio5()));
            horarioIntWs.setHoraFin1(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraFin1()));
            horarioIntWs.setHoraFin2(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraFin2()));
            horarioIntWs.setHoraFin3(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraFin3()));
            horarioIntWs.setHoraFin4(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraFin4()));
            horarioIntWs.setHoraFin5(DateUtil
                    .dateToXMLGregorianCalendar(horarioInt.getHoraFin5()));
            horarioIntWs.setDiaSemana(horarioInt.getDiaSemana());

            horariosIntWs.add(horarioIntWs);
        }

        horarioWs.setHorariosInt(horariosIntWs);
        horarioWs.setFechaActualizacion(DateUtil
                .dateToXMLGregorianCalendar(horarioModel
                        .getFechaActualizacion()));
        return horarioWs;
    }

    /**
     * @return the horarioService
     */
    public IHorarioService getHorarioService() {
        return horarioService;
    }

    /**
     * @param horarioService
     *            the horarioService to set
     */
    public void setHorarioService(IHorarioService horarioService) {
        this.horarioService = horarioService;
    }

    /**
     * @return the codBibliotecaXPath
     */
    public XPathExpression getCodBibliotecaXPath() {
        return codBibliotecaXPath;
    }

    /**
     * @param codBibliotecaXPath
     *            the codBibliotecaXPath to set
     */
    public void setCodBibliotecaXPath(XPathExpression codBibliotecaXPath) {
        this.codBibliotecaXPath = codBibliotecaXPath;
    }

    /**
     * @return the jaxb2Marshaller
     */
    public Jaxb2Marshaller getJaxb2Marshaller() {
        return jaxb2Marshaller;
    }

    /**
     * @param jaxb2Marshaller
     *            the jaxb2Marshaller to set
     */
    public void setJaxb2Marshaller(Jaxb2Marshaller jaxb2Marshaller) {
        this.jaxb2Marshaller = jaxb2Marshaller;
    }

}