package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecificationFactory {

  public static RequestSpecification getBasicSpec(){
    return new RequestSpecBuilder()
            .addHeader("Content-Type","application/json")
            .setBaseUri("http://localhost:3000")
            .build();
  }
}
