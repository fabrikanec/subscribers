package service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsixface.YamlConfig;
import model.Subscriber;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SubscriberService {

    private static final String sourcePath;

    static {
        InputStream resource = RouteBuilder.class
                .getClassLoader()
                .getResourceAsStream("configuration.yml");

        YamlConfig config = YamlConfig.load(resource);

        sourcePath = config.getString("services.front.work_dir");
    }


    public static void createDataFiles(HttpServletRequest req, HttpServletResponse resp) {
        Subscriber subscriber = new Subscriber(req);

        ObjectMapper mapper = new ObjectMapper();
        try {
            //Convert object to JSON string and save into file directly
            String filePath = sourcePath;
            String separator = File.separator;
            File jsonFile = new File(filePath + separator + "jsondata.txt");
            mapper.writeValue(jsonFile, subscriber);

            //move JSON file to dir 'work' and rename it
            File destinationDirectory = new File(filePath + separator + "work");
            if (! destinationDirectory.exists()){
                // If you require it to make the entire destinationDirectory path including parents,
                // use destinationDirectory.mkdirs(); here instead.
                destinationDirectory.mkdir();
            }
            String destinationFileName = createFileName(destinationDirectory.getAbsolutePath());
            File destinationFile = new File(destinationFileName);
            try {
                FileUtils.copyFile(jsonFile, destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //create file-flag
            File fileFlag = new File(destinationFileName.replace(".txt", "") + ".ready");
            fileFlag.createNewFile();

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createFileName(String destinationDirectoryPath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy_HHmm");
        String formattedDateTime = LocalDateTime.now().format(formatter);

        return destinationDirectoryPath + File.separator + "json_" +
                formattedDateTime + ".txt";
    }
}
