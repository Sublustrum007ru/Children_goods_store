package util;

import model.toys.impl.Toy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Validator {

    private View view = new View();

    private Toy toy = new Toy();

    public boolean scheckToy(Toy newToy){
        String line;
        int counter = 0;
        try(BufferedReader in  = new BufferedReader(new FileReader("src/DB/store.txt"))){
            while ((line = in.readLine()) != null) {
                String[] temp = line.replace("Id:", "")
                        .replace("Name:", "")
                        .replace("Count:", "")
                        .replace("Chanse:", "")
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

    public String isId(String arg) {
        try{
            Integer.parseInt(arg);
            return arg;
        }catch(NumberFormatException e){
            return arg = view.prompt("Enter 'Id': ");
        }
    }

    public String isCount(String arg) {
        try{
            Integer.parseInt(arg);
            return arg;
        }catch(NumberFormatException e){
            return arg = view.prompt("Enter 'count': ");
        }
    }

    public String isChanse(String arg){
        try{
            Double.parseDouble(arg);
            return arg;
        }catch(NumberFormatException e){
            return arg = view.prompt("Enter 'chanse': ");
        }
    }
}
