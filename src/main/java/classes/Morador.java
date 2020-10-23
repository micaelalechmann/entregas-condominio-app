package main.java.classes;

public class Morador {
  private final String nome;
  private final String rg;
  private int nroApartamento;

  public Morador(String nome, String rg, int nroApartamento) {
    this.nome = nome;
    this.rg = rg;
    this.nroApartamento = nroApartamento;
  }

  public String getNome() {
    return nome;
  }

  public String getRg() {
    return rg;
  }

  public int getNroApartamento() {
    return nroApartamento;
  }
}
