package com.api.base;

import com.api.models.requests.UserManagementRequest;
import io.restassured.response.Response;

public class UserManagement extends BaseService{
    private static final String  BASE_URL = "api/users/";
    
    public Response updateUserProfile(String token, UserManagementRequest payLoad) {
        return putRequest(token, payLoad, BASE_URL+"profile");
    }
    
    public Response getUserProfile(String token) {
        return getRequest(token, BASE_URL+"profile");
    }
}
