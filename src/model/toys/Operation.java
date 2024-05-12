package model.toys;

import java.io.File;
import java.io.IOException;

public interface Operation {
    File createFileName(String args) throws IOException;
    void readFile(File filePath) throws IOException;
}
