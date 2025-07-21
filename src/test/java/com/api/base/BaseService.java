package com.api.base;

import com.api.filters.LoggingFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseService {
    private static final String BASE_URL = "http://64.227.160.186:8080";
    
    static {
        RestAssured.filters(new LoggingFilter());
    }
    
    protected Response postRequest(Object payLoad, String endPoint) {
        return given().baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(payLoad)
                .post(endPoint);
    }
    
}
