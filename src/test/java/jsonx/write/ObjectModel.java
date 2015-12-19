package jsonx.write;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class ObjectModel {

    public static void main(String[] args) {
        System.setProperty(JsonGenerator.PRETTY_PRINTING, "true");
        JsonBuilderFactory jbf = Json.createBuilderFactory(null);
        JsonGeneratorFactory jgf = Json.createGeneratorFactory(null);
        StringWriter writer = new StringWriter();
        writer.write("JSON\n");
        JsonGenerator jsonGenerator = jgf.createGenerator(writer);
        // @formatter:off
        JsonObject model = jbf.createObjectBuilder()
                .add("firstName", "Duke")
                .add("lastName", "Java")
                .add("age", 18)
                .add("streetAddress", "100 Internet Dr")
                .add("city", "JavaTown")
                .add("state", "JA")
                .add("postalCode", "12345")
                .add("phoneNumbers", Json.createArrayBuilder()
                   .add(Json.createObjectBuilder()
                      .add("type", "mobile")
                      .add("number", "111-111-1111"))
                   .add(Json.createObjectBuilder()
                      .add("type", "home")
                      .add("number", "222-222-2222")))
                .build();
        
        jsonGenerator
            .writeStartArray()
                .writeStartObject()
                    .write("firstName", "Tess")
                    .write("lastName", "Java")
                    .write("age", 18)
                    .write("streetAddress", "100 Internet Dr")
                    .write("city", "JavaTown")
                    .write("state", "JA")
                    .write("postalCode", "12345")
                    .writeStartArray("phoneNumbers")
                        .writeStartObject()
                            .write("type", "mobile")
                            .write("number", "311-111-1111")
                        .writeEnd()                    
                        .writeStartObject()
                            .write("type", "home")
                            .write("number", "422-222-2222")
                        .writeEnd()                    
                    .writeEnd()
                .writeEnd()
                .write(model)
                .write(model)
            .writeEnd();
        jsonGenerator.close();
        System.out.println(writer.toString());
    }
}
