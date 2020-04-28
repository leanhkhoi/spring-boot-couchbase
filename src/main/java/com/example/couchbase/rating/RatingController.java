package com.example.couchbase.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{id}")
    public Rating get(@PathVariable(value = "id") String id) {
        return ratingService.findById(id);
    }

    @GetMapping
    public Page<Rating> list(RatingCriteria criteria) {
        return ratingService.findByCriteria(criteria);
    }

    @PostMapping
    public @ResponseBody Rating save(@RequestBody Rating model) {
        return ratingService.save(model);
    }

    /*
     * @DeleteMapping(value = "/{id}") public void delete(@PathVariable(value = "id") String id) {
     * productService.delete(id); }
     */
}
