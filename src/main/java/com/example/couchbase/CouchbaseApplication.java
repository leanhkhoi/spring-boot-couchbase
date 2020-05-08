package com.example.couchbase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.couchbase.order.Order;
import com.example.couchbase.order.OrderService;
import com.example.couchbase.product.Product;
import com.example.couchbase.product.ProductCateogry;
import com.example.couchbase.product.ProductService;
import com.example.couchbase.rating.Rating;
import com.example.couchbase.rating.RatingService;
import com.example.couchbase.user.User;
import com.example.couchbase.user.UserService;

@SpringBootApplication
public class CouchbaseApplication implements CommandLineRunner {

    public static void main(String[] args) {
	SpringApplication.run(CouchbaseApplication.class, args);
    }

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
	//init();
    }

    private void init() {
	/*
	 * userService.delete(userService.findAll());
	 * productService.delete(productService.findAll());
	 * //userService.delete(userService.findByName("Kaitlyn Mitchell III"));
	 * //productService.delete(productService.findByName("Kaitlyn Mitchell"));
	 */

	productService.delete(productService.findAll());
	userService.delete(userService.findAll());
	ratingService.delete(ratingService.findAll());
	orderService.delete(orderService.findAll());

	List<String> products = new ArrayList<String>();
	for (int i = 0; i < 500; i++) {
	    Product product = new Product();
	    product.setName(RandomUtils.randomProductName());
	    product.setDescription(RandomUtils.randomProductDescription());
	    product.setPrice(50000L);
	    product.setMeanRating((RandomUtils.randomInteger() % 5) + 1);
	    product.setCategory(
		    ProductCateogry.values()[RandomUtils.randomInteger() % ProductCateogry.values().length]);
	    product.setCreated(new DateTime());
	    product.setUpdated(new DateTime());
	    productService.save(product);
	    products.add(product.getId());
	}

	List<User> users = new ArrayList<User>();
	for (int i = 0; i < 300; i++) {
	    User user = new User();
	    user.setName(RandomUtils.randomUserName());
	    user.setAge(RandomUtils.randomUserAge());
	    user.setCreated(new DateTime());
	    user.setUpdated(new DateTime());
	    users.add(user);
	}
	Iterable<User> savedUsers = userService.saveAll(users);

	List<Rating> ratings = new ArrayList<Rating>();
	// add rating
	// moi nguoi duoc danh gia 20 lan
	for (User u : savedUsers) {
	    Collections.shuffle(products); // xao tron mang
	    for (int j = 0; j < 20; j++) {
		Rating rating = new Rating();
		rating.setUser(u.getId());
		rating.setProduct(products.get(j));
		rating.setPoint(RandomUtils.randomDouble());
		rating.setCreated(LocalDateTime.now());
		rating.setUpdated(LocalDateTime.now());
		ratings.add(rating);
	    }
	}
	ratingService.saveAll(ratings);

	List<Order> orders = new ArrayList<Order>();
	// add rating
	// moi nguoi duoc danh gia 20 lan
	for (User u : savedUsers) {
	    Collections.shuffle(products); // xao tron mang
	    for (int j = 0; j < 30; j++) {
		Order order = new Order();
		order.setUser(u.getId());
		order.setProduct(products.get(j));
		order.setQuantity(RandomUtils.randomInteger() % 5);
		order.setCreated(RandomUtils.randomDate(2019, 2020));
		order.setUpdated(RandomUtils.randomDate(2019, 2020));
		orders.add(order);
	    }
	}

	orderService.saveAll(orders);

    }

}
