package unittests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.techiewolf.booking.model.Booking;
import org.techiewolf.booking.utils.JsonUtils;

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

}
