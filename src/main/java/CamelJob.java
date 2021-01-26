
import com.github.jsixface.YamlConfig;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * A Camel Router
 */
public class CamelJob {
    static {
        InputStream resource = RouteBuilder.class
                .getClassLoader()
                .getResourceAsStream("configuration.yml");

        YamlConfig config = YamlConfig.load(resource);

        sourcePath = config.getString("services.camelJob.source");
        dataPath = config.getString("services.camelJob.data");
        cron = config.getString("services.camelJob.cron");
    }

    private static final Logger logger = LoggerFactory.getLogger(CamelJob.class);
    private static final String sourcePath;
    private static final String dataPath;
    private static final String cron;
    
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        logger.info("start");
        runCamel();
    }

    private static void runCamel() throws Exception{
        // create a CamelContext
        CamelContext camel = new DefaultCamelContext();

        // add routes which can be inlined as anonymous inner class
        // (to keep all code in a single java file for this basic example)
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:" + sourcePath + "?scheduler=quartz2&scheduler.cron=" + cron).log("files are in processing").to("json-validator:schema.json").
//                from("file:src/work").log("test log").to("json-validator:schema.json").
                        //from("quartz2://myGroup/myTimerName?cron=0+0/5+12-18+?+*+MON-FRI").
                                to("file:" + dataPath);
            }
        });

        // start is not blocking
        camel.start();

        // so run for 10 seconds
        Thread.sleep(10_000); //works if uncommenting
//        Thread.currentThread().join();
        // and then stop nicely
//        camel.stop();
    }

//    /**
//     * Lets configure the Camel routing rules using Java code...
//     */
//    public void configure() {
//
//        from("file:src/data&scheduler=quartz2&scheduler.cron=0+00+21+*+*+*").to("json-validator:myschema.json").
//                //from("quartz2://myGroup/myTimerName?cron=0+0/5+12-18+?+*+MON-FRI").
//        to("file:target/well");
//
//    }
}