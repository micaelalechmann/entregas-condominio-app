package classes;
import main.java.classes.Menu;
import main.java.classes.Condominio;

public class App {
    public static void main(String[] args) {
        Menu menu = new Menu(new Condominio());
        menu.run();
    }
}