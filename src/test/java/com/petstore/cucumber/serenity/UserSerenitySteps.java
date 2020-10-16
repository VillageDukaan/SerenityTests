package com.petstore.cucumber.serenity;

import java.util.HashMap;

import com.petstore.model.PetClass;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSerenitySteps {
	
	@Step("Creating user with username: {0}, firstName: {1}, lastName: {2}, email: {3}, password: {4}, phone: {5}, userStatus: {6}")
	public ValidatableResponse createUser(String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
		PetClass user = new PetClass();
		
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setStatus(userStatus);
		
		return SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when()
		.body(user)
		.post()
		.then();
	}
	
	@Step("Get information of the user with username: {0}")
	public ValidatableResponse getUserByUsername(String username){
		return 
		SerenityRest
		.rest()
		.given()
		.when()
		.get("/" + username).then();
	}
	
	@Step("Update user information username: {0}, firstName: {1}, lastName: {2}, email: {3}, password: {4}, phone: {5}, userStatus: {6}")
	public ValidatableResponse updateUser(String username, String lastName, String firstName, String email, String password, String phone, int userStatus) {
		PetClass user = new PetClass();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setStatus(userStatus);
		
		return SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.when()
		.body(user)
		.put("/" + username)
		.then();
	}
	
	@Step("Deleting user information with username: {0}")
	public  void deleteUser(String username) {
		SerenityRest.rest().given().when().delete("/" + username);
	}
	
}
