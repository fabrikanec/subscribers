package util;

import config.CamelConfig;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

import java.io.File;

public class MyFileFilter<T> implements GenericFileFilter<T> {
    public boolean accept(GenericFile<T> file) {

        System.out.println(file.getAbsoluteFilePath());
//        System.out.println(new File(file.getAbsoluteFilePath() + ".ready").exists());
//        boolean isExists = new File(file.getAbsoluteFilePath() + ".ready").exists();
//        boolean isFlagFile = file.getFileName().lastIndexOf(".ready") >= 0;
        if (file.getFileName().lastIndexOf(".ready") >= 0) {
            String targetFile = file.getAbsoluteFilePath().replaceAll("\\.ready$", "");
            new File(targetFile).renameTo(new File(targetFile + ".toProcessing"));
            new File(file.getAbsoluteFilePath()).delete();
            return false;
        }
        if (file.getFileName().lastIndexOf(".toProcessing") >= 0) {
//            new File(targetFile).renameTo(new File(targetFile + ".toProcessing"));
            return true;
        }
//        return new File(file.getAbsoluteFilePath() + ".ready").exists();
        return false;
    }

    public boolean move(GenericFile<T> file) {
//        System.out.println("method move used with: " + file.getAbsoluteFilePath());
//        String targetFile = CamelConfig.dataPath file.getFileName().replaceAll("\\.toProcessing$", "");
//        new File(file.getAbsoluteFilePath()).renameTo(new File(targetFile + ".toProcessing"));
        return false;
    }
}