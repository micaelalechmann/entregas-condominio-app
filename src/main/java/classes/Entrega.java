package classes;

import exceptions.EntregaJaFoiRetiradaException;
import exceptions.NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException;
import java.time.LocalDateTime;

public class Entrega {
  private final int idEntrega;
  private final LocalDateTime dataRegistro;
  private LocalDateTime dataRetirada;
  private final String descricao;
  private final Operador operadorResponsavel;
  private Morador moradorQueRetirou;
  private final int numeroApartamentoDestinatario;
  private static int ultimoIdEntrega;

  public Entrega(int idEntrega, String descricao, Operador operadorResponsavel, int numeroApartamentoDestinatario) {
    this.idEntrega = idEntrega;
    this.dataRegistro = LocalDateTime.now();
    this.descricao = descricao;
    this.operadorResponsavel = operadorResponsavel;
    this.numeroApartamentoDestinatario = numeroApartamentoDestinatario;
    ultimoIdEntrega = idEntrega;
  }

  public int getIdEntrega() {
    return idEntrega;
  }

  public LocalDateTime getDataRegistro() {
    return dataRegistro;
  }

  public LocalDateTime getDataRetirada() {
    return dataRetirada;
  }

  public String getDescricao() {
    return descricao;
  }

  public Operador getOperadorResponsavel() {
    return operadorResponsavel;
  }

  public Morador getMoradorQueRetirou() {
    return moradorQueRetirou;
  }

  public int getNumeroApartamentoDestinatario() {
    return numeroApartamentoDestinatario;
  }

  public void retirarEntrega(Morador moradorQueVaiRetirar)
      throws EntregaJaFoiRetiradaException, NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException {
    if(moradorQueRetirou != null)
      throw new EntregaJaFoiRetiradaException(moradorQueRetirou.getNome());

    if(moradorQueVaiRetirar.getNumeroApartamento() != this.numeroApartamentoDestinatario)
      throw new NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException(this.numeroApartamentoDestinatario);

    this.dataRetirada = LocalDateTime.now();
    this.moradorQueRetirou = moradorQueVaiRetirar;
  }

  public static int getUltimoIdEntrega() {
    return ultimoIdEntrega;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("Identificação: ");
    builder.append(this.idEntrega);
    builder.append(" - Descrição: ");
    builder.append(this.descricao);

    return builder.toString();
  }
}
