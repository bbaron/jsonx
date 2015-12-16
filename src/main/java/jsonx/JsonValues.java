package jsonx;

import javax.json.JsonValue;

import lombok.experimental.UtilityClass;

@UtilityClass
class JsonValues {

    JsonValue toJsonBoolean(boolean value) {
        return value ? JsonValue.TRUE : JsonValue.FALSE;
    }
    
    boolean isTrue(JsonValue v) {
        return v != null && v == JsonValue.TRUE;
    }
    boolean isFalse(JsonValue v) {
        return v != null &&  v == JsonValue.FALSE;
    }
    
    boolean toBoolean(JsonValue v) {
        if (isTrue(v)) return true;
        if (isFalse(v)) return false;
        throw new ClassCastException(v + " is not a boolean JsonValue");
    }
}
