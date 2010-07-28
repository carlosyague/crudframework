package org.librae.common.webapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.ajax4jsf.framework.ajax.xmlfilter.TidyFilter;

/**
 * Proxy for resource/ajax xml parsing filter.
 * Si en la request se encuentra el parametro affecteAjaxComponent,
 * estamos sobre una peticion ajax de sandbox por lo tanto no pasamos el
 * filtro Tidy para evitar añadir nada a la response que debe quedar intacta.
 * Otra opcion para no pasar el filtro es utilizar el parametro forceparser a
 * false, pero esto puede dar preblemas en algunas pantallas.
 */
public class Ajax4jsfFilter extends TidyFilter {

	/**
	 * Execute the filter.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * El parametro "affectedAjaxComponent" solo esta presente en la peticion
		 * en el caso de una llamada ajax de sandbox.
		 */
		if ((request.getParameter("affectedAjaxComponent")) == null){
			super.doFilter(request, response, chain);
		}else{
			chain.doFilter(request, response);
		}
	}

}

