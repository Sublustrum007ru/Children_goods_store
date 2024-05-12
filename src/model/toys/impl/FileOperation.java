package model.toys.impl;

import model.Toy;
import model.toys.Operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileOperation implements Operation {
    @Override
    public File createFileName(String args) throws IOException {
        String path = "src/DB/" + args + ".txt";
        File result = new File(path);
        result.getParentFile().mkdirs();
        result.createNewFile();
        return result;
    }

    @Override
    public void readFile(File filePath) throws IOException {
        String line;
        try(BufferedReader bf = new BufferedReader(new FileReader(filePath))){
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
