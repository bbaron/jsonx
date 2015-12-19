package jsonx;

import java.util.Collections;
import java.util.Map;

import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;

class JsonxBuilderFactory implements JsonBuilderFactory {

    private static final Map<String, Object> NO_OPTIONS = Collections.emptyMap();
    private final Map<String, ?> options;

    public JsonxBuilderFactory() {
        this.options = NO_OPTIONS;
    }

    JsonxBuilderFactory(Map<String, ?> options) {
        this.options = options == null ? NO_OPTIONS : options;
    }

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
        return options;
    }

}
