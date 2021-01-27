package processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileMessage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessorReady implements Processor {

    public void process(Exchange exchange) throws Exception {
        GenericFile exchangeFileReady = ((GenericFileMessage) exchange.getIn()).getGenericFile();
        GenericFile exchangeFile = new GenericFile();
        String s = exchangeFileReady.getAbsoluteFilePath().replaceAll("\\.ready$", ".txt");
        exchangeFile.setAbsoluteFilePath(s);
        String fileName = exchangeFileReady.getFileName().replaceAll("\\.ready$", ".txt");
        exchangeFile.setFileName(fileName);
        exchangeFile.setFileNameOnly(fileName);
        exchangeFile.setRelativeFilePath(fileName);
        GenericFileMessage in = new GenericFileMessage<>(exchange.getContext(), exchangeFile);
        in.setBody(new String(Files.readAllBytes(Paths.get(s))), String.class);
        exchange.setIn(in);
        exchange.getProperties().put("CamelFileExchangeFile", exchangeFile);
    }

}
