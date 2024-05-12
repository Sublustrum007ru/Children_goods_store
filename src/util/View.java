package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class View {
    private final String filePatch = "src/Hello.txt";

    public void hello() {
        String line;
        try (BufferedReader in = new BufferedReader(new FileReader(filePatch));) {
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String prompt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);;
        return scanner.nextLine();

    }
}
