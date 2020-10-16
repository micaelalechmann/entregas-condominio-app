package classes;

import exceptions.EntregaJaFoiRetiradaException;
import exceptions.NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException;
import java.util.Calendar;

public class Entrega {
  private final int idEntrega;
  private final Calendar dataRegistro;
  private Calendar dataRetirada;
  private final String descricao;
  private final Operador operadorResponsavel;
  private Morador moradorQueRetirou;
  private final int nroApartamentoDestinatario;

  public Entrega(int idEntrega, String descricao, Operador operadorResponsavel, int nroApartamentoDestinatario) {
    this.idEntrega = idEntrega;
    this.dataRegistro = Calendar.getInstance();
    this.descricao = descricao;
    this.operadorResponsavel = operadorResponsavel;
    this.nroApartamentoDestinatario = nroApartamentoDestinatario;
  }

  public int getIdEntrega() {
    return idEntrega;
  }

  public Calendar getDataRegistro() {
    return dataRegistro;
  }

  public Calendar getDataRetirada() {
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

  public int getNroApartamentoDestinatario() {
    return nroApartamentoDestinatario;
  }

  public void retirarEntrega(Morador moradorQueVaiRetirar)
      throws EntregaJaFoiRetiradaException, NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException {
    if(moradorQueRetirou != null)
      throw new EntregaJaFoiRetiradaException(moradorQueRetirou.getNome());

    if(moradorQueVaiRetirar.getNroApartamento() != this.nroApartamentoDestinatario)
      throw new NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException(this.nroApartamentoDestinatario);

    dataRetirada = Calendar.getInstance();
    moradorQueRetirou = moradorQueVaiRetirar;
  }
}
