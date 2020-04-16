package com.example.couchbase.user;

import java.util.List;

import javax.validation.Valid;

import com.example.couchbase.product.Product;

public interface UserService {

    User save(@Valid User product);

    List<User> findByName(String name);
    
    Iterable<User> findAll();

    void delete(Iterable<User> users);
    
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
