package main.java.classes;

public class App {
    public static void main(String[] args) {
        Menu menu = new Menu(new Condominio());
        menu.run();
    }
}