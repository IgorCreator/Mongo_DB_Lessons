import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

public class SparkFormHandling {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                try {
                    StringWriter writer = new StringWriter();
                    Map<String, Object> fruitMap = new HashMap<String, Object>();
                    fruitMap.put("fruits", Arrays.asList("apple", "banana", "orange", "watermelon"));
                    Template template = configuration.getTemplate("fruit_chooser.html");
                    template.process(fruitMap, writer);
                    return writer;
                } catch (Exception e) {
                    halt(500);
                    return null;
                }
            }
        });

        Spark.post("/favorite_fruit", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Pick one";
                } else {
                    return "Your favorite fruit";
                }
            }
        });
    }
}
