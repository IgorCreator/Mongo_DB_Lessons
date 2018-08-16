package mongotest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static utils.UtilizeMongoDB.printJson;

public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("insertTest");
        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(10))
                    .append("y", new Random().nextInt(100))
                    .append("i", i));
        }

        System.out.println("Find one:");
        printJson(collection.find().first());

        System.out.println("Find by Simple filter with into:");
        Bson filter = new Document("name 6", new Document("$gt", 5));
        for (Document document : collection.find(filter).into(new ArrayList<Document>())) {
            printJson(document);
        }

        System.out.println("Find by Static filter with into:");
        Bson filterSecond = and(eq("x", 0), lt("y", 90));
        for (Document document : collection.find(filterSecond).into(new ArrayList<Document>())) {
            printJson(document);
        }

        System.out.println("Find all with into:");
        for (Document document : collection.find().into(new ArrayList<Document>())) {
            printJson(document);
        }

        System.out.println("Counted documents: " + collection.countDocuments());

        System.out.println("With projection");
        //Bson projectionOneWay = new Document("x", 0).append("_id", 0);
        Bson projectionSecondWay = fields(include("x", "i"), excludeId());
        List<Document> all = collection.find().projection(projectionSecondWay).into(new ArrayList<Document>());
        for (Document document : all) {
            printJson(document);
        }

    }
}
