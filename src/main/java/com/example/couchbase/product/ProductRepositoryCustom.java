package com.example.couchbase.product;

import org.springframework.data.domain.Page;

public interface ProductRepositoryCustom {
	
	Page<Product> findByCriteria(ProductCriteria criteria);

}
