package jsonx;

import static jsonx.JsonxGenerator.Context.*;

import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;

import javax.json.JsonValue;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonGenerator;

class JsonxGenerator implements JsonGenerator {

    enum Context {
        INIT, ARRAY, OBJECT;
    }

    private final JsonBufferedWriter writer;
    private final Deque<Context> ctx;
    private int elemCount = 0;

    JsonxGenerator(Writer writer) {
        ctx = new ArrayDeque<Context>();
        ctx.push(INIT);
        this.writer = new JsonBufferedWriter(writer);
    }

    @Override
    public JsonGenerator writeStartObject() {
        if (checkContexts(INIT, ARRAY)) {
            throw new JsonGenerationException("Cannot start anonymous object in " + ctx + " context");
        }
        writer.append('{');
        elemCount = 0;
        ctx.push(OBJECT);
        return this;
    }

    private boolean checkContexts(Context c, Context... rest) {
        if (ctx.isEmpty()) {
            return false;
        }
        EnumSet<Context> set = EnumSet.of(c, rest);
        boolean val = set.contains(ctx.peek());
        if (val && ctx.peek() == INIT) {
            ctx.pop();
        }
        return val;
    }

    private String qq(String s) {
        return '"' + s + '"';
    }

    @Override
    public JsonGenerator writeStartObject(String name) {
        if (checkContexts(INIT, OBJECT)) {
            String s = String.format("%s : {", qq(name));
            writer.append(s);
            ctx.push(OBJECT);
            elemCount = 0;
            return this;
        }
        throw new JsonGenerationException("Cannot start anonymous object in " + ctx + " context");
    }

    @Override
    public JsonGenerator writeStartArray() {
        if (checkContexts(INIT, ARRAY)) {
            writer.append('[');
            elemCount = 0;
            return this;
        }
        throw new JsonGenerationException("Cannot start anonymous array");
    }

    @Override
    public JsonGenerator writeStartArray(String name) {
        if (checkContexts(INIT, OBJECT)) {
            String s = String.format("%s : [", qq(name));
            writer.append(s);
            elemCount = 0;
            return this;
        }
        throw new JsonGenerationException("Cannot start array in " + ctx + " context");
    }

    @Override
    public JsonGenerator write(String name, JsonValue value) {
        if (!checkContexts(OBJECT)) {
            writeProp(name, value);
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    private void writeProp(String name, Object value) {
        String s;
        if (elemCount == 0) {
            s = String.format("%s : %s", qq(name), value);
        } else {
            s = String.format(", %s : %s", qq(name), value);
        }
        writer.append(s);
        elemCount++;
    }

    private void writeProp(Object value) {
        String s;
        if (elemCount == 0) {
            s = String.format("%s", value);
        } else {
            s = String.format("%s", value);
        }
        writer.append(s);
        elemCount++;
    }

    @Override
    public JsonGenerator write(String name, String value) {
        if (checkContexts(OBJECT)) {
            writeProp(name, qq(value));
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    @Override
    public JsonGenerator write(String name, BigInteger value) {
        if (checkContexts(OBJECT)) {
            writeProp(name, value);
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    @Override
    public JsonGenerator write(String name, BigDecimal value) {
        if (checkContexts(OBJECT)) {
            writeProp(name, value);
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    @Override
    public JsonGenerator write(String name, int value) {
        if (checkContexts(OBJECT)) {
            writeProp(name, value);
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    @Override
    public JsonGenerator write(String name, long value) {
        if (checkContexts(OBJECT)) {
            writeProp(name, value);
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    @Override
    public JsonGenerator write(String name, double value) {
        if (checkContexts(OBJECT)) {
            writeProp(name, value);
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    @Override
    public JsonGenerator write(String name, boolean value) {
        if (checkContexts(OBJECT)) {
            writeProp(name, value);
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    @Override
    public JsonGenerator writeNull(String name) {
        if (checkContexts(OBJECT)) {
            writeProp(name, "null");
            return this;
        }
        throw new JsonGenerationException("Must be in object context");
    }

    @Override
    public JsonGenerator writeEnd() {
        if (checkContexts(ARRAY, OBJECT)) {
            throw new JsonGenerationException("Cannot end object or array here");
        }
        if (ctx.peek() == OBJECT) {
            writer.append('}');
        } else {
            writer.append(']');
        }
        ctx.pop();
        return this;
    }

    @Override
    public JsonGenerator write(JsonValue value) {
        return this;
    }

    @Override
    public JsonGenerator write(String value) {
        return this;
    }

    @Override
    public JsonGenerator write(BigDecimal value) {
        return this;
    }

    @Override
    public JsonGenerator write(BigInteger value) {
        return this;
    }

    @Override
    public JsonGenerator write(int value) {
        return this;
    }

    @Override
    public JsonGenerator write(long value) {
        return this;
    }

    @Override
    public JsonGenerator write(double value) {
        return this;
    }

    @Override
    public JsonGenerator write(boolean value) {
        return this;
    }

    @Override
    public JsonGenerator writeNull() {
        return this;
    }

    @Override
    public void close() {
        writer.close();
    }

    @Override
    public void flush() {
        writer.flush();
    }

}
