package service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Subscriber;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class SubscriberService {


    public static void createDataFiles(HttpServletRequest req, HttpServletResponse resp) {
        Subscriber subscriber = new Subscriber(req);

        ObjectMapper mapper = new ObjectMapper();
        try {
            //Convert object to JSON string and save into file directly
            String filePath = System.getProperty("user.dir");
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
        StringBuilder name = new StringBuilder();
        name.append(destinationDirectoryPath).append(File.separator).append("json_").
                append(LocalDateTime.now().getNano()).append(".txt");
        return name.toString();
    }
}
