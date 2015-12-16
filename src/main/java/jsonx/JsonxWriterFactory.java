package jsonx;

import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;

public class JsonxWriterFactory implements JsonWriterFactory {

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
