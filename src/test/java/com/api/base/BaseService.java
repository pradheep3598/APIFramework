package com.api.base;

import com.api.filters.LoggingFilter;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseService {
    private static final String BASE_URL = "http://64.227.160.186:8080";
    
    private RequestSpecification requestSpecification;
    
    public BaseService() {
        requestSpecification = given().baseUri(BASE_URL);
    }
    
    static {
        RestAssured.filters(new LoggingFilter());
    }
    
    private RequestSpecification setAuthToken(String token) {
        requestSpecification.header("Authorization", "Bearer "+token);
        return requestSpecification;
    }
    protected Response postRequest(Object payLoad, String endPoint) {
        return requestSpecification
                .contentType(ContentType.JSON)
                .body(payLoad)
                .post(endPoint);
    }
    
    protected Response putRequest(String token, Object payLoad, String endPoint) {
        return setAuthToken(token)
                .contentType(ContentType.JSON)
                .body(payLoad)
                .put(endPoint);
    }
    
    protected Response getRequest(String token, String endPoint) {
        return setAuthToken(token)
                .get(endPoint);
    }
    
    
}
