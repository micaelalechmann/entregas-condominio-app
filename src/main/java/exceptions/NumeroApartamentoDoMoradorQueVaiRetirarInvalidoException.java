package main.java.exceptions;

public class NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException extends Exception {

  public NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException(int nroApartamentoDestinatario) {
    super("Morador inválido. Esta entrega está registrada para o morador do apartamento " + nroApartamentoDestinatario + '.');
  }
}
