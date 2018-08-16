package mongotest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static utils.UtilizeMongoDB.printJson;

public class DeleteTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");
        collection.drop();

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i));
        }

        collection.deleteMany(or(eq("_id", 3), gt("_id", 4)));

        System.out.println("Find by into:");
        for (Document document : collection.find().into(new ArrayList<Document>())) {
            printJson(document);
        }
    }
}