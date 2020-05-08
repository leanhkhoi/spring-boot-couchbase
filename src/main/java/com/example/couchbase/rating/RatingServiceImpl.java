package com.example.couchbase.rating;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.couchbase.CouchbaseUtils;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CouchbaseUtils couchbaseUtils;

    @Override
    public Rating findById(String id) {
	return null;
    }

    @Override
    public Rating save(@Valid Rating rating) {

	if (rating.getId() == null) {
	    rating.setId(String.valueOf(couchbaseUtils.getNextId()));
	}

	return ratingRepository.save(rating);
    }

    public Iterable<Rating> saveAll(@Valid Iterable<Rating> ratings) {
	for (Rating r : ratings) {
	    if (r.getId() == null) {
		r.setId(String.valueOf(couchbaseUtils.getNextId()));
	    }
	}
	return ratingRepository.saveAll(ratings);
    }

    @Override
    public Iterable<Rating> findAll() {
	return ratingRepository.findAll();
    }

    @Override
    public void delete(Iterable<Rating> products) {
	ratingRepository.deleteAll(products);
    }

    @Override
    public Page<Rating> findByCriteria(RatingCriteria criteria) {
	Page<Rating> rs = ratingRepository.findAll(PageRequest.of(0, 5));
	return rs;
    }
}
