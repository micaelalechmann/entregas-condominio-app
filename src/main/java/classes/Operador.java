package main.java.classes;

public class Operador {
  private final String nome;

  public Operador(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    String[] iniciaisNome = nome.split(" ");
    StringBuilder sb = new StringBuilder();

    for (String s : iniciaisNome) {
      sb.append(s.toUpperCase().charAt(0));
    }

    return sb.toString();
  }
}
