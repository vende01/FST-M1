package Project;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestAssuredProject {
	//ssh keys to test with
	String sshkey = "ssh-xxxxxxxxxxxxxxxxxxxxxx";
	// temp variable to share id
	int keyid;
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	
	@BeforeClass
	public void Setup() {
		//Initialize the request specification
		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://api.github.com/user/keys").
				addHeader("content-type","application/json").
				addHeader("Authorization","token ghp_bbbbbbbbbbbbbbbbbbbbbbbbbb").
				addHeader("X-GitHub-Api-Version", "2022-11-28").
				build();
		//Initialize the request specification
		responseSpec = new ResponseSpecBuilder().
				expectBody("title",Matchers.equalTo("TestKey")).
				expectBody("key", Matchers.equalTo(sshkey)).
				expectResponseTime(Matchers.lessThanOrEqualTo(3000L)).
				build();
				

	}
	@Test (priority = 1)
	public void postRequestTest() {
		HashMap<String,String> reqBody = new HashMap<>();
		reqBody.put("title", "TestKey");
		reqBody.put("key", "sshkey");
		Response response = RestAssured.given().
				spec(requestSpec).
				body(reqBody).
		when().post();
		
		keyid = response.then().extract().path("id");
		response.then().statusCode(201).spec(responseSpec);
	   		
	}
	@Test (priority = 2)
	public void getRequestTest() {
		
		RestAssured.given().spec(requestSpec).pathParam("keyid", keyid).
		when().get("/{keyid}").
		then().statusCode(204);
	}
	@Test (priority = 3)
	public void deleteRequestTest() {
		
		RestAssured.given().spec(requestSpec).pathParam("keyid", keyid).
		when().delete("/{keyid}").
		then().statusCode(204);
    }
}
	