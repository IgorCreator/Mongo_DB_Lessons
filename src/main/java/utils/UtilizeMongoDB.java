package utils;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;

import java.io.StringWriter;

import static org.bson.json.JsonWriterSettings.builder;

public class UtilizeMongoDB {
    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                builder().outputMode(JsonMode.SHELL).indent(false).build());

        new DocumentCodec().encode(jsonWriter, document,
                EncoderContext.builder()
                        .isEncodingCollectibleDocument(true)
                        .build());

        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }
}
