package com.testingacademy;

import io.restassured.RestAssured;

public class Test002 {
    public static void main(String[] args) {
        System.out.println("Rest assured testcases");
        System.out.println("get request demo");
        //gherkins syntax

        RestAssured.given().baseUri("https://restful-booker.herokuapp.com").basePath("booking/1260")
                .when().get()
                .then().log().all()
                .statusCode(200);
    }
}
