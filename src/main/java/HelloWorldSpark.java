import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.StringTokenizer;

public class HelloWorldSpark {
    public static void main(String[] args) {
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "Hello World first Spark";
            }
        });
    }
}
