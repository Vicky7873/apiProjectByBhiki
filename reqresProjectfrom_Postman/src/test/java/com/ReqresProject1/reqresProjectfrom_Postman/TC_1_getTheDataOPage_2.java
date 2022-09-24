package com.ReqresProject1.reqresProjectfrom_Postman;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC_1_getTheDataOPage_2 {
	String id="2";
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://reqres.in";
		RestAssured.basePath="/api/users";
	}
	
	@Test(priority=1)
	public void setTheParameter() {
		Response res=given().queryParam("page",id)
				.when()
				.get();
		System.out.println(res.getBody().prettyPrint());
	}
	
	@Test(priority=2)
	public void getTheStatusAndprintIt() {
		Response res=given()
				.queryParam("page", id)
				.when()
				.get();
		res.prettyPrint();
		int actual=res.getStatusCode();
		System.out.println("my status code is : "+actual);
		int expected=200;
		Assert.assertEquals(actual, expected,"the status code didn't matched");
	}
	
	@Test(priority = 3)
	public void getThetime_andThatShouldBelow2000() {
		Response res=given()
				.queryParam("page",id)
				.when()
				.get();
		res.prettyPrint();
		System.out.println("the response time of the request is :- "+res.getTime());
		int expected=2000;
		long actual=res.getTime();
		if(actual <= expected) {
			System.out.println("Time is below the expected ");
		}
		else{
			System.out.println("time got failed");
		}
	}
	
	@Test(priority = 4)
	public void Validate_the_Header() {
		Response res=given()
				.queryParam("page",id)
				.when()
				.get().then()
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all()
				.extract().response();
		System.out.println(res.getContentType());
		
	}
	
	@Test(priority = 5)
	public void getTheOneOfTheStatusCode() {
		Response res=given()
				.queryParam("page", id)
				.when().get();
		int[] a= {200,201};
		Integer temp=null;
		int actual=res.getStatusCode();   
		Assert.assertTrue(Status.SUCCESS.matches(actual));
	}
	
	@Test(priority = 6)
	public void validateThePage2(){
		Response res=given()
				.queryParam("page", id)
				.when().get().then().body("page", equalTo(2))
				.log().all()
				.extract().response();
		System.out.println("page value :- "+res.path("page"));
	}
	
	@Test(priority = 7)
	public void validateThePer_page() {
		Response res=given()
				.queryParam("page",id)
				.when()
				.get()
				.then()
				.body("per_page",equalTo(6))
				.and().log().all()
				.extract().response();
		System.out.println("value of per_page :- "+res.path("per_page"));
	}
	
	@Test(priority = 8)
	public void validate_the_total() {
		Response res=given()
				.queryParam("page", id)
				.when()
				.get()
				.then()
				.body("total",equalTo(12))
				.log().all()
				.extract().response();
		System.out.println("the value of the total is :- "+res.path("total"));
	}
	
	@Test(priority = 9)
	public void VaidateThetotal_pages() {
		Response res=given()
				.queryParam("page",id)
				.when().get()
				.then()
				.body("total_pages",equalTo(2))
				.log().all()
				.extract().response();
		System.out.println("value of the total_pages :- "+res.path("total_pages"));
	}
	
	@Test(priority = 10)
	public void matchesTheValueOftotal_pages() {
		Response res=given()
				.queryParam("page",id)
				.when().get();
		System.out.println(res.asPrettyString());
		int expected=2;
		int actual=res.path("total_pages");
		System.out.println("value of the actual :- "+actual);
		Assert.assertEquals(actual,expected,"expected value didn't matched");
		if(actual==expected) {
			System.out.println("value matched");
		}
		else {
			System.out.println("value didn't matched");
		}
	}
	
	@Test(priority = 11)
	public void validate_the_1st_data_and_its_id() {
		Response res=given()
				.queryParam("page",id)
				.when().get()
				.then()
				.body("data[0].id", equalTo(7))
				.log().all()
				.extract().response();
		int actual=7;
		int expected=res.path("data[0].id");
		System.out.println("value of the id is :- "+expected);
		if(actual==expected) {
			System.out.println("value matched");
		}
		else {
			System.out.println("value didn't matched");
		}
	}

}
