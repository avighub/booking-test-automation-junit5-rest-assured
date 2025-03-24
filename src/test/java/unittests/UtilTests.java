package unittests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.techiewolf.booking.model.Booking;
import org.techiewolf.booking.utils.JsonUtils;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Slf4j
public class UtilTests {

    @Test
    void checkSchemaGenerationFromPojo() {
        // Act
        String jsonSchemaString = JsonUtils.generateSchemaFromPojo(Booking.class);

        // Assert
        assertThat(jsonSchemaString).isNotNull();
    }

    @Test
    void checkSchemaGenerationFromString() throws IOException {
        // Arrange
        String booking = JsonUtils.pojoToString(Booking.getInstance());

        // Act
        String jsonSchemaString = JsonUtils.generateSchemaFromJsonString("Booking Schema", "Booking Schema", booking, null);

        // Assert
        assertThat(jsonSchemaString).isNotNull();
    }


}
