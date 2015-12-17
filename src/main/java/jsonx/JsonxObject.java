package jsonx;

import static java.util.Objects.*;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
class JsonxObject extends AbstractMap<String, JsonValue>implements JsonObject {

    private final Map<String, JsonValue> object;

    JsonxObject(LinkedHashMap<? extends String, ? extends JsonValue> m) {
        Map<String, JsonValue> obj = new LinkedHashMap<>(m);
        this.object = Collections.unmodifiableMap(obj);
    }

    @Override
    public ValueType getValueType() {
        return ValueType.OBJECT;
    }

    @Override
    public int size() {
        return object.size();
    }

    @Override
    public boolean isEmpty() {
        return object.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return object.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return object.containsValue(value);
    }

    @Override
    public JsonValue get(Object key) {
        return object.get(key);
    }

    JsonValue getRequired(Object key) {
        return getRequired(key, JsonValue.class);
    }

    <T extends JsonValue> T getRequired(Object key, Class<T> c) {
        return c.cast(requireNonNull(get(key), "'" + key + "' is not a valid property key"));
    }

    @Override
    public Set<Entry<String, JsonValue>> entrySet() {
        return object.entrySet();
    }

    @Override
    public JsonArray getJsonArray(String name) {
        return (JsonArray) get(name);
    }

    @Override
    public JsonObject getJsonObject(String name) {
        return (JsonObject) get(name);
    }

    @Override
    public JsonNumber getJsonNumber(String name) {
        return (JsonNumber) get(name);
    }

    @Override
    public JsonString getJsonString(String name) {
        return (JsonString) get(name);
    }

    @Override
    public String getString(String name) {
        return getRequired(name, JsonString.class).getString();
    }

    @Override
    public String getString(String name, String defaultValue) {
        if (!containsKey(name)) {
            return defaultValue;
        }
        return getString(name);
    }

    @Override
    public int getInt(String name) {
        return getRequired(name, JsonNumber.class).intValue();
    }

    @Override
    public int getInt(String name, int defaultValue) {
        if (!containsKey(name)) {
            return defaultValue;
        }
        return getInt(name);
    }

    @Override
    public boolean getBoolean(String name) {
        JsonValue value = getRequired(name);
        return JsonValues.toBoolean(value);
    }

    @Override
    public boolean getBoolean(String name, boolean defaultValue) {
        JsonValue value = getRequired(name);
        if (JsonValues.isBoolean(value)) {
            return JsonValues.toBoolean(value);
        }
        return defaultValue;
    }

    @Override
    public boolean isNull(String name) {
        return get(name) == JsonValue.NULL;
    }

}
