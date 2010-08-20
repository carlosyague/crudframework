package es.uma.crudframework.dao.wsclient;

import es.uma.crudframework.dao.GenericDAO;
import es.uma.crudframework.exception.CrudException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

public class GenericWSClientImpl<T, PK extends Serializable> implements
        GenericDAO<T, PK> {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    // Parámetros de configuración inyectados en el Bean de Spring
    protected boolean   enable;                             // Flag que
    // habilita
    // este Cliente WS
    protected String    endpointReference;                  // URL donde se
    // publica el WS
    protected String    namespaceURI;                       // Espacio de
    // nombres
    protected String    serviceName;                        // nombre del
    // servicio
    protected Object    parameterBean;                      // bean que se pasa
    // como parámetro
    protected String    returnType;                         // clase usada como

    // resultado

    // Getter $ Setters
    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getEndpointReference() {
        return endpointReference;
    }

    public void setEndpointReference(String endpointReference) {
        this.endpointReference = endpointReference;
    }

    public String getNamespaceURI() {
        return namespaceURI;
    }

    public void setNamespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object getParameterBean() {
        return parameterBean;
    }

    public void setParameterBean(Object parameterBean) {
        this.parameterBean = parameterBean;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    // Operations

    /**
     * Sets a Spring Bean to a WS operation. <code>parameterBean</code> is set
     * in the spring ws client bean (<code>applicationContext-dao.xml</code>)
     */
    public Exception setBean() {

        return setBean(this.parameterBean);
    }

    /**
     * Sets a Bean to a WS operation. <code>serviceName</code> is set in the
     * spring ws client bean (<code>applicationContext-dao.xml</code>)
     * 
     * @param parameterBean
     * @return
     */
    public Exception setBean(Object parameterBean) {

        Object[] opSetterArgs = new Object[] { parameterBean };

        return setBean(this.serviceName, opSetterArgs);
    }

    /**
     * Sets a Bean to a WS operation. <code>serviceName</code> is set in the
     * spring ws client bean (<code>applicationContext-dao.xml</code>)
     * 
     * @param opSetterArgs
     * @return
     */
    public Exception setBean(Object[] opSetterArgs) {

        return setBean(this.serviceName, opSetterArgs);
    }

    /**
     * Sets a Bean to a WS operation
     * 
     * @param opSetterArgs
     * @param serviceName
     * @return
     */
    public Exception setBean(String serviceName, Object[] opSetterArgs) {
        Exception result = null;

        if (this.enable) {

            try {

                RPCServiceClient serviceClient = new RPCServiceClient();

                Options options = serviceClient.getOptions();

                EndpointReference targetEPR = new EndpointReference(
                        this.endpointReference);

                options.setTo(targetEPR);

                // Setting the bean
                QName opSetter = new QName(this.namespaceURI, serviceName);

                serviceClient.invokeRobust(opSetter, opSetterArgs);

            } catch (Exception e) {
                result = e;
            }
        }

        return result;
    }

    /**
     * Returns a Class object about a classpath-name
     * 
     * @param className
     * @return
     */
    protected Class getClass(String className) {
        Class result = null;
        try {
            result = Class.forName(className);
        } catch (ClassNotFoundException e) {
            // TODO
        }

        return result;
    }

    /**
     * Converts an array to a list
     * 
     * @param array
     * @return
     */
    protected List arrayToList(Object[] array) {
        List<T> result = null;

        if (array != null && array.length > 0) {
            result = new ArrayList<T>();
        }

        for (int i = 0; i < array.length; ++i) {
            T tmp = (T) array[i];
            result.add(tmp);
        }

        return result;
    }

    /**
     * Gets a Bean from a WS operation. <code>serviceName</code> y
     * <code>returnType</code> are set in the spring ws client bean (
     * <code>applicationContext-dao.xml</code>)
     * 
     * @return
     */
    public Object getBean() {
        Object result = null;

        Class[] returnTypes = new Class[] { getClass(this.returnType) };
        Object[] response = getBean(this.serviceName, returnTypes);

        if (response != null && response.length > 0) {
            result = response[0];
        }

        return result;
    }

    /**
     * Sets a Bean to a WS operation.
     * 
     * @param serviceName
     * @param returnType
     * @return
     */
    public Object[] getBean(String serviceName, Class returnType) {

        Class[] returnTypes = new Class[] { returnType };

        return getBean(serviceName, returnTypes);
    }

    /**
     * Sets a Bean to a WS operation.
     * 
     * @param serviceName
     * @param returnTypes
     * @return
     */
    public Object[] getBean(String serviceName, Class[] returnTypes) {

        Object[] opGetterArgs = new Object[] {};

        return getBean(serviceName, opGetterArgs, returnTypes);
    }

    public Object[] getBean(String serviceName, Object[] opGetterArgs,
            Class returnType) {

        Class[] returnTypes = new Class[] { returnType };

        return getBean(serviceName, opGetterArgs, returnTypes);
    }

    /**
     * Sets a Bean to a WS operation.
     * 
     * @param serviceName
     * @param opGetterArgs
     * @param returnTypes
     * @return
     */
    public Object[] getBean(String serviceName, Object[] opGetterArgs,
            Class[] returnTypes) {
        Object[] result = null;

        if (this.enable) {

            try {
                RPCServiceClient serviceClient = new RPCServiceClient();

                Options options = serviceClient.getOptions();

                EndpointReference targetEPR = new EndpointReference(
                        this.endpointReference);

                options.setTo(targetEPR);

                // Setting the bean
                QName opGetter = new QName(this.namespaceURI, serviceName);

                Object[] response = serviceClient.invokeBlocking(opGetter,
                        opGetterArgs, returnTypes);

                if (response != null) {
                    result = response;
                }

            } catch (Exception e) {
                log.debug("GenericWSClientImpl.getBean()::Exception::"
                        + e.getMessage());
            }
        }

        return result;

    }

    
    public boolean exists(PK id) {

        boolean result = true;

        Object[] opGetterArgs = new Object[] { id };
        Object[] response = getBean("exists", opGetterArgs, Boolean.class);

        if (response != null && response.length > 0
                && (response[0] instanceof Boolean) && response[0] != null) {
            result = (Boolean) response[0];
        }

        return result;
    }

    
    public T get(PK id) {
        T result = null;

        Object[] opGetterArgs = new Object[] { id };
        Object[] response = getBean("get", opGetterArgs,
                getClass(this.returnType));

        if (response != null && response.length > 0 && response[0] != null) {
            result = (T) response[0];
        }

        return result;
    }

    
    public List<T> getAll() {
        List<T> result = null;

        Object[] response = getBean("getAll", getClass(this.returnType));

        if (response != null) {
            result = arrayToList(response);
        }

        return result;
    }

    
    public void remove(PK id) {
        Object[] opSetterArgs = new Object[] { id };
        Exception exception = setBean("remove", opSetterArgs);

        if (exception != null) {
            // caso error
            log.debug("GenericWSClientImpl.remove()::Exception::" + exception);
            // TODO
        }
    }

    
    public T save(T object) {
        T result = null;

        Object[] opGetterArgs = new Object[] { object };
        Object[] response = getBean("save", opGetterArgs,
                getClass(this.returnType));

        if (response != null && response.length > 0 && response[0] != null) {
            result = (T) response[0];
        }

        return result;
    }

    public List<T> getPage(int pag, int num_res) throws CrudException {
        // TODO Auto-generated method stub
        return null;
    }

    public List<T> getPage(int pag, int num_res, String orderColumn,
            boolean ascendingOrder) throws CrudException {
        // TODO Auto-generated method stub
        return null;
    }

    public Long countAll() throws CrudException {
        // TODO Auto-generated method stub
        return null;
    }

    public void clear() throws CrudException {
        // TODO Auto-generated method stub

    }

    public void evict(T obj) throws CrudException {
        // TODO Auto-generated method stub

    }

    public List<T> execSQL(String sql) throws CrudException {
        // TODO Auto-generated method stub
        return null;
    }

    public void flush() throws CrudException {
        // TODO Auto-generated method stub

    }

    public Session getORMSession() throws CrudException {
        // TODO Auto-generated method stub
        return null;
    }

    public T merge(T object) throws CrudException {
        // TODO Auto-generated method stub
        return null;
    }

	public String createCondition(String label, String column) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createCondition(String label, String column, String function) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createWhereCondition(String[] conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getAllDistinct() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getResults(String table, String label, String[] conditions,
			Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}
}
