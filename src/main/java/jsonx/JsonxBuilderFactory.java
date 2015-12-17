package jsonx;

import java.util.Collections;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

class JsonxBuilderFactory implements JsonBuilderFactory {

    @Override
    public JsonObjectBuilder createObjectBuilder() {
        return new JsonxObjectBuilder();
    }

    @Override
    public JsonArrayBuilder createArrayBuilder() {
        return new JsonxArrayBuilder();
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return Collections.emptyMap();
    }

    @Override
    public JsonObjectBuilder createObjectBuilder(JsonObject object) {
        return new JsonxObjectBuilder(object);
    }

    @Override
    public JsonArrayBuilder createArrayBuilder(JsonArray array) {
        return new JsonxArrayBuilder(array);
    }

}
