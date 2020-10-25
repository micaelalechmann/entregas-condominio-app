package classes;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
//        Menu menu2 = new Menu(new Condominio());
//        LocalDateTime dataInicial = LocalDateTime.of(2020,10,22,20,30);
//        LocalDateTime dataFinal = LocalDateTime.of(2020,10,30,20,30);
//        menu2.gerarRelatorio(dataInicial,dataFinal);
        Menu menu = new Menu(new Condominio());
        menu.run();
    }
}