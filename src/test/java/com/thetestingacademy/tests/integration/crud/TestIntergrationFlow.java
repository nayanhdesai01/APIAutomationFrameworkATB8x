package com.thetestingacademy.tests.integration.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojo.Booking;
import com.thetestingacademy.pojo.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestIntergrationFlow extends BaseTest {
    // Create A Booking, Create a Token
    // Get booking
    // Update the Booking
    // Delete the Booking

    @Test(groups = "integration", priority = 1)
    @Owner("Promode")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response= RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Extract booking ID
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());

    }
    @Test(groups = "integration", priority = 2)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL +"/"+ bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured.given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Extract booking response
        Booking booking = payloadManager.getResponseFromJson(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Hema");

    }

    @Test(groups = "integration", priority = 3)
    @Owner("Promode")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){
        String token = getToken();
        iTestContext.setAttribute("token",token);
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathPUT = APIConstants.CREATE_UPDATE_BOOKING_URL+ "/" +bookingid;
        System.out.println(basePathPUT);
        requestSpecification.basePath(basePathPUT);
        response=RestAssured.given(requestSpecification)
                .cookie("token",token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJson(response.asString());
        assertThat(booking.getFirstname()).isNotBlank().isNotNull();
        assertThat(booking.getFirstname()).isEqualTo("Jatin");
        assertThat(booking.getLastname()).isNotNull().isNotBlank();
        assertThat(booking.getLastname()).isEqualTo("Verma");
    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL+ "/" +bookingid;
        requestSpecification.basePath(basePathDELETE).cookie("token",token);

        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }
}
