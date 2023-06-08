package com.example.sandbox;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Common extends Endpoints {

    //----------------------------------GET----------------------------------
    public Response getUrl(String endpoint) {


        return given()
                .relaxedHTTPSValidation()
                .and()
                .log().everything()
                .when()
                .get(baseUrl + endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }

    public Response getUrl(String endpoint, Map<String, String> queryParam) {


        return given()
                .relaxedHTTPSValidation()
                .params(queryParam)
                .and()
                .log().everything()
                .when()
                .get(baseUrl + endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }

    public Response getUrl(String endpoint, Map<String, String> headers, Map<String, String> queryParam) {


        return given()
                .relaxedHTTPSValidation()
                .params(queryParam)
                .headers(headers)
                .and()
                .log().all()
                .when()
                .get(baseUrl + endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }

    //----------------------------------POST----------------------------------
    public Response postUrl(String endpoint, String body) {


        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .body(body)
                .and()
                .log().everything()
                .when()
                .post(baseUrl + endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }

    // TODO: 08/06/2023 make signature less specific
    public Response postUrlForm(String endpoint, String k1, String v1, String k2, String v2, boolean multipart) {
        RequestSpecification requestSpecification = given()
                .relaxedHTTPSValidation();

        requestSpecification = multipart ?
                requestSpecification
                        .contentType("multipart/form-data; charset=UTF-8")
                        .multiPart(k1, v1)
                        .multiPart(k2, v2)
                :
                requestSpecification
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .formParams(k1, v1, k2, v2);

        return requestSpecification
                .and()
                .log().everything()
                .when()
                .post(baseUrl + endpoint)
                .then()
                .log()
                .all()
                .extract().response();
    }

    //----------------------------------PUT----------------------------------
    public Response putUrl(String endpoint, String body) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .body(body)
                .and()
                .log().everything()
                .when()
                .put(baseUrl + endpoint)
                .then()
                .log()
                .all()
                .extract().response();
    }

    //----------------------------------DELETE----------------------------------
    public Response deleteUrl(String endpoint) {
        return given()
                .relaxedHTTPSValidation()
                .and()
                .log().everything()
                .when()
                .delete(baseUrl + endpoint)
                .then()
                .log()
                .all()
                .extract().response();
    }
}

