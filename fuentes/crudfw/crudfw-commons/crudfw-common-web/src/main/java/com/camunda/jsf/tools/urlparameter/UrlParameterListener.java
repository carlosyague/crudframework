package com.camunda.jsf.tools.urlparameter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * The UrlParameterListener class.
 * 
 * @author camunda
 */
public class UrlParameterListener implements PhaseListener {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = -317889761147976015L;

    /**
     * The constructor.
     */
    public UrlParameterListener() {
        try {
            Document configurationDocument = null;
            final SAXBuilder saxBuilder = new SAXBuilder();

            final URL url = this.getClass().getResource(CONFIGURATION_FILENAME);
            logger.info("Registering UrlParameter from resource: "
                    + url.getPath() + " / " + url.getFile());
            if (url == null) {
                logger
                        .error("Could not open UrlParameter configuration. UrlParameters will not work!");
            } else {
                configurationDocument = saxBuilder.build(url);
                initUrlParameters(configurationDocument);
            }
        } catch (final IOException ex) {
            logger
                    .error(
                            "Could not open UrlParameter configuration. UrlParameters will not work!",
                            ex);
        } catch (final JDOMException ex) {
            logger
                    .error(
                            "Could not open UrlParameter configuration. UrlParameters will not work!",
                            ex);
        }

    }

    /**
     * initUrlParameters.
     * 
     * @param doc
     *            the document.
     */
    private void initUrlParameters(final Document doc) {
        final List params = doc.getRootElement().getChildren();

        final Iterator paramIter = params.iterator();
        while (paramIter.hasNext()) {

            try {

                final UrlParameter urlParameter = new UrlParameter();

                final Element paramElement = (Element) paramIter.next();
                urlParameter.setName(paramElement.getChild("name").getText());
                urlParameter.setManagedBeanName(paramElement.getChild(
                        "managed-bean-name").getText());
                urlParameter.setManagedBeanClass(Class.forName(paramElement
                        .getChild("managed-bean-class").getText()));
                urlParameter.setManagedBeanMethod(paramElement.getChild(
                        "managed-bean-method").getText());

                logger.info("Adding UrlParameter: " + urlParameter);
                urlParameterList.add(urlParameter);

            } catch (final ClassNotFoundException ex) {
                logger.error("Could not create UrlParameter: "
                        + ex.getMessage(), ex);
            }

        }

    }

    /**
     * afterPhase.
     * 
     * @param phaseEvent
     *            the phaseEvent.
     */
    public void afterPhase(final PhaseEvent phaseEvent) {
        logger.info("Do nothing");
    }

    /**
     * beforePhase.
     * 
     * @param phaseEvent
     *            the phaseEvent
     */
    public final void beforePhase(final PhaseEvent phaseEvent) {

        final FacesContext context = phaseEvent.getFacesContext();

        if (!context.getExternalContext().getRequestParameterMap().isEmpty()) {

            final Iterator<UrlParameter> paramIter = urlParameterList
                    .iterator();

            UrlParameter param;

            while (paramIter.hasNext()) {

                param = paramIter.next();
                String value = null;

                try {

                    if (context.getExternalContext().getRequestParameterMap()
                            .containsKey(param.getName())) {

                        value = context.getExternalContext()
                                .getRequestParameterMap().get(param.getName());

                        Object managedBean = null;

                        if (value != null) {

                            managedBean = context.getCurrentInstance()
                                    .getExternalContext().getSessionMap().get(
                                            param.getManagedBeanName());

                            managedBean = param.getManagedBeanClass().cast(
                                    managedBean);

                        }

                        if (value != null && managedBean == null) {
                            managedBean = param.getManagedBeanClass()
                                    .newInstance();
                            context.getCurrentInstance().getExternalContext()
                                    .getSessionMap().put(
                                            param.getManagedBeanName(),
                                            managedBean);
                        }

                        if (value != null) {

                            final Method method = managedBean.getClass()
                                    .getMethod(param.getManagedBeanMethod(),
                                            new Class[] { String.class });
                            method.invoke(managedBean, new Object[] { value });

                        }
                    }
                } catch (final Exception ex) {
                    logger.warn(
                            "Exception while setting url parameter value. Values: "
                                    + param.getName() + " with value '" + value
                                    + "', in Bean '"
                                    + param.getManagedBeanName()
                                    + "', of class '"
                                    + param.getManagedBeanClass() + "', "
                                    + param.getManagedBeanMethod(), ex);
                }
            }
        }

    }

    /**
     * returns the PhaseId.
     * 
     * @return the PhaseId
     */
    public final PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    /**
     * setUrlParameterList.
     * 
     * @param urlParameterList
     *            the List of UrlParameter
     */
    public final void setUrlParameterList(
            final List<UrlParameter> urlParameterList) {
        this.urlParameterList = urlParameterList;
    }

    /**
     * returns the urlParameterList.
     * 
     * @return the urlParameterList
     */
    public final List getUrlParameterList() {
        return urlParameterList;
    }

    /**
     * The log.
     */
    private final Logger        logger                 = Logger.getLogger(this
                                                               .getClass());

    /**
     * the urlParameterList.
     * 
     * @associates com.camunda.jsf.tools.urlparameter.UrlParameter
     * @directed
     * @supplierCardinality 0..*
     */
    private List<UrlParameter>  urlParameterList       = new ArrayList<UrlParameter>();

    /**
     * the CONFIGURATION_FILENAME.
     */
    private static final String CONFIGURATION_FILENAME = "/url-parameter-configuration.xml";

}
