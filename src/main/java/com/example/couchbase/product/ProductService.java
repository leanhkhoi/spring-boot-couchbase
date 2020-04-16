package com.example.couchbase.product;

import java.util.List;

import javax.validation.Valid;

public interface ProductService {

    Product save(@Valid Product product);

    List<Product> findByName(String name);
    
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
}
