package jsonx;

import java.util.Map;

import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;

class JsonxBuilderFactory implements JsonBuilderFactory {

    @Override
    public JsonObjectBuilder createObjectBuilder() {
        return null;
    }

    @Override
    public JsonArrayBuilder createArrayBuilder() {
        return null;
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return null;
    }

}
