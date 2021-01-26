package service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Subscriber;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;

public class SubscriberService {


    public static void createDataFiles(HttpServletRequest req, HttpServletResponse resp) {
        Subscriber subscriber = new Subscriber(req);

        ObjectMapper mapper = new ObjectMapper();
        try {
            //Convert object to JSON string and save into file directly
            String filePath = System.getProperty("user.dir");
            String separator = File.separator;
            mapper.writeValue(new File(filePath + separator + "jsondata.txt"), subscriber);

            //Convert object to JSON string and pretty print
//            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(subscriber);
//            System.out.println(jsonInString);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
