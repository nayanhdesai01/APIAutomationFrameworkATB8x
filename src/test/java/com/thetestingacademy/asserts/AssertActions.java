package com.thetestingacademy.asserts;

import io.restassured.response.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertEquals;

public class AssertActions {
    //Common assertions that can be reused
    public void verifyResponseBody(String actual, String expected, String description){
        assertEquals(actual,expected,description);
        //Code to verify
    }

    public void verifyResponseBody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
        //Code to verify
    }

    public void verifyStatusCode(Response response,Integer expected){
        assertEquals(response.getStatusCode(),expected);
        //Code to verify
    }

    public void verifyStringkey(String keyExpected, String keyActual){
        //assertJ

        assertThat(keyExpected).isNotNull();
        assertThat(keyExpected).isNotBlank().isNotEmpty().isNotNull();
        assertThat(keyExpected).isEqualTo(keyActual);
    }
}
