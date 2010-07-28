package org.librae.common.webapp.component.tree;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;
import javax.faces.event.PhaseId;

/**
 * @author Mathias Broekelmann
 * 
 */
public class ToggleExpandedEvent extends FacesEvent {
	private final String mNodeId;

	/**
	 * @param uiComponent
	 * @param nodeId
	 */
	public ToggleExpandedEvent(UIComponent uiComponent, String nodeId) {
		super(uiComponent);
		mNodeId = nodeId;
		setPhaseId(PhaseId.INVOKE_APPLICATION);
	}

	/**
	 * @return Returns the nodeId.
	 */
	public String getNodeId() {
		return mNodeId;
	}

	/**
	 * @see javax.faces.event.FacesEvent#isAppropriateListener(javax.faces.event.FacesListener)
	 */
	public boolean isAppropriateListener(FacesListener faceslistener) {
		return false;
	}

	/**
	 * @see javax.faces.event.FacesEvent#processListener(javax.faces.event.FacesListener)
	 */
	public void processListener(FacesListener faceslistener) {
		throw new UnsupportedOperationException();
	}
}
