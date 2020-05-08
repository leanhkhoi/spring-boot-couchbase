package com.example.couchbase.order;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.couchbase.CouchbaseUtils;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CouchbaseUtils couchbaseUtils;

    @Override
    public Order findById(String id) {
	return null;
    }

    @Override
    public Order save(@Valid Order rating) {

	if (rating.getId() == null) {
	    rating.setId(String.valueOf(couchbaseUtils.getNextId()));
	}

	return orderRepository.save(rating);
    }

    public Iterable<Order> saveAll(@Valid Iterable<Order> ratings) {
	for (Order r : ratings) {
	    if (r.getId() == null) {
		r.setId(String.valueOf(couchbaseUtils.getNextId()));
	    }
	}
	return orderRepository.saveAll(ratings);
    }

    @Override
    public Iterable<Order> findAll() {
	return orderRepository.findAll();
    }

    @Override
    public void delete(Iterable<Order> products) {
	orderRepository.deleteAll(products);
    }

    @Override
    public Page<Order> findByCriteria(OrderCriteria criteria) {
	Page<Order> rs = orderRepository.findAll(PageRequest.of(0, 5));
	return rs;
    }
}
