package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private GeradorId geradorId;
    private Condominio condominio;
    private Operador operadorAtual;
    private Scanner scanner;

    public Menu(Condominio condominio){
        this.condominio = condominio;
        instanciaDadosIniciais();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Operador atual: " + (operadorAtual == null ? "Nenhum" : operadorAtual.getNome()));
        System.out.println("-- Menu -- \n");
        System.out.println("0 - Sair");
        System.out.println("1 - Pesquisar entregas por descrição");
        System.out.println("2 - Listar entregas não retiradas");
        System.out.println("3 - Cadastrar entregas");
        System.out.println("4 - Operadores");
        System.out.println("5 - Cadastrar novo morador");
        System.out.println("6 - Listar moradores");


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
            case 4:
                this.menuOperador();
                this.run();
                break;
            case 5:
                this.cadastrarMorador();
                this.run();
                break;
            case 6:
                this.listarMoradores();
                this.run();
                break;
            default:
                System.out.println("Opção inexistente");
                this.run();
                break;
        }
    }

    private void cadastrarMorador() {
        System.out.println("Digite o nome do morador: ");
        final String nomeOperador = this.scanner.nextLine();

        System.out.println("Digite o RG do morador: ");
        final String rgMorador = this.scanner.nextLine();

        System.out.println("Digite o número do apartamento: ");
        final Integer numeroApartamento = recebeNumero();

        final Morador morador = new Morador(nomeOperador, rgMorador, numeroApartamento);

        this.condominio.cadastrarMorador(morador);
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

    public void listarMoradores(){
        List<Morador> moradores = this.condominio.getMoradores();

        System.out.println("-- Moradores:\n");

        if(moradores.size() > 0) {
            for (Morador morador : moradores) {
                System.out.println(morador);
            }
            System.out.println("\n\n");
        } else {
            System.out.println("Nenhum morador cadastrado");
        }
    }

    public void menuOperador() {
        System.out.println("1 - Cadastrar operador");
        System.out.println("2 - Selecionar operador");
        System.out.println("3 - Voltar ");

        this.scanner.reset();
        int numOpcao = recebeNumero();

        switch (numOpcao){
            case 1:
                this.cadastrarOperador();
                this.menuOperador();
                break;
            case 2:
                this.listarOperadores();
                this.menuOperador();
                break;
            case 3:
                break;
            default:
                System.out.println("Opção inexistente");
                this.menuOperador();
                break;
        }
    }

    public void cadastrarOperador() {
        System.out.println("Digite o nome do operador: ");
        String nomeOperador = this.scanner.nextLine();

        Operador novoOperador = new Operador(nomeOperador);

        condominio.cadastrarOperador(novoOperador);

        System.out.println("Operador cadastrado com sucesso!");
        this.scanner.reset();
    }

    public void listarOperadores() {
        System.out.println("Listando todos os operadores...");

        for (int i = 0; i < condominio.getOperadores().size(); i++)
            System.out.println((i + 1) + " - " + condominio.getOperadores().get(i).getNome());

        System.out.println("\nSelecione um operador: ");
        int indiceOperador = recebeNumero();

        operadorAtual = condominio.getOperadores().get(indiceOperador - 1);
        this.scanner.reset();
    }

    protected void instanciaDadosIniciais() {
        geradorId = new GeradorId();
        lerArquivoMorador("src/inputFiles/dadosMorador.csv");
        lerArquivoOperador("src/inputFiles/dadosOperador.csv");
        lerArquivoEntrega("src/inputFiles/dadosEntrega.csv");

    }

    protected void lerArquivoMorador(String caminho) {
        try {
            File arquivo = new File(caminho);
            Scanner leitor = new Scanner(arquivo);

            leitor.useDelimiter(",");
            List<Morador> moradores = new ArrayList<>();

            while (leitor.hasNextLine()) {
                String[] data = leitor.nextLine().split(",");
                if(data.length == 3) {
                    moradores.add(new Morador(data[0], data[1], Integer.parseInt(data[2])));
                }
            }
            this.condominio.setMoradores(moradores);
            leitor.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }


    protected void lerArquivoOperador(String caminho) {
        try {
            File arquivo = new File(caminho);
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String data = leitor.nextLine();
                if(!data.isEmpty()) {
                    this.condominio.cadastrarOperador(new Operador(data));
                }
            }
            leitor.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    protected void lerArquivoEntrega(String caminho) {
        try {
            File arquivo = new File(caminho);
            Scanner leitor = new Scanner(arquivo);

            leitor.useDelimiter(",");
            List<Entrega> entregas = new ArrayList<>();

            while (leitor.hasNextLine()) {
                String[] data = leitor.nextLine().split(",");

                Morador morador = this.condominio.getMoradores().get(0);
                if(data.length == 4) {
                    for(Morador moradorDaLista : this.condominio.getMoradores()){
                        if(moradorDaLista.getNome().equals(data[3])) {
                            morador = moradorDaLista;
                        }
                    }
                    Entrega entrega = new Entrega(geradorId.getProximoId(), data[0], new Operador(data[1]), Integer.parseInt(data[2]));
                    entrega.setMoradorQueRetirou(morador);
                    entrega.setDataRetirada(LocalDateTime.now());
                    entregas.add(entrega);
                }
                else if(data.length == 3) {
                    entregas.add(new Entrega(geradorId.getProximoId(), data[0], new Operador(data[1]), Integer.parseInt(data[2])));
                }
            }
            this.condominio.setEntregas(entregas);
            leitor.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

}
