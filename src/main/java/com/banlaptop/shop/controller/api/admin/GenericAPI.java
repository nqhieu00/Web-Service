package com.banlaptop.shop.controller.api.admin;


import com.banlaptop.shop.dto.Filter;
import com.banlaptop.shop.exception.GenericException;
import com.banlaptop.shop.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoRepositoryBean
@PreAuthorize("hasAnyRole(#root.this.roles)")
public abstract class GenericAPI<E,I,D> {

    GenericService<E,I,D> genericService;
    public abstract String getRoles();

    public GenericAPI(GenericService<E,I,D> genericService){
        this.genericService=genericService;
    }


    @GetMapping
    public ResponseEntity<Page<D>> getPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "sort") String sort,
            @RequestBody(required = false)List<Filter> filters){
        try {
            return new ResponseEntity(genericService.getPage(page,limit,sort,filters),HttpStatus.OK);
        }
        catch (GenericException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getOne(@PathVariable I id) {
        try {
            return new ResponseEntity(genericService.get(id),HttpStatus.OK);
        }
        catch (GenericException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @PostMapping
    public ResponseEntity create(@RequestBody D dto){

        try {
            System.out.println(dto.toString());
            return new ResponseEntity(genericService.create(dto),HttpStatus.CREATED);
        }
        catch (GenericException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }


    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@RequestBody D dto,@PathVariable I id){

        try {
            System.out.println(dto.toString());
            return new ResponseEntity(genericService.update(dto,id),HttpStatus.OK);
        }
        catch (GenericException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable I id){

        try {
            genericService.delete(id);
            return new ResponseEntity("Delete id = "+id+" successful",HttpStatus.OK);
        }
        catch (GenericException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

}
