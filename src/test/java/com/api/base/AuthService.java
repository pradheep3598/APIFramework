package com.api.base;

import com.api.models.requests.LoginRequest;
import io.restassured.response.Response;

public class AuthService extends BaseService {
    private static final String BASE_PATH = "/api/auth";
    
    public Response login(LoginRequest payLoad) {
        return postRequest(payLoad, BASE_PATH + "/login");
    }
}
