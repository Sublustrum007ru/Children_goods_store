package util;

import model.Toy;
import java.io.*;

public class Validator {

    private final String  filePathToy = "src/DB/store.txt";

    private View view = new View();

    private Toy toy = new Toy();

    public boolean scheckToy(Toy newToy) throws FileNotFoundException {
        String line;
        int counter = 0;
        try(BufferedReader bf = new BufferedReader(new FileReader(filePathToy))){
            while ((line = bf.readLine()) != null) {
                String[] temp = line.replace("Id: ", "")
                        .replace("Name: ", "")
                        .replace("Count: ", "")
                        .replace("Chanse: ", "")
                        .split(" ");
                Toy tempToy = toy.createToy(temp);
                if(newToy.equals(tempToy)){
                    counter++;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        if(counter == 0){
            return true;
        }
        return false;
    }

    public String[] scheckLine(String[] line) {
        String[] result = new String[4];
        if (line.length != 4) {
            System.out.println("Введено не верное колличесвто данных");
            String temp = view.prompt(("Повторите попытку: "));
            line = temp.split(" ");
            scheckLine(line);
        }
        for (int i = 0; i < line.length; i++) {
            result[i] = line[i];
        }
        return result;
    }

    public int isId(String arg) {
        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            arg = view.prompt("Enter 'Id': ");
        }
        return Integer.parseInt(arg);
    }

    public int isCount(String arg) {
        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            arg = view.prompt("Enter 'count': ");
        }
        return Integer.parseInt(arg);
    }

    public int isChanse(String arg) {
        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            arg = view.prompt("Enter 'chanse': ");
        }
        return Integer.parseInt(arg);
    }

    public int isNumeric(String arg) {
        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            arg = view.prompt("Enter 'Count': ");
        }
        return Integer.parseInt(arg);
    }
}
