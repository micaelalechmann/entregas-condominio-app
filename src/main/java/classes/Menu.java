package classes;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Condominio condominio;
    private Scanner scanner;

    public Menu(Condominio condominio){
        this.condominio = condominio;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("-- Menu -- \n");
        System.out.println("0 - Sair");
        System.out.println("1 - Pesquisar entregas por descrição");
        System.out.println("2 - Listar entregas não retiradas");
        System.out.println("3 - Cadastrar entregas ");

        this.scanner.reset();
        int numOpcao = recebeNumero();

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
            case 2:
                this.listarEntregasNaoRetiradas();
                this.run();
                break;
            case 3:
                this.cadastrarEntrega();
                this.run();
                break;

            default:
                System.out.println("Opção inexistente");
                this.run();
                break;
        }
    }

    private void cadastrarEntrega() {
        System.out.println("Digite a descrição da entrega: ");

        String descricaoPedido = this.scanner.nextLine();

        System.out.println("Digite o nome do operador que recebeu: ");
        String nomeOperador = this.scanner.nextLine();

        System.out.println("Digite o número do apartamento: ");
        Integer numeroApartamento = recebeNumero();

        Operador operadorResponsavel = new Operador(nomeOperador);
        Entrega entrega = new Entrega(Entrega.getUProximoIdEntrega(),
                descricaoPedido, operadorResponsavel, numeroApartamento);

        this.condominio.cadastrarEntrega(entrega);

        System.out.println("Entrega cadastrada ");

    }

    private Integer recebeNumero() {
        while (true) {
            String aux = this.scanner.nextLine();
            aux = aux.replaceAll(" ", "");
            if(aux.matches("^\\d+$")){
                return Integer.parseInt(aux);
            }
            System.out.println("Valor inválido, digite um número válido: ");
        }
    }

    public void listarEntregasPorDescricao(){
        System.out.println("Digite a descrição desejada:");

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

    public void listarEntregasNaoRetiradas(){

        List<Entrega> entregasNaoRetiradas =  this.condominio.buscaEntregasNaoRetiradas();

        System.out.println("-- Entregas não retiradas:\n");

        if (entregasNaoRetiradas.size() > 0) {
            for (Entrega entrega : entregasNaoRetiradas) {
                System.out.println(entrega.toString());
            }
            System.out.println("\n\n");
        } else {
            System.out.println("Todas as entregas foram retiradas");
        }
    }
}
