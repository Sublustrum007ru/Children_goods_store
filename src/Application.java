import model.Toy;
import model.toys.impl.FileOperation;
import util.Validator;
import util.View;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private final FileOperation filePath = new FileOperation();

    private final List<Toy> toyList = new ArrayList<>();

    private final Validator vl = new Validator();

    private final View view = new View();

    private final Toy toy = new Toy();


    public void run() throws IOException {
        view.hello();
        File path = filePath.createFileName("store");
        filePath.readFile(path);
        String str = view.prompt("Введите данные игрушки (Id, Name, Сount, Сhanse) через пробел: ");
//        String str = "1 Mishka 10 25";
        String[] line = vl.scheckLine(str.split(" "));
        Toy newToy = toy.createToy(line);
        if (vl.scheckToy(newToy)) {
            try (FileWriter fr = new FileWriter(path, true)) {
                fr.write(String.valueOf(newToy));
                fr.flush();
                System.out.println("Игрушка добавлена!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Игрушка с идентичными данными уже существует!");
        }
    }

}
