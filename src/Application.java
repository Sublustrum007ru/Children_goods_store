import model.Toy;
import model.toys.impl.FileOperation;
import util.Validator;
import util.View;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private final FileOperation filePath = new FileOperation();

    private final List<Toy> toyList = new ArrayList<>();

    private final Validator vl = new Validator();

    private final View view = new View();

    private final Toy toys = new Toy();


    public void run() throws IOException{
        view.hello();
        File path = filePath.createFileName("store");
        filePath.readFile(path);
        createToyList(path);

//        String str = view.prompt(ANSI_GREEN + "Введите данные игрушки (Id, Name, Сount, Сhanse) через пробел: " + ANSI_RESET);
        String str = "2 Dog 10 25";
        String[] line = vl.scheckLine(str.split(" "));

        Toy newToy = toys.createToy(line);
        if (vl.scheckToy(newToy)) {
            try (FileWriter fr = new FileWriter(path, true)) {
                fr.write(String.valueOf(newToy));
                fr.flush();
                addToy(newToy);
                System.out.println(ANSI_GREEN + "Игрушка добавлена!" + ANSI_RESET);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(ANSI_RED + "Игрушка с идентичными данными уже существует!" + ANSI_RESET);
        }
        System.out.println("Вывод toyList");
        System.out.println(toyList);
    }

    public void createToyList(File path) throws IOException {
        String line;
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            while ((line = bf.readLine()) != null) {
                String[] temp = line.replace("Id: ", "")
                        .replace("Name: ", "")
                        .replace("Count: ", "")
                        .replace("Chanse: ", "")
                        .split(" ");
                toyList.add(toys.createToy(temp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToy(Toy toy) {
        toyList.add(toy);
    }

}
