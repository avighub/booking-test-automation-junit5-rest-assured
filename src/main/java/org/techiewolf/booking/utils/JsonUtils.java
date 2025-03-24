package org.techiewolf.booking.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.github.victools.jsonschema.generator.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Iterator;


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

    public static String generateSchemaFromJsonString(String title, String description,
                                                      String json, JsonNodeType type) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        StringBuilder output = new StringBuilder();
        output.append("{");

        if (type == null) output.append(
                "\"title\": \"" +
                        title + "\", \"description\": \"" +
                        description + "\", \"type\": \"object\", \"properties\": {");

        for (Iterator<String> iterator = jsonNode.fieldNames(); iterator.hasNext(); ) {
            String fieldName = iterator.next();
            log.info("processing {}", fieldName + "...");

            JsonNodeType nodeType = jsonNode.get(fieldName).getNodeType();

            output.append(convertNodeToStringSchemaNode(jsonNode, nodeType, fieldName));
        }

        if (type == null) output.append("}");

        output.append("}");

        log.info("generated schema = {}", output.toString());
        return output.toString();
    }

    private static String convertNodeToStringSchemaNode(
            JsonNode jsonNode, JsonNodeType nodeType, String key) throws IOException {
        StringBuilder result = new StringBuilder("\"" + key + "\": { \"type\": \"");

        log.info("{} node type {} with value {}", key, nodeType, jsonNode.get(key));
        JsonNode node = null;
        switch (nodeType) {
            case ARRAY:
                node = jsonNode.get(key).get(0);
                log.info("{} is an array with value of {}", key, node.toString());
                result.append("array\", \"items\": { \"properties\":");
                result.append(generateSchemaFromJsonString(null, null, node.toString(), JsonNodeType.ARRAY));
                result.append("}},");
                break;
            case BOOLEAN:
                result.append("boolean\" },");
                break;
            case NUMBER:
                result.append("number\" },");
                break;
            case OBJECT:
                node = jsonNode.get(key);
                result.append("object\", \"properties\": ");
                result.append(generateSchemaFromJsonString(null, null, node.toString(), JsonNodeType.OBJECT));
                result.append("},");
                break;
            case STRING:
                result.append("string\" },");
                break;
        }

        return result.toString();
    }

}
