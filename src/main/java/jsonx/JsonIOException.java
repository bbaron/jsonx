package jsonx;

import java.io.IOException;

import javax.json.JsonException;

public class JsonIOException extends JsonException {

    private static final long serialVersionUID = 1L;

    public JsonIOException(String message) {
        super(message);
    }

    public JsonIOException(String message, IOException cause) {
        super(message, cause);
    }

}
