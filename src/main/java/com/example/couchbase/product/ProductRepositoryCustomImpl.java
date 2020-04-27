package com.example.couchbase.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.core.mapping.CouchbasePersistentEntity;
import org.springframework.data.couchbase.repository.query.CouchbaseEntityInformation;
import org.springframework.data.couchbase.repository.query.CountFragment;
import org.springframework.data.couchbase.repository.query.support.N1qlUtils;
import org.springframework.data.couchbase.repository.support.MappingCouchbaseEntityInformation;
import org.springframework.data.domain.Page;

import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.Statement;

@SuppressWarnings("unchecked")
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

	@Autowired
	private CouchbaseOperations operations;
	
	@Override
	public Page<Product> findByCriteria(ProductCriteria criteria) {
		
		CouchbasePersistentEntity<Object> itemPersistenceEntity = (CouchbasePersistentEntity<Object>)
				operations.getConverter()
		            .getMappingContext()
		            .getPersistentEntity(Product.class);

		    CouchbaseEntityInformation<? extends Object, String> itemEntityInformation =
		        new MappingCouchbaseEntityInformation<Object, String>(itemPersistenceEntity);
		    
		Statement countStatement = N1qlUtils.createCountQueryForEntity(operations.getCouchbaseBucket().name(),
				operations.getConverter(), itemEntityInformation);
		//N1qlUtils.buildQuery(statement, queryPlaceholderValues, scanConsistency)

		//ScanConsistency consistency = operations.getDefaultConsistency().n1qlConsistency();
		N1qlParams queryParams = N1qlParams.build();
		N1qlQuery query = N1qlQuery.simple(countStatement, queryParams);

		List<CountFragment> countFragments = operations.findByN1QL(query, CountFragment.class);

		/*N1qlQuery query = 
		List<Product> objects = operation.findByN1QL(n1ql, Product.class);*/
		
		return null;
	}

}
