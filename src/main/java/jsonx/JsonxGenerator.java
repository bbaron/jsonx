package jsonx;

import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonGenerator;

class JsonxGenerator implements JsonGenerator {


    @SuppressWarnings("unused")
    private final Map<String, ?> config;
    private final com.fasterxml.jackson.core.JsonGenerator generator;
    private final Deque<Boolean> context = new ArrayDeque<>();
    private static boolean IN_OBJECT = true;
    private static boolean IN_ARRAY = false;

    public JsonxGenerator(Map<String, ?> config, com.fasterxml.jackson.core.JsonGenerator generator) {
        this.config = requireNonNull(config);
        this.generator = requireNonNull(generator);
    }

    @Override
    public JsonGenerator writeStartObject() {
        try {
            generator.writeStartObject();
            context.push(IN_OBJECT);
            return this;
        } catch (IOException e) {
            throw wrap("writeStartObject", e);
        }
    }

    @Override
    public JsonGenerator writeStartObject(String name) {
        try {
            generator.writeObjectFieldStart(name);
            context.push(IN_OBJECT);
            return this;
        } catch (IOException e) {
            throw wrap("writeStartObject", e, name);
        }
    }

    @Override
    public JsonGenerator writeStartArray() {
        try {
            generator.writeStartArray();
            context.push(IN_ARRAY);
            return this;
        } catch (IOException e) {
            throw wrap("writeStartArray", e);
        }
    }

    @Override
    public JsonGenerator writeStartArray(String name) {
        try {
            generator.writeArrayFieldStart(name);
            context.push(IN_ARRAY);
            return this;
        } catch (IOException e) {
            throw wrap("writeStartArray", e, name);
        }
    }

    @Override
    public JsonGenerator write(String name, JsonValue value) {
        return write(value, name);
    }

    private JsonGenerator write(JsonValue value, String name) {
        switch (value.getValueType()) {
        case ARRAY:
            if (name != null)
                writeStartArray(name);
            else
                writeStartArray();
            JsonArray array = value.asJsonArray();
            for (JsonValue elem : array) {
                write(elem);
            }
            writeEnd();
            break;

        case FALSE:
        case TRUE:

            boolean flag = JsonValues.toBoolean(value);
            if (name != null)
                write(name, flag);
            else
                write(flag);
            break;
        case NULL:
            if (name != null)
                writeNull(name);
            else
                writeNull();
            break;
        case NUMBER:
            JsonNumber jn = (JsonNumber) value;
            if (jn.isIntegral()) {
                BigInteger bigun = jn.bigIntegerValue();
                if (name != null)
                    write(name, bigun);
                else
                    write(bigun);
            } else {
                BigDecimal bigun = jn.bigDecimalValue();
                if (name != null)
                    write(name, bigun);
                else
                    write(bigun);
            }
            break;
        case OBJECT:
            if (name != null)
                writeStartObject(name);
            else
                writeStartObject();
            JsonObject obj = value.asJsonObject();
            for (Entry<String, JsonValue> e : obj.entrySet()) {
                write(e.getKey(), e.getValue());
            }
            writeEnd();
            break;
        case STRING:
            if (name != null)
                write(name, value.toString());
            else
                write(value.toString());
            break;
        default:
            throw new IllegalStateException(value.getValueType() + " is invalid");
        }
        return this;

    }

    @Override
    public JsonGenerator write(String name, String value) {
        try {
            generator.writeStringField(name, value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, name, value);
        }
    }

    @Override
    public JsonGenerator write(String name, BigInteger value) {
        try {
            generator.writeNumberField(name, new BigDecimal(value));
            return this;
        } catch (IOException e) {
            throw wrap("write", e, name, value);
        }
    }

    @Override
    public JsonGenerator write(String name, BigDecimal value) {
        try {
            generator.writeNumberField(name, value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, name, value);
        }
    }

    @Override
    public JsonGenerator write(String name, int value) {
        try {
            generator.writeNumberField(name, value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, name, value);
        }
    }

    @Override
    public JsonGenerator write(String name, long value) {
        try {
            generator.writeNumberField(name, value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, name, value);
        }
    }

    @Override
    public JsonGenerator write(String name, double value) {
        try {
            generator.writeNumberField(name, value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, name, value);
        }
    }

    @Override
    public JsonGenerator write(String name, boolean value) {
        try {
            generator.writeBooleanField(name, value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, name, value);
        }
    }

    @Override
    public JsonGenerator writeNull(String name) {
        try {
            generator.writeNullField(name);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, name);
        }
    }

    @Override
    public JsonGenerator writeEnd() {
        if (context.isEmpty()) {
            throw new JsonGenerationException("writeEnd: Not in object or array context");
        }
        try {
            if (context.pop()) {
                generator.writeEndObject();
            } else {                
                generator.writeEndArray();
            }
            return this;
        } catch (IOException e) {
            throw wrap("writeEnd", e);
        }
    }

    @Override
    public JsonGenerator write(JsonValue value) {
        return write(value, null);
    }

    @Override
    public JsonGenerator write(String value) {
        try {
            generator.writeString(value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, value);
        }
    }

    @Override
    public JsonGenerator write(BigInteger value) {
        try {
            generator.writeNumber(new BigDecimal(value));
            return this;
        } catch (IOException e) {
            throw wrap("write", e, value);
        }
    }

    @Override
    public JsonGenerator write(BigDecimal value) {
        try {
            generator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, value);
        }
    }

    @Override
    public JsonGenerator write(int value) {
        try {
            generator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, value);
        }
    }

    @Override
    public JsonGenerator write(long value) {
        try {
            generator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, value);
        }
    }

    @Override
    public JsonGenerator write(double value) {
        try {
            generator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, value);
        }
    }

    @Override
    public JsonGenerator write(boolean value) {
        try {
            generator.writeBoolean(value);
            return this;
        } catch (IOException e) {
            throw wrap("write", e, value);
        }
    }

    @Override
    public JsonGenerator writeNull() {
        try {
            generator.writeNull();
            return this;
        } catch (IOException e) {
            throw wrap("write", e);
        }
    }

    @Override
    public void close() {
        try {
            generator.close();
        } catch (IOException e) {
            throw wrap("close", e);
        }
    }

    @Override
    public void flush() {
        try {
            generator.flush();
        } catch (IOException e) {
            throw wrap("flush", e);
        }
    }

    private RuntimeException wrap(String name, IOException cause, Object... args) {
        String arguments = Stream.of(args).map(arg -> arg.toString()).collect(joining(", "));
        String msg = String.format("%s(%s)", name, arguments);
        RuntimeException re;
        if (cause instanceof com.fasterxml.jackson.core.JsonGenerationException) {
            re = new JsonGenerationException(msg, cause);
        } else {
            re = new JsonException(msg, cause);
        }
        return re;
    }

}
