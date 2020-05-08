package com.example.couchbase.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.search.HighlightStyle;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchQueryResult;
import com.couchbase.client.java.search.result.SearchQueryRow;
import com.example.couchbase.CouchbaseUtils;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouchbaseUtils couchbaseUtils;

    @Autowired
    private CouchbaseTemplate template;

    @Override
    public Product findById(String id) {
	return null;
    }

    @Override
    public Page<Product> findByName(String name, Pageable pageable) {
	Page<Product> rs = productRepository.searchByName("%" + name + "%", pageable);

	return rs;
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
	Page<Product> rs;
	if (StringUtils.isNotBlank(criteria.getQuery())) {
	    Bucket bucket = productRepository.getCouchbaseOperations().getCouchbaseBucket();
	    SearchQuery query = new SearchQuery("search-product", SearchQuery.match(criteria.getQuery()))
		    .limit(criteria.getPageSize()).skip(criteria.getPageSize() * criteria.getPageIndex())
		    .highlight(HighlightStyle.HTML);
	    SearchQueryResult searchResults = bucket.query(query);
	    // get contents from search results
	    List<Product> products = new ArrayList<Product>();
	    for (int i = 0; i < searchResults.hits().size(); i++) {
		SearchQueryRow row = searchResults.hits().get(i);
		JsonObject doc = bucket.get(row.id()).content();
		Product product = new Product();
		product.setId(row.id());
		product.setName(doc.getString("name"));
		product.setDescription(doc.getString("description"));
		product.setMeanRating(doc.getInt("meanRating"));
		product.setPrice(doc.getLong("price"));

		Map<String, List<String>> fragments = row.fragments();
		if (fragments.containsKey("description")) {
		    product.setDescription(fragments.get("description").get(0));
		}
		if (fragments.containsKey("name")) {
		    product.setName(fragments.get("name").get(0));
		}
		product.setMatchContent(fragments.get(fragments.keySet().iterator().next()).get(0));

		products.add(product);
	    }

	    // set up Page response
	    rs = new PageImpl<Product>(products, PageRequest.of(criteria.getPageIndex(), criteria.getPageSize()),
		    searchResults.metrics().totalHits());
	} else if (StringUtils.isNotBlank(criteria.getName())) {
	    rs = productRepository.searchByName("%" + criteria.getName() + "%",
		    PageRequest.of(criteria.getPageIndex(), criteria.getPageSize()));
	} else if (criteria.getCategory() != null) {
	    rs = productRepository.searchByCategory(criteria.getCategory().toString(),
		    PageRequest.of(criteria.getPageIndex(), criteria.getPageSize()));
	} else {
	    rs = productRepository.findAll(PageRequest.of(criteria.getPageIndex(), criteria.getPageSize()));
	}

//	for (int i = 0; i < rs.getContent().size(); i++) {
//	    Product product = rs.getContent().get(i);
//
//	    N1qlQuery n1ql = N1qlQuery.simple("SELECT META(rks).id AS _ID, META(rks).cas AS _CAS, (rks).*"
//		    + "FROM `shop-store` lks JOIN `shop-store` rks ON lks.name=rks.product"
//		    + "    AND rks.type = 'RATING' WHERE META(lks).id='" + product.getId() + "'");
//
//	    product.setRatings(template.findByN1QL(n1ql, Rating.class));
//	}
	return rs;// productRepository.findByCriteria(criteria);
    }
}
