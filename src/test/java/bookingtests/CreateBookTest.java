package bookingtests;

import assertion.VerifyResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.techiewolf.booking.apiservice.BookingApi;
import org.techiewolf.booking.model.Booking;

class CreateBookTest {

    @Test
    void shouldBeAbleToCreateNewBooking() {
        // Arrange
        Booking booking = Booking.getInstance();

        // Act
        Response createBooking = BookingApi.createBooking(booking);

        // Assert
        VerifyResponse.assertThat(createBooking)
                .matchStatusCode(200)
                .matchesSchema("json-schemas/create-booking-response-schema.json")
                .assertAll();
    }


}
