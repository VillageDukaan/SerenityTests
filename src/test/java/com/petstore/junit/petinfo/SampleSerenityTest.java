package com.petstore.junit.petinfo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;

@RunWith(SerenityRunner.class)
public class SampleSerenityTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="https://petstore.swagger.io/v2/pet";
	}
	
	@Test
	public void getAllPets() {
		SerenityRest.given()
		.when()
		.get("/findByStatus?status=available")
		.then()
		.log()
		.all()
		.statusCode(200);
	}

}
