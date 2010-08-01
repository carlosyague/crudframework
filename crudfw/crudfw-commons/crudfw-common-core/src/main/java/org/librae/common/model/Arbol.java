package org.librae.common.model;

import java.io.Serializable;

public class Arbol implements Serializable {

    protected Long    padre;
    protected Long    nodo;
    protected Integer pos;

    public Arbol() {
        super();
    }

    public Arbol(Long nodo, Long padre, Integer pos) {
        super();
        this.nodo = nodo;
        this.padre = padre;
        this.pos = pos;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Long getPadre() {
        return padre;
    }

    public void setPadre(Long padre) {
        this.padre = padre;
    }

    public Long getNodo() {
        return nodo;
    }

    public void setNodo(Long nodo) {
        this.nodo = nodo;
    }
}
