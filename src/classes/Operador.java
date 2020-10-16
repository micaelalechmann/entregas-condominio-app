package classes;

public class Operador {
  private String nome;

  public Operador(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    String[] iniciaisNome = nome.split(" ");
    StringBuilder sb = new StringBuilder();

    for (String s : iniciaisNome) {
      sb.append(s);
    }

    return sb.toString();
  }
}
