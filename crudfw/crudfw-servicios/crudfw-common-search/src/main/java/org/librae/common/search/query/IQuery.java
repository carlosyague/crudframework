package org.librae.common.search.query;

/**
 * @author cyague
 */
public interface IQuery {

    // Lucene Boolean Operators
    public final static String BOOLEAN_OPERATOR_AND = "AND";
    public final static String BOOLEAN_OPERATOR_OR  = "OR";

    /**
     * @param query
     * @return
     */
    public IQuery setQuery(String query);

    /**
     * @param field
     */
    public void addSortField(String field);

    /**
     * @param field
     * @param ascending
     */
    public void addSortField(String field, boolean ascending);

    /**
     * @param value
     */
    public void setFacet(boolean value);

    /**
     * @param value
     */
    public void setFacetMinCount(int value);

    /**
     * @param value
     */
    public void setFacetLimit(int value);

    /**
     * @param field
     */
    public void addFacetField(String field);

    /**
     * gets the native implementation of this IQuery object
     * 
     * @return
     */
    public Object toNativeImpl();

}
