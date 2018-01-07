package com.AdidasTask.testbase;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class TestBase {

	
	@BeforeClass
	public static void init(){
		
		RestAssured.baseURI = "https://www.adidas.fi/api/pages/landing";
		
	}
}
