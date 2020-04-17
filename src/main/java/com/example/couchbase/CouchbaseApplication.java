package com.example.couchbase;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.couchbase.product.Product;
import com.example.couchbase.product.ProductService;
import com.example.couchbase.user.User;
import com.example.couchbase.user.UserService;

@SpringBootApplication
public class CouchbaseApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CouchbaseApplication.class, args);
	}
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		init();
	}
	
	private void init() {
		/*userService.delete(userService.findAll());
		productService.delete(productService.findAll());
		
		//userService.delete(userService.findByName("Kaitlyn Mitchell III"));
		//productService.delete(productService.findByName("Kaitlyn Mitchell"));
		
		
		List<String> products = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			Product product = new Product();
			product.setName(RandomUtils.randomProductName());
			product.setDescription(RandomUtils.randomProductDescription());
			product.setPrice(50000L);
			product.setCreated(new DateTime());
			product.setUpdated(new DateTime());
			productService.save(product);
			products.add(product.getId());
		}
		
		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setName(RandomUtils.randomUserName());
			user.setAge(RandomUtils.randomUserAge());
			user.setInterestingProducts(products);
			user.setCreated(new DateTime());
			user.setUpdated(new DateTime());
			userService.save(user);
		}*/
		 
	}
	
}
