import model.Toy;
import model.toys.impl.FileOperation;
import util.Validator;
import util.View;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    protected static Boolean exit = false;

    private final FileOperation filePath = new FileOperation();

    private File path = filePath.createFileName("store");

    private File pathPrize = filePath.createFileName("prize");

    private final List<Toy> toyList = new ArrayList<>();

    private final List<Toy> prizeToyList = new ArrayList<>();

    private final Validator vl = new Validator();

    private final View view = new View();

    private final Toy toys = new Toy();

    public Application() throws IOException {
    }


    public void run() throws IOException {
        createToyList(path);
        while (!exit) {
            menu();
        }
    }

    public void menu() throws IOException {
        String str = view.prompt("Выберите нужное действие.\n" +
                "1. Добавить новую игрушку\n" +
                "2. Розыграть игрушку\n" +
                "3. Обновить колличество\n" +
                "4. Выход\n" +
                "Ввод: ");
        int comm = Integer.parseInt(str);
        if (comm <= 4 && comm > 0) {
            if (comm == 1) {
                addToy();
                exit = false;
            }
            if (comm == 2) {
                playToy();
                exit = false;
            }
            if (comm == 3) {
                updateCount();
                exit = false;
            }
            if (comm == 4) {
                System.out.println("Досвидания");
                exit = true;
            }
        }
    }

    public void addToy() throws IOException {
        filePath.readFile(path);
        String str = view.prompt(ANSI_GREEN + "Введите данные игрушки (Id, Name, Сount, Сhanse) через пробел: " + ANSI_RESET);
        //        String str = "3 Car 19 40";
        String[] line = vl.scheckLine(str.split(" "));
        Toy newToy = toys.createToy(line);
        if (vl.scheckToy(newToy)) {
            try (FileWriter fr = new FileWriter(path, true)) {
                fr.write(String.valueOf(newToy));
                fr.flush();
                System.out.println(ANSI_GREEN + "Игрушка добавлена!" + ANSI_RESET);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(ANSI_RED + "Игрушка с идентичными данными уже существует!" + ANSI_RESET);
        }
        toyList.add(newToy);
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

    public void updateCount() throws IOException {
        filePath.readFile(path);
        String name = view.prompt("Введите название игрышки: ");
        String count = view.prompt("Введите новое значение колличества: ");
        int newCount = Integer.parseInt(count);
        for (Toy toy : toyList) {
            if (toy.getToyName() == name) {
                toy.setCountToy(newCount);
            }
        }
    }

    public void playToy() throws IOException {
        for (Toy toy : toyList) {
            int numPrize = (int) ((toy.getChanse() / 100) * toy.getCountToy());
            for (int i = 0; i < numPrize; i++) {
                prizeToyList.add(toy);
            }
        }
        Random random = new Random();
        Toy prizeToy = prizeToyList.remove(random.nextInt(prizeToyList.size()));
        prizeToy.decreaseCountToy();
        try {
            FileWriter fr = new FileWriter(pathPrize, true);
            fr.write(prizeToy.toString());
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (toyList.isEmpty() || prizeToy.getCountToy() < 0) {
            System.out.println("Увы. Больше нету игрушек на разыгрыш");
            return;
        }
        System.out.println("Выш призовая игрушка: " + prizeToy.getToyName() + ". Их осталось в розыгрыше: " + prizeToy.getCountToy());
    }

}
