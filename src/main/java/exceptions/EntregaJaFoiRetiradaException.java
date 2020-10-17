package exceptions;

public class EntregaJaFoiRetiradaException extends Exception {

  public EntregaJaFoiRetiradaException(String nomeMorador) {
    super("Esta entraga já foi retirada pelo morador" + nomeMorador + '.');
  }
}