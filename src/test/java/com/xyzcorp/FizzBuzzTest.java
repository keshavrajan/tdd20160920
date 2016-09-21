package com.xyzcorp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

	@Test
	public void testConvertIntToFizzBuzzWith1() {
				// 1. Do I want make an instantiation?
				// FizzBuzz fizzBuzz = new FizzBuzz();
				// fizzBuzz.convertIntToFizzBuzz(1);
				// 2. Do I want to run this as a static method?
				// FizzBuzz.convertIntToFizzBuzz(1);
		
		assertThat(FizzBuzz.convertIntToFizzBuzz(1)).isEqualTo("1");
	}
	
	@Test
	public void testConvertIntToFizzBuzzWith3() {
				// 1. Do I want make an instantiation?
				// FizzBuzz fizzBuzz = new FizzBuzz();
				// fizzBuzz.convertIntToFizzBuzz(1);
				// 2. Do I want to run this as a static method?
				// FizzBuzz.convertIntToFizzBuzz(1);
		
		assertThat(FizzBuzz.convertIntToFizzBuzz(3)).isEqualTo("3");
	}
}
