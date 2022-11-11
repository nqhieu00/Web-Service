package com.banlaptop.shop.service.imp;


import com.banlaptop.shop.dto.Filter;
import com.banlaptop.shop.exception.GenericException;
import com.banlaptop.shop.repository.GenericRepository;
import com.banlaptop.shop.service.GenericService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;


public abstract class GenericServiceImp<E, I extends Serializable, D> implements GenericService<E, I, D> {




    private GenericRepository<E,I> repository;

    public GenericServiceImp(GenericRepository<E,I> repository) {
        this.repository = repository;
    }



    @Override
    public D get(I id) {

        Optional<E> element = repository.findById(id);
        if (element.isPresent()) {
            return this.transformEntityToDTO((element.get()));
        } else {
            throw new GenericException("Not Exists Id");
        }


    }

    @Override
    public List<D> getAll() {


        List<E> list = repository.findAll();
        return list.stream().map(e -> transformEntityToDTO(e)).collect(Collectors.toList());
    }

    private void setOrders(String sort,List<Sort.Order> orders){
        Sort.Order order;
        if(sort.contains("-")){
            // sort=-price
            sort=sort.substring(1);
            // sort=price
            order=new Sort.Order(Sort.Direction.DESC,sort);
            orders.add(order);
        }
        else if(sort.contains(" ")){
            sort=sort.substring(1);
            order=new Sort.Order(Sort.Direction.ASC,sort);
            orders.add(order);
        }
        else {
            order=new Sort.Order(Sort.Direction.ASC,sort);
        }
        orders.add(order);
    }

    @Override
    public Page<D> getPage(int page, int limit,String sort,List<Filter> filters) {
        List<Sort.Order> orders=new ArrayList<>();
        //sort=+id,-price,...
        String[] sorts=sort.split(",");
        Arrays.stream(sorts).forEach(i-> setOrders(i,orders));
        Sort sorting=Sort.by(orders);
        Pageable pageable =PageRequest.of(page, limit,sorting);
        Optional<List<Filter>> optionalFilters=Optional.ofNullable(filters);
        if(optionalFilters.isPresent()){
            try {
                return repository.findAll(getSpecification(filters),pageable)
                        .map(this::transformEntityToDTO);
            }catch (Exception e){
                throw new GenericException("Field does not exist");
            }
        }
        return repository.findAll(pageable)
                .map(this::transformEntityToDTO);


    }
    @Override
    public Specification<E> getSpecification(List<Filter> filters) {
        Specification<E> specification =
                where(createSpecification(filters.remove(0)));
        for (Filter input : filters) {
            specification = specification.and(createSpecification(input));
        }
        return specification;
    }
    private Object castToRequiredType(Class fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if(fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if(Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        }
        else if(fieldType.isAssignableFrom(String.class)){
            return String.valueOf(value);
        }
        return null;
    }

    private Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }
    private Specification<E> createSpecification(Filter input) {

            switch (input.getOperator()){
                case EQUALS:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.equal(root.get(input.getField()),
                                    castToRequiredType(root.get(input.getField()).getJavaType(),
                                            input.getValue()));

                case NOT_EQUALS:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.notEqual(root.get(input.getField()),
                                    castToRequiredType(root.get(input.getField()).getJavaType(),
                                            input.getValue()));

                case GREATER_THAN:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.gt(root.get(input.getField()),
                                    (Number) castToRequiredType(
                                            root.get(input.getField()).getJavaType(),
                                            input.getValue()));

                case LESS_THAN:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.lt(root.get(input.getField()),
                                    (Number) castToRequiredType(
                                            root.get(input.getField()).getJavaType(),
                                            input.getValue()));

                case LIKE:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(root.get(input.getField()),
                                    "%"+input.getValue()+"%");

                case IN:
                    return (root, query, criteriaBuilder) ->
                            criteriaBuilder.in(root.get(input.getField()))
                                    .value(castToRequiredType(
                                            root.get(input.getField()).getJavaType(),
                                            input.getValues()));

                default:
                    throw new RuntimeException("Operation not supported yet");
            }

    }

    @Override
    @Transactional
    public D create(D element) {
        try {
            return transformEntityToDTO(repository.saveAndFlush(transformDTOToEntity(element)));
        } catch (Exception e) {
            throw new GenericException("error occurred when adding");
        }

    }

    @Override
    @Transactional
    public D update(D element, I id) {
        Optional<E> eOptional = repository.findById(id);
        if (eOptional.isPresent()) {
            try {
                setId(id,element,"id");
                E e=repository.saveAndFlush(transformDTOToEntity(element));
                return this.transformEntityToDTO(e);

            }
            catch (Exception e){
                throw new GenericException("error occurred when updating");
            }

        }
        else {
            throw new GenericException("Not Exists id");
        }
    }
    private void setId(I id, Object trg, String props) {
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
        trgWrap.setPropertyValue(props,id);
    }

    @Override
    @Transactional
    public void delete(I id) {

       try {
           repository.deleteById(id);
       }
       catch (Exception e){
           throw new GenericException("error occurred when deleting");
       }

    }

    @Override
    public boolean isExist(I id) {

        return repository.existsById(id);
    }

    @Override
    public E transformDTOToEntity(D element) {
        return null;
    }

    @Override
    public D transformEntityToDTO(E element) {
        return null;
    }

}
