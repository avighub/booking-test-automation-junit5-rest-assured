package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BookApi {
  public static Response addBook(Book book){
    return RestAssured.given()
            .spec(SpecificationFactory.getBasicSpec())
            .body(book)
            .log().all()
            .when()
            .put("/book")
            .then()
            .log().all()
            .extract()
            .response();
  }

  public static Response getBook(int bookId){
    return RestAssured.given()
            .spec(SpecificationFactory.getBasicSpec())
            .log().all()
            .when()
            .get("/book"+bookId)
            .then()
            .log().all()
            .extract()
            .response();
  }

  public static Response deleteAllBooks(){
    return RestAssured.given()
            .spec(SpecificationFactory.getBasicSpec())
            .log().all()
            .when()
            .delete("/book")
            .then()
            .log().all()
            .extract()
            .response();
  }
}
