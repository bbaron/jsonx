package jsonx;

import java.math.BigDecimal;

import javax.json.stream.JsonLocation;
import javax.json.stream.JsonParser;

public class JsonxParser implements JsonParser {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Event next() {
        return null;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public boolean isIntegralNumber() {
        return false;
    }

    @Override
    public int getInt() {
        return 0;
    }

    @Override
    public long getLong() {
        return 0;
    }

    @Override
    public BigDecimal getBigDecimal() {
        return null;
    }

    @Override
    public JsonLocation getLocation() {
        return null;
    }

    @Override
    public void close() {
    }

}
