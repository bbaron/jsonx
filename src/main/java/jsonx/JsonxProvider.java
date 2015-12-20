package jsonx;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.json.*;
import javax.json.spi.JsonProvider;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingJsonFactory;

public class JsonxProvider extends JsonProvider {

    private final JsonFactory jsonFactory;
    private final JsonxGeneratorFactory jsonGeneratorFactory;
    private final JsonxBuilderFactory builderFactory = new JsonxBuilderFactory();
    private final JsonxWriterFactory writerFactory = new JsonxWriterFactory();

    public JsonxProvider() {
        this.jsonFactory = new MappingJsonFactory();
        this.jsonGeneratorFactory = new JsonxGeneratorFactory(jsonFactory);
    }

    @Override
    public JsonParser createParser(Reader reader) {
        return null;
    }

    @Override
    public JsonParser createParser(InputStream in) {
        return null;
    }

    @Override
    public JsonParserFactory createParserFactory(Map<String, ?> config) {
        return null;
    }

    @Override
    public JsonGenerator createGenerator(Writer writer) {
        return jsonGeneratorFactory.createGenerator(writer);
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        return jsonGeneratorFactory.createGenerator(out);
    }

    @Override
    public JsonGeneratorFactory createGeneratorFactory(Map<String, ?> config) {
        return new JsonxGeneratorFactory(jsonFactory, config);
    }

    @Override
    public JsonReader createReader(Reader reader) {
        return null;
    }

    @Override
    public JsonReader createReader(InputStream in) {
        return null;
    }

    @Override
    public JsonWriter createWriter(Writer writer) {
        return writerFactory.createWriter(writer);
    }

    @Override
    public JsonWriter createWriter(OutputStream out) {
        return writerFactory.createWriter(out);
    }

    @Override
    public JsonWriterFactory createWriterFactory(Map<String, ?> config) {
        return new JsonxWriterFactory(config);
    }

    @Override
    public JsonReaderFactory createReaderFactory(Map<String, ?> config) {
        return null;
    }

    @Override
    public JsonObjectBuilder createObjectBuilder() {
        return builderFactory.createObjectBuilder();
    }

    @Override
    public JsonArrayBuilder createArrayBuilder() {
        return builderFactory.createArrayBuilder();
    }

    @Override
    public JsonBuilderFactory createBuilderFactory(Map<String, ?> config) {
        return new JsonxBuilderFactory(config);
    }

}
