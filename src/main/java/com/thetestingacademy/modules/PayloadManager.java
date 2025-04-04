package com.thetestingacademy.modules;

import com.google.gson.Gson;
import com.thetestingacademy.pojo.Booking;
import com.thetestingacademy.pojo.BookingResponse;
import com.thetestingacademy.pojo.Bookingdates;
import com.thetestingacademy.pojo.Auth;
import  com.thetestingacademy.pojo.TokenResponse;

public class PayloadManager {

    //Converting the Java Objects to String
    //GSON
    Gson gson;
    //Serialisation
    public String createPayloadBookingAsString(){

        Booking booking = new Booking();
        booking.setFirstname("Hema");
        booking.setLastname("Tiwari");
        booking.setTotalprice(2500);
        booking.setDepositpaid(true);

        Bookingdates bookingDates = new Bookingdates();
        bookingDates.setCheckin("2025-05-10");
        bookingDates.setCheckout("2025-05-15");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        gson = new Gson();
        String jsonStringPayload=gson.toJson(booking);
        System.out.println(jsonStringPayload);

        return jsonStringPayload;
    }

    //De Serialisation
    public BookingResponse bookingResponseJava(String responseString){
        gson  = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    public Booking getResponseFromJson(String getResponse){
        Booking booking = gson.fromJson(getResponse,Booking.class);
        return booking;
    }

    public String setAuthPayload(){
//Auth Object to Json String
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson=new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload is set to ->"+ jsonPayloadString);
        return jsonPayloadString;

    }

    public  String getTokenFromJson(String tokenResponse){
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse,TokenResponse.class);
        return tokenResponse1.getToken();
    }
    //    public String createPayloadBookingAsStringFromExcel(){}
}
