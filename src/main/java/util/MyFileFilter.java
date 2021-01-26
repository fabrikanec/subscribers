package util;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

import java.io.File;

public class MyFileFilter<T> implements GenericFileFilter<T> {
    public boolean accept(GenericFile<T> file) {

        System.out.println(file.getAbsoluteFilePath());
        return new File(file.getAbsoluteFilePath() + ".ready").exists();
    }
}