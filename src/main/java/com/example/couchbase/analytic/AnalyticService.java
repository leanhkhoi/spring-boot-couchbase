package com.example.couchbase.analytic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.analytics.AnalyticsQuery;
import com.couchbase.client.java.analytics.AnalyticsQueryResult;
import com.couchbase.client.java.document.json.JsonObject;

@Service
public class AnalyticService {

    @Autowired
    private CouchbaseTemplate template;

    public AnalyticsQueryResult analyticProduct() {
	Bucket bucket = template.getCouchbaseBucket();

//	"SELECT r.*,\r\n" + 
//	"       u as rater\r\n" + 
//	"FROM products p\r\n" + 
//	"    JOIN ratings r ON meta(p).id=r.product\r\n" + 
//	"    JOIN users u ON r.`user`=meta(u).id\r\n" + 
//	"WHERE META(p).id='1132'"
	return bucket.query(
		AnalyticsQuery.simple("SELECT airportname, country FROM airports WHERE country = 'France' LIMIT 5"));
    }

    public List<MonthlyOrderReport> orderReportMonthly(Integer year) {
	Bucket bucket = template.getCouchbaseBucket();
	AnalyticsQueryResult analyticResult = bucket.query(AnalyticsQuery
		.simple("SELECT COUNT(*) AS amount,"
			+ " DATE_PART_MILLIS(o.created, 'month') AS month,"
			+ " DATE_PART_MILLIS(o.created, 'year') as year"
			+ " FROM orders o"
			+ " WHERE DATE_PART_MILLIS(o.created, 'year') = " + year
			+ " GROUP BY"
			+ " DATE_PART_MILLIS(o.created, 'month'),"
			+ " DATE_PART_MILLIS(o.created, 'year')"
			+ " ORDER BY"
			+ " DATE_PART_MILLIS(o.created, 'year'),"
			+ " DATE_PART_MILLIS(o.created, 'month')"));
	List<MonthlyOrderReport> results = analyticResult.allRows().stream().map(r -> {
	    JsonObject jsonValue = r.value();
	    MonthlyOrderReport o = new MonthlyOrderReport();
	    o.setAmount(jsonValue.getInt("amount"));
	    o.setMonth(jsonValue.getInt("month"));
	    o.setYear(jsonValue.getInt("year"));
	    return o;
	}).collect(Collectors.toList());

	return results;
    }

    public List<TopMostRatedProduct> top5MostRated() {
	Bucket bucket = template.getCouchbaseBucket();
	AnalyticsQueryResult analyticResult = bucket.query(AnalyticsQuery
		.simple("SELECT META(p).id AS id, p.name AS name, COUNT(*) AS numberOfRates, AVG(r.point) AS avg" + 
			" FROM ratings r " + 
			" JOIN products p ON r.product = META(p).id" + 
			" GROUP BY META(p).id, p.name" + 
			" HAVING COUNT(*) > 0" + 
			" ORDER BY numberOfRates DESC" + 
			" LIMIT 5"));
	List<TopMostRatedProduct> results = analyticResult.allRows().stream().map(r -> {
	    JsonObject jsonValue = r.value();
	    TopMostRatedProduct o = new TopMostRatedProduct();
	    o.setId(jsonValue.getString("id"));
	    o.setName(jsonValue.getString("name"));
	    o.setNumberOfRates(jsonValue.getInt("numberOfRates"));
	    o.setAvg(jsonValue.getDouble("avg"));
	    return o;
	}).collect(Collectors.toList());
	return results;
    }

    public List<TopMostBuyUser> top5MostBuy() {
	Bucket bucket = template.getCouchbaseBucket();
	AnalyticsQueryResult analyticResult = bucket.query(AnalyticsQuery
		.simple("SELECT META(u).id as id, u.name AS name,"
			+ " SUM(o.quantity) AS numberOfProducts,"
			+ " SUM(o.quantity * p.price) AS totalBudget"
			+ " FROM orders o"
			+ " JOIN users u on o.user = meta(u).id"
			+ " JOIN products p on o.product = meta(p).id"
			+ " GROUP BY META(u).id, u.name"
			+ " HAVING COUNT(*) > 0"
			+ " ORDER BY totalBudget DESC"
			+ " LIMIT 5"));
	List<TopMostBuyUser> results = analyticResult.allRows().stream().map(r -> {
	    JsonObject jsonValue = r.value();
	    TopMostBuyUser o = new TopMostBuyUser();
	    o.setId(jsonValue.getString("id"));
	    o.setName(jsonValue.getString("name"));
	    o.setNumberOfProducts(jsonValue.getInt("numberOfProducts"));
	    o.setTotalBudget(jsonValue.getInt("totalBudget"));
	    return o;
	}).collect(Collectors.toList());
	return results;
    }

}
