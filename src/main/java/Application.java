import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        logger.info("start");
//        CamelConfig.runCamel();
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("camel-context.xml");
        // Keep main thread alive for some time to let application finish processing the input files.
        applicationContext.start();
        System.in.read();
    }
}
