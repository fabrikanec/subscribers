package config;

import com.github.jsixface.YamlConfig;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


import java.io.InputStream;

/**
 * A Camel Router
 */
public class CamelConfig {
    static {
        InputStream resource = RouteBuilder.class
                .getClassLoader()
                .getResourceAsStream("configuration.yml");

        YamlConfig config = YamlConfig.load(resource);

        sourcePath = config.getString("services.camelJob.source");
        dataPath = config.getString("services.camelJob.data");
    }

    private static final String sourcePath;
    private static final String dataPath;


    public static void runCamel() throws Exception {
        // create a CamelContext
        CamelContext camel = new DefaultCamelContext();

        // add routes which can be inlined as anonymous inner class
        // (to keep all code in a single java file for this basic example)
        camel.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
//                from("file:" + sourcePath + "?scheduler=quartz2&scheduler.cron=" + cron)
//                .log("files are in processing")
//                .to("json-validator:schema.json").
                from("file:" + sourcePath + "?noop=true")
                        .log("files are in processing")
                        .to("json-validator:schema.json")
                        .to("file:" + dataPath);
            }
        });

        // start is not blocking
        camel.start();

        //prevent close main thread
        System.in.read();
    }
}