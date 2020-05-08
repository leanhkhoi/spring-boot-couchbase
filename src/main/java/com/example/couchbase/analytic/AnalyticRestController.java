package com.example.couchbase.analytic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/analytics")
public class AnalyticRestController {

    @Autowired
    private AnalyticService analyticService;

    @GetMapping("/orders/monthly-sale-report")
    public List<MonthlyOrderReport> monthlyOrderReport(@RequestParam Integer year) {
	return analyticService.orderReportMonthly(year);
    }

    @GetMapping("/products/top-5-most-rated")
    public List<TopMostRatedProduct> top5MostRated() {
	return analyticService.top5MostRated();
    }

    @GetMapping("/users/top-5-most-buy")
    public List<TopMostBuyUser> top5MostBuy() {
	return analyticService.top5MostBuy();
    }

}
