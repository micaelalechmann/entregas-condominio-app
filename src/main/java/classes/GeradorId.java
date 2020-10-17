package classes;

public class GeradorId {
    private int id = 1;

    public int getProximoId() {
        return id++;
    }
}

