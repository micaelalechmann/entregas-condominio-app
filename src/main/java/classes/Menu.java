package main.java.classes;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Condominio condominio;
    private Scanner scanner;

    public Menu(Condominio condominio){
        this.condominio = condominio;
        this.scanner = new Scanner(System.in);
    }

    public void run(){
        System.out.println("-- Menu -- \n");
        System.out.println("0 - Sair");
        System.out.println("1 - Pesquisar entregas por descrição");

        this.scanner.reset();
        int numOpcao = this.scanner.nextInt();

        this.executarAcao(numOpcao);
    }

    public void executarAcao(int numOpcao){
        switch (numOpcao){
            case 0:
                System.out.println("\nSaindo...");
                break;
            case 1:
                this.listarEntregasPorDescricao();
                this.run();
                break;
            default:
                System.out.println("Opção inexistente");
                this.run();
                break;
        }
    }

    public void listarEntregasPorDescricao(){
        System.out.println("Digite a descrição desejada:");

        this.scanner.skip("\n");
        String descricao = this.scanner.nextLine();

        List<Entrega> entregasFiltradas =  this.condominio.buscaEntregasPelaDescricao(descricao);

        System.out.println("-- Entregas que contenham a descrição: " + descricao + " -- \n");

        if (entregasFiltradas.size() > 0) {
            for (Entrega entrega : entregasFiltradas) {
                System.out.println(entrega.toString());
            }
            System.out.println("\n\n");
        } else {
            System.out.println("Nenhuma entrega contem a descrição buscada");
        }
    }
}
