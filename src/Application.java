import model.toys.impl.Toy;
import util.Validator;
import util.View;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    private final List<Toy> toyList = new ArrayList<>();

    private final Validator vl = new Validator();

    private View view = new View();

    private Toy toy = new Toy();

    public void run() throws FileNotFoundException {
        view.hello();
//        String str = view.prompt("Введите данные: ");
        String str = "1 Mishka 10 50";
        Toy newToy = toy.createToy(str.split(" "));
        if(vl.scheckToy(newToy)){
            try(FileWriter fr = new FileWriter("src/DB/store.txt", true)){
                fr.write(String.valueOf(newToy));
                fr.flush();
                System.out.println("Запись добавлена!");
            }catch (IOException e){
                e.printStackTrace();
            }
            toyList.add(newToy);
        }else{
            System.out.println("Идентичная запись уже существует");
        }
        for (int i = 0; i < toyList.size(); i++) {
            System.out.println(toyList.get(i));
        }
    }

}
