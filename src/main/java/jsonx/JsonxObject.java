package jsonx;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
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

@EqualsAndHashCode
@ToString
class JsonxObject implements JsonObject {

    private final class EntrySetIterator implements Iterator<Entry<String, JsonValue>> {
        final Iterator<Entry<String, JsonValue>> iter = object.entrySet().iterator();

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public Entry<String, JsonValue> next() {
            return iter.next();
        }
    }
    
    private final class EntrySet extends AbstractSet<Entry<String, JsonValue>> {

        @Override
        public Iterator<Entry<String, JsonValue>> iterator() {
            return new EntrySetIterator();
        }

        @Override
        public int size() {
            return object.size();
        }
    }

    private final LinkedHashMap<String, JsonValue> object;

    JsonxObject(LinkedHashMap<? extends String, ? extends JsonValue> m) {
        this.object = new LinkedHashMap<>(m);
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

    @Override
    public JsonValue put(String key, JsonValue value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonValue remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends JsonValue> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        final Iterator<Entry<String, JsonValue>> iter = entrySet().iterator();
        return new AbstractSet<String>() {

            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    @Override
                    public boolean hasNext() {
                        return iter.hasNext();
                    }

                    @Override
                    public String next() {
                        return iter.next().getKey();
                    }
                };
            }

            @Override
            public int size() {
                return object.size();
            }
        };
    }

    @Override
    public Collection<JsonValue> values() {
        final Iterator<Entry<String, JsonValue>> iter = entrySet().iterator();
        return new AbstractCollection<JsonValue>() {

            @Override
            public Iterator<JsonValue> iterator() {
                return new Iterator<JsonValue>() {

                    @Override
                    public boolean hasNext() {
                        return iter.hasNext();
                    }

                    @Override
                    public JsonValue next() {
                        return iter.next().getValue();
                    }
                };
            }

            @Override
            public int size() {
                return object.size();
            }
        };
    }

    @Override
    public Set<Entry<String, JsonValue>> entrySet() {
        return new EntrySet();
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
        return getJsonString(name).getString();
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
        return getJsonNumber(name).intValue();
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
        return false;
    }

    @Override
    public boolean getBoolean(String name, boolean defaultValue) {
        return false;
    }

    @Override
    public boolean isNull(String name) {
        return false;
    }

}
