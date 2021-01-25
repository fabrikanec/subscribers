
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

/**
 * A Camel Router
 */
public class Camel extends RouteBuilder {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main.main(args);
    }

    /**
     * Lets configure the Camel routing rules using Java code...
     */
    public void configure() {

        // TODO create Camel routes here.

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message
        // using XPath
        from("quartz2://timer1?cron=0+00+21+*+*+*").
        from("file:src/data").to("json-validator:myschema.json").
                //from("quartz2://myGroup/myTimerName?cron=0+0/5+12-18+?+*+MON-FRI").
        to("file:target/messages/well");

    }
}