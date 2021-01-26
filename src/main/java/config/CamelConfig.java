package config;

import com.github.jsixface.YamlConfig;
import org.apache.camel.CamelContext;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultEndpoint;
import org.springframework.context.annotation.Bean;
import util.MyFileFilter;


import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * A Camel Router
 */
public class CamelConfig {
    static {
        InputStream resource = RouteBuilder.class
                .getClassLoader()
                .getResourceAsStream("configuration.yml");

        YamlConfig config = YamlConfig.load(resource);

        sourcePath = config.getString("services.front.work_dir");
        dataPath = config.getString("services.camelJob.data");
    }

    private static final String sourcePath;
    public static final String dataPath;


    public static void runCamel() throws Exception {
        // create a CamelContext
        CamelContext camel = new DefaultCamelContext();

        // add routes which can be inlined as anonymous inner class
        // (to keep all code in a single java file for this basic example)
        class Router extends RouteBuilder {
            @Override
            public void configure() throws Exception {
                from("file:" + sourcePath + "?noop=true")
                        .filter()
                        .method(MyFileFilter.class, "accept")
//                from("file:" + sourcePath + "?noop=true&filter=#myFilter")
                        .log("files are in processing")
                        .to("json-validator:schema.json");  // После валидации необходимо добавить choice -- проверку,
                        // на основе которой решить, в какую директорию переещать файл.

                //TODO добавить ещё одну цепочку обогащения файлов в директории data, переносящую файлы в директорию done.
            }

        }

        camel.addRoutes(new Router());

        // start is not blocking
        camel.start();

        //prevent close main thread
        System.in.read();
    }
}