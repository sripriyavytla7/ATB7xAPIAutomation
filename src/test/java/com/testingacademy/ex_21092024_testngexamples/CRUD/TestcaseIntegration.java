package com.testingacademy.ex_21092024_testngexamples.CRUD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class TestcaseIntegration {
    String token;
    Integer bookingid;
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    public String  getToken() {

        String payload = "{\n" +
                "                    \"username\" : \"admin\",\n" +
                "                    \"password\" : \"password123\"\n" +
                "                }";

        // Given - Request Spec
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

        // When -     Response
        Response response = requestSpecification.when().post();


        // Then - ValidatableResponse
        // Validation
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
        token = response.jsonPath().getString("token");
        return token;

    }
    public Integer getBookingID(){
        String payloadPOST= "{\n" +
                "    \"firstname\" : \"Pramod\",\n" +
                "    \"lastname\" : \"Dutta\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
            requestSpecification.basePath("/booking");
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.cookie("token",token);
            requestSpecification.body(payloadPOST).log().all();

            Response response = requestSpecification.when().post();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
        bookingid = response.jsonPath().getInt("bookingid");
        return bookingid;

    }
    @Test(priority = 1)
    public void test_update_requst_put(){
        token = getToken();
        bookingid =getBookingID();
        System.out.println(token);
        System.out.println(bookingid);
        String payloadPUT= "{\n" +
                "    \"firstname\" : \"sri\",\n" +
                "    \"lastname\" : \"v\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

            requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
            requestSpecification.basePath("/booking/"+bookingid);
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.cookie("token",token);
            requestSpecification.body(payloadPUT).log().all();

            Response response = requestSpecification.when().put();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(200);
        System.out.println(bookingid);


    }
    @Test(priority = 2)
    public void test_update_requst_get(){
        String expectedFirstname = "sri";
        String expectedLastname = "v";
        System.out.println(bookingid);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);

        Response response = requestSpecification.when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.body("firstname", Matchers.equalTo(expectedFirstname));
        validatableResponse.body("lastname", Matchers.equalTo(expectedLastname));

    }
    @Test(priority =3 )
    public void test_delete_booking(){
        System.out.println(bookingid);
        System.out.println(token);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        Response response = requestSpecification.when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }
    @Test(priority = 4)
    public void test_afet_delete_request_get(){
        System.out.println(bookingid);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);

        Response response = requestSpecification.when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

    }

}
