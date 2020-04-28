package com.example.couchbase.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
        Page<Product> rs = productRepository.search("a", "%a%", PageRequest.of(0, 5));
        // Page<Product> rs = productRepository.findAllById(Arrays.asList("116", "117", "118"), PageRequest.of(0,5));
        // productRepository.findByName("123124");
        return rs;// productRepository.findByCriteria(criteria);
    }

    @Override
    public Page<ProductFullTextSearchDto> fullTextSearch(ProductFullTextSearch criteria) {
        Bucket bucket = productRepository.getCouchbaseOperations().getCouchbaseBucket();
        SearchQuery query = new SearchQuery("search-product", SearchQuery.match(criteria.getQuery())).limit(criteria.getPageSize())
                .skip(criteria.getPageSize() * criteria.getPageIndex()).highlight(HighlightStyle.HTML);
        SearchQueryResult searchResults = bucket.query(query);
        
        // get contents from search results
        List<ProductFullTextSearchDto> products = new ArrayList<ProductFullTextSearchDto>();
        for (int i = 0; i < searchResults.hits().size(); i++) {
            SearchQueryRow row = searchResults.hits().get(i);
            JsonObject doc = bucket.get(row.id()).content();
            ProductFullTextSearchDto product = new ProductFullTextSearchDto();
            product.setId(row.id());
            product.setName(doc.getString("name"));
            product.setDescription(doc.getString("description"));
            product.setPrice(doc.getLong("price"));

            Map<String, List<String>> fragments = row.fragments();
            product.setMatchContent(fragments.get(fragments.keySet().iterator().next()).get(0));

            products.add(product);
        }

        // set up Page response
        return new PageImpl<ProductFullTextSearchDto>(products, PageRequest.of(criteria.getPageIndex(), criteria.getPageSize()),
                searchResults.metrics().totalHits());
    }

    // @Autowired
    // private BuildingRepository buildingRepository;
    //
    // @Override
    // public List<Building> findByCompanyId(String companyId) {
    // return buildingRepository.findByCompanyId(companyId);
    // }
    //
    // public List<Building> findByCompanyIdAndNameLike(String companyId, String name, int page) {
    // return buildingRepository.findByCompanyIdAndNameLikeOrderByName(companyId, name, new PageRequest(page, 20))
    // .getContent();
    // }
    //
    // @Override
    // public Building findByCompanyAndAreaId(String companyId, String areaId) {
    // return buildingRepository.findByCompanyAndAreaId(companyId, areaId);
    // }
    //
    // @Override
    // public List<Building> findByPhoneNumber(String telephoneNumber) {
    // return buildingRepository.findByPhoneNumber(telephoneNumber);
    // }
    //
    // @Override
    // public Building findById(String buildingId) {
    // return buildingRepository.findOne(buildingId);
    // }
    //
    // @Override
    // public Building save(@Valid Building building) {
    // return buildingRepository.save(building);
    // }
    //
    // @Override
    // public Long countBuildings(String companyId) {
    // return buildingRepository.countBuildings(companyId);
    // }
}
