package model.toys.impl;


import model.toys.Operation;
import java.io.File;
import java.io.IOException;

public class FileOperation implements Operation {

    @Override
    public File createFileName(String args) throws IOException {
        String path = "src/DB/" + args + ".txt";
        File result = new File(path);
        result.getParentFile().mkdirs();
        result.createNewFile();
        return result;
    }


}