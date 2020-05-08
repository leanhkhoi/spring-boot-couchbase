package com.example.couchbase.product;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "product")
public interface ProductRepository extends CouchbasePagingAndSortingRepository<Product, String>, ProductRepositoryCustom {
	
    List<Product> findByName(String name);
    
    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter}")
    List<Product> findAll();
    
    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and META().id in $1")
    Page<Product> findAllById(Iterable<String> ids, Pageable pageable);
    
    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and (name like $1)")
    Page<Product> searchByName(String name, Pageable pageable);
    
    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and (category = $1)")
    Page<Product> searchByCategory(String category, Pageable pageable);
    
//    List<Building> findByCompanyId(String companyId);
//
//    Page<Building> findByCompanyIdAndNameLikeOrderByName(String companyId, String name, Pageable pageable);
//
//    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and companyId = $1 and $2 within #{#n1ql.bucket}")
//    Building findByCompanyAndAreaId(String companyId, String areaId);
//
//    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} AND ANY phone IN phoneNumbers SATISFIES phone = $1 END")
//    List<Building> findByPhoneNumber(String telephoneNumber);
//
//    @Query("SELECT COUNT(*) AS count FROM #{#n1ql.bucket} WHERE #{#n1ql.filter} and companyId = $1")
//    Long countBuildings(String companyId);
    
}