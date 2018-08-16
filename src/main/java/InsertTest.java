import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document ;

import static java.util.Arrays.asList;
import static utils.UtilizeMongoDB.printJson;

public class InsertTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();

        Document smith = new Document()
                .append("name", "Smith")
                .append("age", 42)
                .append("profession", "programmer");
        Document jones = new Document()
                .append("name", "Jones")
                .append("age", 25)
                .append("profession", "driver");

        collection.insertMany(asList(smith, jones));
        printJson(smith);
        printJson(jones);


        MongoDatabase database = client.getDatabase("school");
        MongoCollection<Document> people = database.getCollection("people");
        Document doc = new Document("name", "Andrew Erlichson").append("company", "10gen");

        people.insertOne(doc);      // first insert
        printJson(doc);
        doc.remove("_id");             // remove the _id key
        people.insertOne(doc);      // second insert
        printJson(doc);
    }


}
