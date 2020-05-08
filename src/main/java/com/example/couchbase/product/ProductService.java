package com.example.couchbase.product;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product findById(String id);

    Product save(@Valid Product product);

    Page<Product> findByName(String name, Pageable pageable);

    Iterable<Product> findAll();

    void delete(Iterable<Product> products);
    /*
     * Building save(@Valid Building building);
     * 
     * Building findById(String buildingId);
     * 
     * List<Building> findByCompanyId(String companyId);
     * 
     * Building findByCompanyAndAreaId(String companyId, String areaId);
     * 
     * List<Building> findByCompanyIdAndNameLike(String companyId, String name, int
     * page);
     * 
     * List<Building> findByPhoneNumber(String telephoneNumber);
     * 
     * Long countBuildings(String companyId);
     */

    Page<Product> findByCriteria(ProductCriteria criteria);
}
