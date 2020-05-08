package com.example.couchbase.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/shop-grid.html")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product get(@PathVariable(value = "id") String id) {
	return productService.findById(id);
    }

    @GetMapping()
    public String list(ProductCriteria criterias, Model model) {
	Page<Product> results = productService.findByCriteria(criterias);
	model.addAttribute("searchResults", results);
	model.addAttribute("searchCriterias", criterias);
	return "shop-grid";
    }

    @PostMapping
    public @ResponseBody Product save(@RequestBody Product model) {
	return productService.save(model);
    }

    /*
     * @DeleteMapping(value = "/{id}") public void delete(@PathVariable(value =
     * "id") String id) { productService.delete(id); }
     */
}
