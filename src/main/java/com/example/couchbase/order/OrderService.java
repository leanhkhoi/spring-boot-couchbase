package com.example.couchbase.order;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

public interface OrderService {

    Order findById(String id);

    Order save(@Valid Order order);
    
    Iterable<Order> saveAll(@Valid Iterable<Order> orders);

    Iterable<Order> findAll();

    void delete(Iterable<Order> orders);
    /*
     * Building save(@Valid Building building); Building findById(String buildingId); List<Building> findByCompanyId(String
     * companyId); Building findByCompanyAndAreaId(String companyId, String areaId); List<Building>
     * findByCompanyIdAndNameLike(String companyId, String name, int page); List<Building> findByPhoneNumber(String
     * telephoneNumber); Long countBuildings(String companyId);
     */

    Page<Order> findByCriteria(OrderCriteria criteria);

}
