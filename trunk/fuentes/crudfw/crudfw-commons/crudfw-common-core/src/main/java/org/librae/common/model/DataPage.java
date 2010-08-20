package org.librae.common.model;

import java.util.List;

/**
 * A simple class that represents a "page" of data out of a longer set, ie a
 * list of objects together with info to indicate the starting row and the full
 * size of the dataset. EJBs can return instances of this type when returning
 * subsets of available data.
 * 
 * @param <T>
 *            the data type of the page
 */
public class DataPage<T> {

    /**
     * the datasetSize.
     */
    private final int     datasetSize;

    /**
     * the startRow.
     */
    private final int     startRow;

    /**
     * the data list.
     */
    private final List<T> data;

    /**
     * Create an object representing a sublist of a dataset.
     * 
     * @param datasetSize
     *            is the total number of matching rows available.
     * @param startRow
     *            is the index within the complete dataset of the first element
     *            in the data list.
     * @param data
     *            is a list of consecutive objects from the dataset.
     */
    public DataPage(final int datasetSize, final int startRow,
            final List<T> data) {
        this.datasetSize = datasetSize;
        this.startRow = startRow;
        this.data = data;
    }

    /**
     * Return the number of items in the full dataset.
     * 
     * @return the size of the dataset
     */
    public final int getDatasetSize() {
        return datasetSize;
    }

    /**
     * Return the offset within the full dataset of the first element in the
     * list held by this object.
     * 
     * @return the startRow
     */
    public final int getStartRow() {
        return startRow;
    }

    /**
     * Return the list of objects held by this object, which is a continuous
     * subset of the full dataset.
     * 
     * @return the data, the list of elements
     */
    public final List<T> getData() {
        return data;
    }
}
