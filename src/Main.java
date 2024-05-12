import util.View;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        View view = new View();
        Application app = new Application();
        view.hello();
        app.run();
    }
}