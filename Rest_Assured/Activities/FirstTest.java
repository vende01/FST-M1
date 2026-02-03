package Example;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstTest {
	 @Test
	public static void getRequestwithQueryParam() {
		// GET https://petstore.swagger.io/v2/pet/findByStatus?status=sold
		//Send request,Save response
		Response response =
				RestAssured.given().
				baseUri("https://petstore.swagger.io/v2/pet").
				header("Content-Type","application/json").
				queryParam("status", "sold").
				when().get("/findByStatus");
		//Print the response
		System.out.println(response.getBody().asPrettyString());
		String PetStatus = response.then().extract().path("[0].status");
		System.out.println(PetStatus);
		//Assertion
		Assert.assertEquals(PetStatus, "sold");
		//RestAssured Assertion
		response.then().statusCode(200).body("[0].status",Matchers.equalTo("sold"));
		System.out.println("======================================================");
		}
		//
		@Test
		public void getRequestWithPathParam() {
			// Send request, receive response, assert
			RestAssured.given().
				baseUri("https://petstore.swagger.io/v2/pet").
				header("Content-Type", "application/json"). // Define request type
				pathParam("petId", 12).
			when().
				get("/{petId}"). // get("/12")
			then().
				statusCode(200).
				body("status", Matchers.equalTo("available")).
				body("name", Matchers.equalTo("doggie"));
		
	}
	

}
