package jsonx;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import javax.json.spi.JsonProvider;

public class JsonxProvider extends JsonProvider {

    @Override
    public JsonxParser createParser(Reader reader) {
        return null;
    }

    @Override
    public JsonxParser createParser(InputStream in) {
        return null;
    }

    @Override
    public JsonxParserFactory createParserFactory(Map<String, ?> config) {
        return null;
    }

    @Override
    public JsonxGenerator createGenerator(Writer writer) {
        return null;
    }

    @Override
    public JsonxGenerator createGenerator(OutputStream out) {
        return null;
    }

    @Override
    public JsonxGeneratorFactory createGeneratorFactory(Map<String, ?> config) {
        return null;
    }

    @Override
    public JsonxReader createReader(Reader reader) {
        return null;
    }

    @Override
    public JsonxReader createReader(InputStream in) {
        return null;
    }

    @Override
    public JsonxWriter createWriter(Writer writer) {
        return null;
    }

    @Override
    public JsonxWriter createWriter(OutputStream out) {
        return null;
    }

    @Override
    public JsonxWriterFactory createWriterFactory(Map<String, ?> config) {
        return null;
    }

    @Override
    public JsonxReaderFactory createReaderFactory(Map<String, ?> config) {
        return null;
    }

    @Override
    public JsonxObjectBuilder createObjectBuilder() {
        return null;
    }

    @Override
    public JsonxArrayBuilder createArrayBuilder() {
        return null;
    }

    @Override
    public JsonxBuilderFactory createBuilderFactory(Map<String, ?> config) {
        return null;
    }

}
