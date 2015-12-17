package jsonx;

import static java.util.Collections.*;
import static java.util.Objects.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

class JsonxArray extends AbstractList<JsonValue>implements JsonArray {

    private final List<JsonValue> array;

    public JsonxArray(List<JsonValue> array) {
        requireNonNull(array, "array is required");
        this.array = unmodifiableList(new ArrayList<>(array));
    }

    @Override
    public ValueType getValueType() {
        return ValueType.ARRAY;
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return array.contains(o);
    }

    @Override
    public JsonValue get(int index) {
        return array.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return array.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return array.lastIndexOf(o);
    }

    @Override
    public ListIterator<JsonValue> listIterator() {
        return array.listIterator();
    }

    @Override
    public ListIterator<JsonValue> listIterator(int index) {
        return array.listIterator(index);
    }

    @Override
    public List<JsonValue> subList(int fromIndex, int toIndex) {
        return array.subList(fromIndex, toIndex);
    }

    @Override
    public JsonObject getJsonObject(int index) {
        return null;
    }

    @Override
    public JsonArray getJsonArray(int index) {
        return null;
    }

    @Override
    public JsonNumber getJsonNumber(int index) {
        return null;
    }

    @Override
    public JsonString getJsonString(int index) {
        return null;
    }

    @Override
    public <T extends JsonValue> List<T> getValuesAs(Class<T> clazz) {
        return null;
    }

    @Override
    public String getString(int index) {
        return getJsonString(index).getString();
    }

    @Override
    public String getString(int index, String defaultValue) {
        return null;
    }

    @Override
    public int getInt(int index) {
        return getJsonNumber(index).intValue();
    }

    @Override
    public int getInt(int index, int defaultValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(int index) {
        JsonValue value = getRequired(index);
        return JsonValues.toBoolean(value);
    }

    @Override
    public boolean getBoolean(int index, boolean defaultValue) {
        JsonValue value = getRequired(index);
        if (JsonValues.isBoolean(value)) {
            return JsonValues.toBoolean(value);
        }
        return defaultValue;
    }

    @Override
    public boolean isNull(int index) {
        return get(index) == JsonValue.NULL;
    }

    JsonValue getRequired(int index) {
        return getRequired(index, JsonValue.class);
    }

    <T extends JsonValue> T getRequired(int index, Class<T> c) {
        return c.cast(get(requireInbounds(index)));
    }
    
    private int requireInbounds(int index) {
        if (index < 0 || array.size() <= index) {
            throw new ArrayIndexOutOfBoundsException(index + " is not a valid array index");
        }
        return index;
    }

}
