package main.java.classes;
import java.time.LocalDateTime;
import main.java.exceptions.*;

public class Entrega {
  private final int idEntrega;
  private final LocalDateTime dataRegistro;
  private LocalDateTime dataRetirada;
  private final String descricao;
  private final Operador operadorResponsavel;
  private Morador moradorQueRetirou;
  private final int numeroApartamentoDestinatario;

  public Entrega(int idEntrega, String descricao, Operador operadorResponsavel, int numeroApartamentoDestinatario) {
    this.idEntrega = idEntrega;
    this.dataRegistro = LocalDateTime.now();
    this.descricao = descricao;
    this.operadorResponsavel = operadorResponsavel;
    this.numeroApartamentoDestinatario = numeroApartamentoDestinatario;
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
}
