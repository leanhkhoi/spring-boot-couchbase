package com.example.couchbase.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.couchbase.CouchbaseUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CouchbaseUtils couchbaseUtils;
	
	
	@Override
	public Product findById(String id) {
		return null;
	}
	
	@Override
	public List<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public Product save(@Valid Product product) {
		
		if (product.getId() == null) {
			product.setId(String.valueOf(couchbaseUtils.getNextId()));
		}
		
		return productRepository.save(product);
	}
	

	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public void delete(Iterable<Product> products) {
		productRepository.deleteAll(products);
	}

	@Override
	public Page<Product> findByCriteria(ProductCriteria criteria) {
		return productRepository.findByCriteria(criteria);
	}

	

//    @Autowired
//    private BuildingRepository buildingRepository;
//
//    @Override
//    public List<Building> findByCompanyId(String companyId) {
//        return buildingRepository.findByCompanyId(companyId);
//    }
//
//    public List<Building> findByCompanyIdAndNameLike(String companyId, String name, int page) {
//        return buildingRepository.findByCompanyIdAndNameLikeOrderByName(companyId, name, new PageRequest(page, 20))
//                .getContent();
//    }
//
//    @Override
//    public Building findByCompanyAndAreaId(String companyId, String areaId) {
//        return buildingRepository.findByCompanyAndAreaId(companyId, areaId);
//    }
//
//    @Override
//    public List<Building> findByPhoneNumber(String telephoneNumber) {
//        return buildingRepository.findByPhoneNumber(telephoneNumber);
//    }
//
//    @Override
//    public Building findById(String buildingId) {
//        return buildingRepository.findOne(buildingId);
//    }
//
//    @Override
//    public Building save(@Valid Building building) {
//        return buildingRepository.save(building);
//    }
//
//    @Override
//    public Long countBuildings(String companyId) {
//        return buildingRepository.countBuildings(companyId);
//    }
}
