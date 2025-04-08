package com.thetestingacademy.tests.integration.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojo.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class testVerifyCreateBookingPOST01 extends BaseTest {

//    Additional Info
    @Owner("Nayan")
    @Link(name="Link to TC",url="dummyurl")
    @Issue("JIRA_Dummy")
    @Description("Verify that POST request is working fine.")
    @Test
    public void test_VerifyCreateBookingPOST01(){

requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

response= RestAssured.given(requestSpecification)
        .when().body(payloadManager.createPayloadBookingAsString()).post();

validatableResponse = response.then().log().all();
validatableResponse.statusCode(200);


//Default Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo("Hema"));

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        //AssertJ
        //assertActions.verifyStringkey(bookingResponse.getBooking().getFirstname(),"Hema");
        assertThat(bookingResponse.getBookingid()).isPositive().isNotZero().isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Hema");
        assertThat(bookingResponse.getBooking().getLastname()).isEqualTo("Tiwari");

        //TestNG assertions
        assertActions.verifyStatusCode(response,200);
        assertActions.verifyResponseBody(bookingResponse.getBooking().getFirstname(),"Hema","Verify first name");
        Assert.assertEquals(bookingResponse.getBooking().getFirstname(),"Hema");
        Assert.assertEquals(bookingResponse.getBooking().getLastname(),"Tiwari");
    }
    @Test
    public void test_VerifyCreateBookingPOST02(){}
    @Test
    public void test_VerifyCreateBookingPOST03(){}
    @Test
    public void test_VerifyCreateBookingPOST04(){}
    @Test
    public void test_VerifyCreateBookingPOST05(){}
}

