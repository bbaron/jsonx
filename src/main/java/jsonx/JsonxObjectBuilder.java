package jsonx;

import static java.util.Objects.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

class JsonxObjectBuilder implements JsonObjectBuilder {

    private LinkedHashMap<String, JsonValue> object = new LinkedHashMap<>();

    @Override
    public JsonObjectBuilder add(String name, JsonValue value) {
        requireNonNull(name, "name is required");
        requireNonNull(value, "value is required");
        object.put(name, value);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, String value) {
        requireNonNull(name, "name is required");
        requireNonNull(value, "value is required");
        object.put(name, new JsonxString(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, BigInteger value) {
        requireNonNull(name, "name is required");
        requireNonNull(value, "value is required");
        object.put(name, new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, BigDecimal value) {
        requireNonNull(name, "name is required");
        requireNonNull(value, "value is required");
        object.put(name, new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, int value) {
        requireNonNull(name, "name is required");
        object.put(name, new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, long value) {
        requireNonNull(name, "name is required");
        object.put(name, new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, double value) {
        requireNonNull(name, "name is required");
        object.put(name, new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, boolean value) {
        requireNonNull(name, "name is required");
        object.put(name, JsonValues.toJsonBoolean(value));
        return this;
    }

    @Override
    public JsonObjectBuilder addNull(String name) {
        requireNonNull(name, "name is required");
        object.put(name, JsonValue.NULL);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, JsonObjectBuilder builder) {
        requireNonNull(name, "name is required");
        requireNonNull(builder, "builder is required");
        object.put(name, builder.build());
        return this;
    }

    @Override
    public JsonObjectBuilder add(String name, JsonArrayBuilder builder) {
        requireNonNull(name, "name is required");
        requireNonNull(builder, "value is builder");
        return this;
    }

    @Override
    public JsonObject build() {
        return new JsonxObject(object);
    }

 
}
