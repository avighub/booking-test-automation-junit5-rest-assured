package org.techiewolf.booking.apiservice;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.techiewolf.booking.model.Booking;
import org.techiewolf.booking.specs.SpecificationFactory;

import static io.restassured.RestAssured.given;

@Slf4j
public final class BookingApi {
    private static final String BOOKING_ENDPOINT = "/booking";

    private BookingApi() {
    }

    public static Response createRandomBooking() {
        Booking booking = Booking.getInstance();
        return createBooking(booking);
    }

    public static Response createBooking(Booking booking) {

        Response response = given()
                .spec(SpecificationFactory.getBasicSpec())
                .when()
                .body(booking)
                .post(BOOKING_ENDPOINT)
                .then()
                .log()
                .ifError()
                .extract()
                .response();

        if (log.isDebugEnabled()) {
            response
                    .then()
                    .log().all();
        }
        return response;
    }

}
