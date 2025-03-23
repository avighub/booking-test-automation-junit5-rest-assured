package org.techiewolf.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.techiewolf.booking.utils.DateUtil;
import org.techiewolf.booking.utils.JsonUtils;

@Slf4j
@Data
@Builder(setterPrefix = "set")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Accessors(chain = true)
public class Booking {
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("totalprice")
    private Long totalPrice;
    @JsonProperty("depositpaid")
    private Boolean depositPaid;
    @JsonProperty("bookingdates")
    private BookingDates bookingDates;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    @SneakyThrows
    public static Booking getInstance() {
        BookingDates bookingDates = BookingDates.builder()
                .setCheckin(DateUtil.getTodaysDateAsString("YYYY-mm-dd"))
                .setCheckout(DateUtil.getTodaysDateAsString("YYYY-mm-dd"))
                .build();
        Booking booking = Booking.builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setTotalPrice(1000L)
                .setDepositPaid(true)
                .setAdditionalNeeds("Breakfast")
                .setBookingDates(bookingDates)
                .build();

        JsonUtils.pojoToString(booking);

        return booking;
    }
}
