package com.api.tests;

import com.api.base.AuthService;
import com.api.base.UserManagement;
import com.api.listeners.TestListeners;
import com.api.models.requests.LoginRequest;
import com.api.models.requests.SignupRequest;
import com.api.models.requests.UserManagementRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListeners.class)
public class LoginServiceTests {
    
    @Test(description = "To Verify login up service")
    public void loginTest() {
        LoginRequest request = new LoginRequest("pradheep_varatharajan", "Avengers.2023");
        AuthService auth = new AuthService();
        
        Response response = auth.login(request);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        Assert.assertEquals(loginResponse.getEmail(), "pradheep.varatharajan@gmail.com");
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    @Test(description = "To Verify sign up service")
    public void signUpTest() {
        // User Creation
        SignupRequest signupRequest = new SignupRequest.Builder().username("pradheep_test2")
                .password("Test1234!")
                .email("pradheep.test2@gmail.com")
                .firstName("Pradheep")
                .lastName("Test2")
                .mobileNumber("9090909090")
                .build();
        AuthService auth = new AuthService();
        Response response = auth.signUp(signupRequest);
        Assert.assertEquals(response.getStatusCode(), 200);
        
        // Login
        LoginRequest request = new LoginRequest(signupRequest.getUsername(), signupRequest.getPassword());
        response = auth.login(request);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        Assert.assertEquals(loginResponse.getEmail(), signupRequest.getEmail());
        Assert.assertEquals(response.getStatusCode(), 200);
        
        
        // Update Profile
        UserManagementRequest userManagementRequest = new UserManagementRequest.Builder().email(signupRequest.getEmail())
                .firstName(signupRequest.getFirstName())
                .lastName("Dev")
                .mobileNumber(signupRequest.getMobileNumber())
                .build();
        UserManagement user = new UserManagement();
        response = user.updateUserProfile(loginResponse.getToken(), userManagementRequest);
        System.out.println(response.asPrettyString());
        
        // Get Profile
        
        response = user.getUserProfile(loginResponse.getToken());
        System.out.println(response.asPrettyString());
    }

    
    @Test(description = "To Verify user profile")
    public void getProfile() {
        LoginRequest request = new LoginRequest("pradheep_varatharajan", "Avengers.2023");
        AuthService auth = new AuthService();
        
        Response response = auth.login(request);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        Assert.assertEquals(loginResponse.getEmail(), "pradheep.varatharajan@gmail.com");
        Assert.assertEquals(response.getStatusCode(), 200);
        
        // Get Profile
        UserManagement user = new UserManagement();
        response = user.getUserProfile(loginResponse.getToken());
        System.out.println(response.asPrettyString());
    }
    
}
