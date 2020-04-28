package com.example.couchbase;

import com.github.javafaker.Faker;

public class RandomUtils {
	
	public static String randomUserName() {
		return new Faker().name().fullName(); // Miss Samanta Schmidt
	}
	
	public static int randomUserAge() {
		return new Faker().number().numberBetween(6, 200); // Miss Samanta Schmidt
	}
	
	public static String randomProductName() {
		return new Faker().commerce().productName();
	}
	
	public static String randomProductDescription() {
		return new Faker().book().title();
	}
	
	public static Double randomDouble() {
        return new Faker().number().randomDouble(1, 1, 5);
    }
}
