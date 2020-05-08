package com.example.couchbase;

import java.time.LocalDateTime;

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
    
    public static Integer randomInteger() {
	return new Faker().number().numberBetween(0, Integer.MAX_VALUE);
    }
    
    public static LocalDateTime randomDate(Integer from, Integer to) {
	int day = (randomInteger() % 28) + 1;
	int month = (randomInteger() % 12) + 1;
	int year = from + (randomInteger() % (to - from + 1));
	return LocalDateTime.of(year, month, day, 0, 0);
    }
}
