
package com.butyter.contact.core.service.mapper;

/**
 * Generic interface for mappings between entities and their data transfer
 * objects.
 * 
 * @param <T> - type of the entity
 * @param <V> - type of the data transfer object
 */
public interface Mapper<T, V> {
    /**
     * Method to map fields from entity into its data transfer object.
     * 
     * @param entity - domain object
     * @return data transfer object for entity
     */
    V toDTO(T entity);

    /**
     * Method to map fields from data transfer object into its entity.
     * 
     * @param dto - data transfer object for entity
     * @return domain object
     */
    T fromDTO(V dto);
}
