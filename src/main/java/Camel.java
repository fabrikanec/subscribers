
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Camel Router
 */
public class Camel extends RouteBuilder {
    private static final Logger logger = LoggerFactory.getLogger(Camel.class);
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        logger.info("start");
        Main.main(args);
    }

    /**
     * Lets configure the Camel routing rules using Java code...
     */
    public void configure() {

        from("file:src/data&scheduler=quartz2&scheduler.cron=0+00+21+*+*+*").to("json-validator:myschema.json").
                //from("quartz2://myGroup/myTimerName?cron=0+0/5+12-18+?+*+MON-FRI").
        to("file:target/well");

    }
}