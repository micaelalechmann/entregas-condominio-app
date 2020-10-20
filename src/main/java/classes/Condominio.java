package classes;

import java.util.ArrayList;
import java.util.List;

public class Condominio {

    private List<Morador> moradores;
    private List<Entrega> entregas;

    public Condominio() {
        this.moradores = new ArrayList<>();
        this.entregas = new ArrayList<>();
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public void setMoradores(List<Morador> moradores) {
        this.moradores = moradores;
    }

    public List<Entrega> getEntregas() {
        return this.entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }

    public void listaEntregasPelaDescricao(String descricao) {
        List<Entrega> entregasFiltradas = buscaEntregasPelaDescricao(descricao);

        System.out.println("Entregas que contenham a descrição: " + descricao);

        if (entregasFiltradas.size() > 0) {
            for (Entrega entrega : entregasFiltradas) {
                System.out.println(entrega.toString());
            }
        } else {
            System.out.println("Nenhuma entrega contem a descrição buscada");
        }
    }

    public List<Entrega> buscaEntregasPelaDescricao(String descricao) {
        List<Entrega> entregasFiltradas = new ArrayList<>();

        for (Entrega entrega : this.entregas) {
            if (entrega.getDescricao().toLowerCase().contains(descricao.toLowerCase())) {
                entregasFiltradas.add(entrega);
            }
        }

        return entregasFiltradas;
    }
}
