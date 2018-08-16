package mongotest;

import org.bson.Document;

import java.util.Arrays;
import java.util.Date;

import static utils.UtilizeMongoDB.printJson;

public class DocumentsTest {
    public static void main(String[] args) {
        Document documents = new Document()
                .append("string", "MongoDB")
                .append("integer", 42)
                .append("long", 1L)
                .append("double", 1.1)
                .append("boolean", true)
                .append("date", new Date())
                .append("null", null)
                .append("nestedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1,2,3));

        String string = documents.getString("string");
        int number = documents.getInteger("integer");

        printJson(documents);
    }
}
