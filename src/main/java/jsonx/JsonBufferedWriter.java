package jsonx;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonBufferedWriter {
    private BufferedWriter writer;
    
    JsonBufferedWriter(Writer writer) {
        if (writer instanceof BufferedWriter) {
            this.writer = (BufferedWriter) writer;
        } else {
            this.writer = new BufferedWriter(writer);
        }
    }
    
    JsonBufferedWriter append(char c) {
        try {
           writer.append(c);
        } catch (IOException e) {
            new JsonIOException("IO Write Error", e);
        }
        return this;
    }
    
    JsonBufferedWriter append(CharSequence cs) {
        try {
            writer.append(cs);
        } catch (IOException e) {
            new JsonIOException("IO Write Error", e);
        }
        return this;
    }
    
    void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new JsonIOException("Exception closing writer", e);
        }
    }
    void flush() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new JsonIOException("Exception closing writer", e);
        }
    }
}
