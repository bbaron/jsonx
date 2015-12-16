package jsonx;

import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;

public class JsonxParserFactory implements JsonParserFactory {

    @Override
    public JsonParser createParser(Reader reader) {
        return null;
    }

    @Override
    public JsonParser createParser(InputStream in) {
        return null;
    }

    @Override
    public JsonParser createParser(InputStream in, Charset charset) {
        return null;
    }

    @Override
    public JsonParser createParser(JsonObject obj) {
        return null;
    }

    @Override
    public JsonParser createParser(JsonArray array) {
        return null;
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return null;
    }

}
