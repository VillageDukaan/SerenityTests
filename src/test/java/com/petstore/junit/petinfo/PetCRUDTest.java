package com.petstore.junit.petinfo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.petstore.cucumber.serenity.UserSerenitySteps;
import com.petstore.model.PetClass;
import com.petstore.testbase.TestBase;
import com.petstore.utils.TestUtils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetCRUDTest extends TestBase {
	
	static String username = TestUtils.getRandomValue() + "PetLover";
	static String firstName = "PetLover";
	static String lastName = "PetLover";
	static String email = TestUtils.getRandomValue() + "xyz@gmail.com"; 
	static String password = "petlover123";
	static String phone = "9182" + TestUtils.getRandomValue();
	static int userStatus = 0;
	
	
	@Steps
	UserSerenitySteps steps;
	
	@Title("This test will create a new User")
	@Test
	public void createUser() {
		steps.createUser(username, firstName, lastName, email, phone, password, userStatus)
		.statusCode(200);
	}
	
	@Title("This test will update a user")
	@Test
	public void updateUser() {
		lastName = lastName + "_updated";
		firstName = firstName + "_updated";
		
		steps.updateUser(username, firstName, lastName, email, phone, password, userStatus);
		
		steps.getUserByUsername(username)
		.assertThat()
		.body("firstName", containsString(firstName))
		.body("lastName", containsString(lastName));
		
	}
	
	@Title("Delete the user and verify if the user is deleted!")
	@Test
	public void deleteUser(){
		steps.deleteUser(username);
		steps.getUserByUsername(username).statusCode(404);
	}
}
