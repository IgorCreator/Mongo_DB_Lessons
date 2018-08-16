package mongotest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static utils.UtilizeMongoDB.printJson;

public class UpdateTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");
        collection.drop();

        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i)
                    .append("y", true));
        }


        //collection.updateOne(eq("x", 5), Updates.set("x",20));
        //collection.replaceOne(eq("x", 5), new Document("x", 20).append("updated", true));
        //collection.updateOne(eq("x", 5), new Document("$set", new Document("x",20).append("updated", true)));
        collection.updateOne(eq("x", 5), combine(set("x",20), set("updated", true)));
        collection.updateOne(eq("_id", 9), combine(set("x",11), set("inserted", true)), new UpdateOptions().upsert(true));

        System.out.println("Find by into:");
        for (Document document : collection.find().into(new ArrayList<Document>())) {
            printJson(document);
        }
    }
}