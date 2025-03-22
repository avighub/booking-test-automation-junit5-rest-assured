package org.techiewolf.booking.apiservice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.techiewolf.booking.specs.SpecificationFactory;
import org.techiewolf.booking.model.Book;

public final class BookApi {
    private BookApi() {
    }

    private static final String BOOKING_ENDPOINT = "/book";

    public static Response addBook(Book book) {
        return RestAssured.given()
                .spec(SpecificationFactory.getBasicSpec())
                .body(book)
                .log().all()
                .when()
                .put(BOOKING_ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response getBook(int bookId) {
        return RestAssured.given()
                .spec(SpecificationFactory.getBasicSpec())
                .log().all()
                .when()
                .get(BOOKING_ENDPOINT + bookId)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response deleteAllBooks() {
        return RestAssured.given()
                .spec(SpecificationFactory.getBasicSpec())
                .log().all()
                .when()
                .delete(BOOKING_ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
