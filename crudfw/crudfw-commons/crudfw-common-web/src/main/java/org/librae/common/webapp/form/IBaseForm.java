package org.librae.common.webapp.form;

import java.io.Serializable;

public interface IBaseForm<K> extends Serializable {

    /**
     * Gets an entity object from a form object
     * 
     * @return an entity object
     */
    public K toEntity();

    /**
     * Sets all entity's properties of this form from an entity
     * 
     * @param entity
     */
    public void fromEntity(K entity);
}
