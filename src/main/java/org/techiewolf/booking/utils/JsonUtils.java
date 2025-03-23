package org.techiewolf.booking.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.victools.jsonschema.generator.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class JsonUtils {
    private JsonUtils() {
    }

    @SneakyThrows
    public static String pojoToString(Object pojo) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String prettyJson = objectMapper.writeValueAsString(pojo);
        log.info("Booking JSON: {}", prettyJson);

        return prettyJson;
    }

    public static String generateSchemaFromPojo(Class<?> clazz) {
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(
                SchemaVersion.DRAFT_7, OptionPreset.PLAIN_JSON
        );
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator schemaGenerator = new SchemaGenerator(config);
        JsonNode jsonSchema = schemaGenerator.generateSchema(clazz);
        log.info("Generated schema for class: {}, \nSchema: {}", clazz, jsonSchema.toPrettyString());

        return jsonSchema.toPrettyString();
    }

}
