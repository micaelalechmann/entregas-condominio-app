package classes;

public class Morador {
  private final String nome;
  private final String rg;
  private int numeroApartamento;

  public Morador(String nome, String rg, int numeroApartamento) {
    this.nome = nome;
    this.rg = rg;
    this.numeroApartamento = numeroApartamento;
  }

  public String getNome() {
    return nome;
  }

  public String getRg() {
    return rg;
  }

  public int getNumeroApartamento() {
    return numeroApartamento;
  }
}
