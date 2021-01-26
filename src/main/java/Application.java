import config.CamelConfig;
import org.apache.camel.component.quartz2.CamelJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(CamelJob.class);


    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        logger.info("start");
        CamelConfig.runCamel();
    }
}
