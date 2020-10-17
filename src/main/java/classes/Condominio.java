package classes;

import java.util.ArrayList;
import java.util.List;

public class Condominio {

    private List<Morador> moradores;

    public Condominio() {
        this.moradores = new ArrayList<>();
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public void setMoradores(List<Morador> moradores) {
        this.moradores = moradores;
    }
}
