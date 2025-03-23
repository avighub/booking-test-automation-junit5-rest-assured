package org.techiewolf.booking.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class SpecificationFactory {
    private SpecificationFactory() {
    }

    public static RequestSpecification getBasicSpec() {
        return new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }
}
