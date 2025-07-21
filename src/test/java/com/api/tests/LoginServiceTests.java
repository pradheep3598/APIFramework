package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.base.AuthService;
import com.api.listeners.TestListeners;
import com.api.models.requests.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListeners.class)
public class LoginServiceTests {
    
    @Test
    public void loginTest2() {
        LoginRequest request = new LoginRequest("pradheep_varatharajan", "Avengers.2023");
        AuthService auth = new AuthService();
        
        Response response = auth.login(request);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        Assert.assertEquals(loginResponse.getEmail(), "pradheep.varatharajan@gmail.com");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
