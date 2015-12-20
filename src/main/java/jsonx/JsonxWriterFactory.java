package jsonx;

import static java.util.Collections.*;

import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;

public class JsonxWriterFactory implements JsonWriterFactory {
    private final Map<String, ?> config;
    private static final Map<String, Object> NO_OPTIONS = emptyMap();

    JsonxWriterFactory() {
        this(null);
    }

    JsonxWriterFactory(Map<String, ?> config) {
        this.config = config == null ? NO_OPTIONS : new LinkedHashMap<>(config);
    }

    @Override
    public JsonWriter createWriter(Writer writer) {
        return null;
    }

    @Override
    public JsonWriter createWriter(OutputStream out) {
        return null;
    }

    @Override
    public JsonWriter createWriter(OutputStream out, Charset charset) {
        return null;
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return null;
    }

}
