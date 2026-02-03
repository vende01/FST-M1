package projects;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Restassured_Project1 {

	RequestSpecification requestSpec;
	static int responseID;

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "https://api.github.com";
		requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.addHeader("Authorization", "token ghp_removed_due_to_security_purpose").build();
		Reporter.log("Request specification initialized with GitHub token.", true);

	}

	@Test(priority = 1)
	public void createSSHKey() {
		String requestBody = "{\r\n" + "  \"title\": \"ShuvamKey\",\r\n"
				+ "  \"key\": \"ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIFxD8/NVOcZzUz86OTPx95f1n3OqhniMraxCOXGjPgLK azuread-shuvambasak@IBM-392NSV3\"\r\n"
				+ "}";

		Response response = given().spec(requestSpec).body(requestBody).when().post("/user/keys").then().assertThat()
				.statusCode(201).extract().response();
		Reporter.log("POST /user/keys Response:\n" + response.asPrettyString(), true);
		responseID = response.jsonPath().getInt("id");

	}

	@Test(priority = 2)
	public void getSSHKey() {
		Response response = given().spec(requestSpec).pathParam("keyId", responseID) 
				.when().get("/user/keys/{keyId}").then().assertThat().statusCode(200).extract().response();
        Reporter.log("GET /user/keys/{keyId} Response:\n" + response.asPrettyString(), true);
	}

	@Test(priority = 3)
	public void deleteSSHKey() {
		Response response = given().spec(requestSpec).pathParam("keyId", responseID) 
				.when().delete("/user/keys/{keyId}").then().assertThat().statusCode(204).extract().response();
        Reporter.log("DELETE /user/keys/{keyId} Response:\n" + response.asPrettyString(), true);
	}

}

