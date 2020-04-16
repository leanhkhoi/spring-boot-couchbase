package com.example.couchbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Component;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.error.DocumentDoesNotExistException;

@Component
public class CouchbaseUtils {
	@Autowired
	private Environment env;
	
	@Autowired
	private CouchbaseTemplate couchbaseTemplate;
	
	public long getNextId() {
		String key = env.getProperty("couchbase.counter.key");
		Bucket bucket = couchbaseTemplate.getCouchbaseBucket();

		try {
			bucket.counter(key, 0, 20);
		} catch (DocumentDoesNotExistException e) {
			e.printStackTrace();
		}

		return bucket.counter(key, 1).content();
	}
}
