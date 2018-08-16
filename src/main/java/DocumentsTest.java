import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;

import static org.bson.json.JsonWriterSettings.builder;

public class DocumentsTest {
    public static void main(String[] args) {
        Document documents = new Document()
                .append("string", "MongoDB")
                .append("integer", 42)
                .append("long", 1L)
                .append("double", 1.1)
                .append("boolean", true)
                .append("date", new Date())
                .append("objectId", new Object())
                .append("null", null)
                .append("nestedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1,2,3));

        String string = documents.getString("string");
        int number = documents.getInteger("integer");

        printJson(documents);
    }

    private static void printJson(Document documents) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                new JsonWriterSettings(JsonMode.SHELL,true));

        new DocumentCodec().encode(jsonWriter, documents,
                        EncoderContext.builder()
                        .isEncodingCollectibleDocument(true)
                        .build());

        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }
}
