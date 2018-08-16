package mongotest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static utils.UtilizeMongoDB.printJson;

public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");
        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document("name " + i, i));
        }

        System.out.println("Find one:");
        printJson(collection.find().first());

        System.out.println("Find all with into:");
        for (Document document : collection.find().into(new ArrayList<Document>())) {
            printJson(document);
        }

        System.out.println("Counted documents: " + collection.countDocuments());

    }
}
