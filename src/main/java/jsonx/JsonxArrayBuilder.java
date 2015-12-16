package jsonx;

import static java.util.Objects.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

class JsonxArrayBuilder implements JsonArrayBuilder {

    private List<JsonValue> array = new ArrayList<>();

    @Override
    public JsonxArrayBuilder add(JsonValue value) {
        requireNonNull(value);
        array.add(value);
        return this;
    }

    @Override
    public JsonxArrayBuilder add(String value) {
        requireNonNull(value);
        array.add(new JsonxString(value));
        return this;
    }

    @Override
    public JsonxArrayBuilder add(BigDecimal value) {
        requireNonNull(value);
        array.add(new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonxArrayBuilder add(BigInteger value) {
        requireNonNull(value);
        array.add(new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonxArrayBuilder add(int value) {
        requireNonNull(value);
        array.add(new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonxArrayBuilder add(long value) {
        array.add(new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonxArrayBuilder add(double value) {
        array.add(new JsonxNumber(value));
        return this;
    }

    @Override
    public JsonxArrayBuilder add(boolean value) {
        array.add(JsonValues.toJsonBoolean(value));
        return this;
    }

    @Override
    public JsonxArrayBuilder addNull() {
        array.add(JsonValue.NULL);
        return this;
    }

    @Override
    public JsonxArrayBuilder add(JsonObjectBuilder builder) {
        requireNonNull(builder);
        array.add(builder.build());
        return this;
    }

    @Override
    public JsonxArrayBuilder add(JsonArrayBuilder builder) {
        requireNonNull(builder);
        array.add(builder.build());
        return this;
    }

    @Override
    public JsonxArray build() {
        return new JsonxArray(array);
    }

 }
