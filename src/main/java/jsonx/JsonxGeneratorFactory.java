package jsonx;

import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class JsonxGeneratorFactory implements JsonGeneratorFactory {

    @Override
    public JsonGenerator createGenerator(Writer writer) {
        return null;
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        return null;
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out, Charset charset) {
        return null;
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return null;
    }

}
