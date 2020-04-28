package com.example.couchbase.rating;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

public interface RatingService {

    Rating findById(String id);

    Rating save(@Valid Rating rating);

    Iterable<Rating> findAll();

    void delete(Iterable<Rating> ratings);
    /*
     * Building save(@Valid Building building); Building findById(String buildingId); List<Building> findByCompanyId(String
     * companyId); Building findByCompanyAndAreaId(String companyId, String areaId); List<Building>
     * findByCompanyIdAndNameLike(String companyId, String name, int page); List<Building> findByPhoneNumber(String
     * telephoneNumber); Long countBuildings(String companyId);
     */

    Page<Rating> findByCriteria(RatingCriteria criteria);

}
