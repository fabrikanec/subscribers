package util;

import java.io.File;

public class FIleChecker {
    public boolean exists(String filename) {
        return new File(filename).exists();
    }
}
