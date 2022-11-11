package com.banlaptop.shop.service;

import com.banlaptop.shop.dto.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface GenericService<E,I,D> {
    /**
     * E : Entity Class
     * I : type of Id element
     * D : DTO POJO
     */

    public D get(I id);
    public Page<D> getPage(int page , int limit, String sort,List<Filter> filters);
    public List<D> getAll();
    public D update(D element,I id);
    public D create(D element);
    public void delete(I id);
    public boolean isExist(I id);
    public E transformDTOToEntity(D element);
    public D transformEntityToDTO(E element);
    public Specification<E> getSpecification(List<Filter> filters);
}
