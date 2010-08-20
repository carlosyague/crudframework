package es.uma.crudframework.trazas.annotations.spring;

/**
 * Tipo enumerado para los scope de ejecución.
 * 
 * @author http://internna.blogspot.com/2007/01/one-of-questions-that-arise-time-and.html
 */
public enum Scope {
    
    SINGLETON(0, "singleton"),
    PROTOTYPE(1, "prototype"),
    REQUEST(2, "request"),
    SESSION(3, "session"), 
    GLOBALSESSIOM(4, "globalSession");
    
    private final int value;
    private final String scopeName;

    private Scope(final int value, final String scopeName) {
        this.value = value;
        this.scopeName  = scopeName;
    }
    
    public int getValue() {
        return value;
    }

    public String getScopeName() {
        return scopeName;
    }
}