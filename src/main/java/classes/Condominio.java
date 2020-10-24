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

    public List<Entrega> buscaEntregasPelaDescricao(String descricao) {
        List<Entrega> entregasFiltradas = new ArrayList<>();

        for (Entrega entrega : this.entregas) {
            if (entrega.getDescricao().toLowerCase().contains(descricao.toLowerCase())) {
                entregasFiltradas.add(entrega);
            }
        }

        return entregasFiltradas;
    }

    public boolean cadastrarEntrega(Entrega entrega) {
        return this.entregas.add(entrega);
    }

     public List<Entrega> buscaEntregasNaoRetiradas (){
        List<Entrega> entregasNaoRetiradas = new ArrayList<>();
        for (Entrega confereEntrega: this.entregas){
            if(!confereEntrega.entregaFoiRetirada()){
                entregasNaoRetiradas.add(confereEntrega);
            }
        }
        return entregasNaoRetiradas;
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

}
