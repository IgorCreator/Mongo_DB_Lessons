import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;

public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
//        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
//        MongoClient client = new MongoClient(asList(new ServerAddress("localhost", 27017)));
//        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient anotherClient = new MongoClient(new ServerAddress(), options);
        MongoDatabase db = anotherClient.getDatabase("test");

        MongoCollection<BsonDocument> collection = db.getCollection("test", BsonDocument.class);
    }
}