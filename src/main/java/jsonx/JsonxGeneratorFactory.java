package jsonx;

import static java.util.Collections.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.json.JsonException;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

class JsonxGeneratorFactory implements JsonGeneratorFactory {

    private final JsonFactory factory;
    private final Map<String, ?> config;
    private static final Map<String, Object> NO_OPTIONS = emptyMap();

    JsonxGeneratorFactory(JsonFactory factory) {
        this(factory, NO_OPTIONS);

    }

    JsonxGeneratorFactory(JsonFactory factory, Map<String, ?> config) {
        this.factory = factory;
        this.config = config == null ? NO_OPTIONS : new LinkedHashMap<>(config);
    }

    @Override
    public JsonGenerator createGenerator(Writer writer) {
        try {
            com.fasterxml.jackson.core.JsonGenerator jackson = factory.createGenerator(writer);
            jackson.setPrettyPrinter(new DefaultPrettyPrinter());
            return new JsonxGenerator(config, jackson);
        } catch (IOException e) {
            throw new JsonException("Error creating generator", e);
        }
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        return createGenerator(out, null);
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out, Charset charset) {
        try {
            JsonEncoding enc = toJsonEncoding(charset);
            com.fasterxml.jackson.core.JsonGenerator jackson = factory.createGenerator(out, enc);
            if (System.getProperty(JsonGenerator.PRETTY_PRINTING) != null ) {
                jackson.setPrettyPrinter(new DefaultPrettyPrinter());                
            }
            return new JsonxGenerator(config, jackson);
        } catch (IOException e) {
            throw new JsonException("Error creating generator", e);
        }
    }

    private JsonEncoding toJsonEncoding(Charset charset) {
        if (charset == null) {
            return JsonEncoding.UTF8;
        }
        for (JsonEncoding enc : JsonEncoding.values()) {
            if (enc.getJavaName().equalsIgnoreCase(charset.name())) {
                return enc;
            }
        }
        throw new JsonException(charset + " not supported");
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return unmodifiableMap(config);
    }

}
