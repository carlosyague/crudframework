package org.librae.common.webapp.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.custom.crosstable.UIColumns;
import org.apache.myfaces.renderkit.html.ext.HtmlTableRenderer;

/**
 * Renderer para los listados de librae.
 * 
 * @JSFComponent name = "librae:listado" class =
 *               "org.librae.common.webapp.component.HtmlListado" tagClass =
 *               "org.librae.common.webapp.component.tag.HtmlListadoTag"
 * @author jcisneros
 */
public class HtmlListado extends HtmlDataTable {

	static public final String COMPONENT_FAMILY = "javax.faces.Data";
	static public final String COMPONENT_TYPE = "org.librae.common.webapp.component.HtmlListado";
	static public final String DEFAULT_RENDERER_TYPE = "org.librae.common.webapp.component.renderer.HtmlListadoRenderer";

	private Map _detailRowStates = new HashMap();

	public HtmlListado() {
		setRendererType("org.librae.common.webapp.component.renderer.HtmlListadoRenderer");
	}

	/**
	 * {@inheritDoc}
	 */
	public void processDecodes(FacesContext context) {
		if ((procesarListado(context))) {
			super.processDecodes(context);
		} else {
			if (ordenarListado(context)) {
				processDecodesFaces(context);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void processValidators(FacesContext context) {
		if (procesarListado(context)) {
			super.processValidators(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void processUpdates(FacesContext context) {
		if (procesarListado(context)) {
			super.processUpdates(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void encodeChildren(FacesContext context) throws IOException {
		if ((!procesarListado(context)) || (ordenarListado(context))) {
			super.encodeChildren(context);
		}
	}

	/**
	 * Busca el valor 'procesarListado' en la request, si esta permitira hacer
	 * cierta operacion.
	 * 
	 * @param context
	 * @return
	 */
	public boolean ordenarListado(FacesContext context) {
		boolean procesar = false;
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		String procesarListado = (String) params.get("ordenarListado");
		if ("true".equals(procesarListado)) {
			procesar = true;
		}
		return procesar;
	}

	/**
	 * Busca el valor 'procesarListado' en la request, si esta permitira hacer
	 * cierta operacion.
	 * 
	 * @param context
	 * @return
	 */
	public boolean procesarListado(FacesContext context) {
		boolean procesar = false;
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		String procesarListado = (String) params.get("procesarListado");
		if ("true".equals(procesarListado)) {
			procesar = true;
		}
		return procesar;
	}

	/**
	 * Se decodifica las etiquetas faces.
	 * 
	 * @param context
	 */
	public void processDecodesFaces(FacesContext context) {
		if (!isRendered()) {
			return;
		}
		Object facet = getFacets().remove(
				HtmlTableRenderer.DETAIL_STAMP_FACET_NAME);
		processDecodesColumns(context);
		if (facet != null)
			getFacets().put(HtmlTableRenderer.DETAIL_STAMP_FACET_NAME,
					(UIComponent) facet);

		setRowIndex(-1);
	}

	/**
	 * Se decodifica las columnas.
	 * 
	 * @param context
	 */
	public void processDecodesColumns(FacesContext context) {
		if (!isRendered()) {
			return;
		}
		Object facet = getFacets().remove(
				HtmlTableRenderer.DETAIL_STAMP_FACET_NAME);
		processDecodesChildren(context);
		if (facet != null)
			getFacets().put(HtmlTableRenderer.DETAIL_STAMP_FACET_NAME,
					(UIComponent) facet);

		setRowIndex(-1);
		processColumns(context, 1);
		setRowIndex(-1);
		processDetails(context, 1);
		setRowIndex(-1);
	}

	/**
	 * Se decodifica los hijos.
	 */
	public void processDecodesChildren(FacesContext context) {
		if (context == null)
			throw new NullPointerException("context");
		if (!isRendered())
			return;
		setRowIndex(-1);
		processFacets(context, 1);
		processColumnFacets(context, 1);
		processColumnChildren(context, 1);
		setRowIndex(-1);
		try {
			decode(context);
		} catch (RuntimeException e) {
			context.renderResponse();
			throw e;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	private void processColumns(FacesContext context, int processAction) {
		for (Iterator it = getChildren().iterator(); it.hasNext();) {
			UIComponent child = (UIComponent) it.next();
			if (child instanceof UIColumns) {
				process(context, child, processAction);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	private void processDetails(FacesContext context, int processAction) {
		UIComponent facet = getFacet(HtmlTableRenderer.DETAIL_STAMP_FACET_NAME);

		if (facet != null) {
			int first = getFirst();
			int rows = getRows();
			int last;
			if (rows == 0) {
				last = getRowCount();
			} else {
				last = first + rows;
			}
			for (int rowIndex = first; last == -1 || rowIndex < last; rowIndex++) {
				setRowIndex(rowIndex);

				// scrolled past the last row
				if (!isRowAvailable()) {
					break;
				}

				if (!isCurrentDetailExpanded()) {
					continue;
				}
				if (1 == processAction) {
					resetAllSubmittedValues(facet);
				}
				process(context, facet, processAction);

				if (rowIndex == (last - 1)) {
					Set set = new HashSet();
					set.add(facet);
					_detailRowStates
							.put(
									getClientId(FacesContext
											.getCurrentInstance()),
									saveDescendantComponentStates(set
											.iterator(), false));
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	private void processFacets(FacesContext context, int processAction) {
		for (Iterator it = getFacets().values().iterator(); it.hasNext();) {
			UIComponent facet = (UIComponent) it.next();
			process(context, facet, processAction);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	private void processColumnFacets(FacesContext context, int processAction) {
		for (Iterator childIter = getChildren().iterator(); childIter.hasNext();) {
			UIComponent child = (UIComponent) childIter.next();
			if (child instanceof UIColumn) {
				if (!child.isRendered()) {
					// Column is not visible
					continue;
				}
				for (Iterator facetsIter = child.getFacets().values()
						.iterator(); facetsIter.hasNext();) {
					UIComponent facet = (UIComponent) facetsIter.next();
					process(context, facet, processAction);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	private void processColumnChildren(FacesContext context, int processAction) {
		int first = getFirst();
		int rows = getRows();
		int last;
		if (rows == 0) {
			last = 0;
		} else {
			last = first + rows;
		}
		for (int rowIndex = first; last == -1 || rowIndex < last; rowIndex++) {
			setRowIndex(rowIndex);

			// scrolled past the last row
			if (!isRowAvailable())
				break;

			for (Iterator it = getChildren().iterator(); it.hasNext();) {
				UIComponent child = (UIComponent) it.next();
				if (child instanceof UIColumn) {
					if (!child.isRendered()) {
						// Column is not visible
						continue;
					}
					for (Iterator columnChildIter = child.getChildren()
							.iterator(); columnChildIter.hasNext();) {
						UIComponent columnChild = (UIComponent) columnChildIter
								.next();
						process(context, columnChild, processAction);
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	private void process(FacesContext context, UIComponent component,
			int processAction) {
		switch (processAction) {
		case 1:
			component.processDecodes(context);
			break;
		case 2:
			component.processValidators(context);
			break;
		case 3:
			component.processUpdates(context);
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	private void resetAllSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			((EditableValueHolder) component).setSubmittedValue(null);
		}

		for (Iterator it = component.getFacetsAndChildren(); it.hasNext();) {
			resetAllSubmittedValues((UIComponent) it.next());
		}
	}
}
