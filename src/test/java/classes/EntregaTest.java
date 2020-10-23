package test.java.classes;


import java.time.LocalDateTime;
import java.util.List;

import main.java.classes.*;
import main.java.exceptions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;

import org.junit.Assert;

import main.java.classes.Operador;
import main.java.classes.Morador;
import main.java.classes.Entrega;
import main.java.classes.DateFormater;
import main.java.exceptions.EntregaJaFoiRetiradaException;
import main.java.exceptions.NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException;

public class EntregaTest {
  Operador op;
  Morador morador;
  Entrega entrega;

  @Before
  public void setup() {
    op = new Operador("Carlos Almeida");
    morador = new Morador("Marlon", "2244610441", 204);
    entrega = new Entrega(1, "Pacote grande e laranja", op, 204);
  }

  @Test
  public void deveCriarUmaEntregaCorretamente() {
    Assert.assertNotNull(entrega);
  }

  @Test
  public void deveRetornarOIdentificadorDaEntregaCorretamente() {
    int idEntregaEsperado = 1;
    int idEntregaObtido = entrega.getIdEntrega();

    Assert.assertEquals(idEntregaEsperado, idEntregaObtido);
  }

  @Test
  public void deveRetornarADescricaoDaEntregaCorretamente() {
    String descricaoEsperada = "Pacote grande e laranja";
    String descricaoObtida = entrega.getDescricao();

    Assert.assertEquals(descricaoEsperada, descricaoObtida);
  }

  @Test
  public void deveRetornarOOperadorDaEntregaCorretamente() {
    String nomeOperadorEsperado = "CA";
    String nomeOperadorObtido = entrega.getOperadorResponsavel().toString();

    Assert.assertEquals(nomeOperadorEsperado, nomeOperadorObtido);
  }

  @Test
  public void deveRetornarONumeroDoApartamentoDaEntregaCorretamente() {
    int nroApartamentoDeEntregaEsperado = 204;
    int nroApartamentoDeEntregaObtido = entrega.getNumeroApartamentoDestinatario();

    Assert.assertEquals(nroApartamentoDeEntregaEsperado, nroApartamentoDeEntregaObtido);
  }

  @Test
  public void deveRetornarAHoraDeRegistroCorretamente() {
    int horaEsperada = LocalDateTime.now().getHour();
    int horaObtida = entrega.getDataRegistro().getHour();

    Assert.assertEquals(horaEsperada, horaObtida);
  }

  @Test
  public void deveRetornarOMinutoDeRegistroCorretamente() {
    int minutosEsperado = LocalDateTime.now().getMinute();
    int minutosObtido = entrega.getDataRegistro().getMinute();

    Assert.assertEquals(minutosEsperado, minutosObtido);
  }

  @Test
  public void deveRetornarADataNoFormatoTextoCorretamente() {
    String dataFormatadaEsperada = DateFormater.getDataEmFormatoTexto(entrega.getDataRegistro());
    String dataFormatadaObtida = DateFormater.getDataEmFormatoTexto(LocalDateTime.now());

    Assert.assertEquals(dataFormatadaEsperada, dataFormatadaObtida);
  }

  @Test
  public void deveRetornarOHorarioNoFormatoTextoCorretamente() {
    String horarioFormatadoEsperada = DateFormater.getHorarioEmFormatoTexto(entrega.getDataRegistro());
    String horarioFormatadoObtida = DateFormater.getHorarioEmFormatoTexto(LocalDateTime.now());

    Assert.assertEquals(horarioFormatadoEsperada, horarioFormatadoObtida);
  }

  @Test
  public void deveSerPossivelRetirarUmaEntregaQuandoNumeroDoApartamentoDoMoradorQueVaiRetiraForIgualAoNumeroDoApartamentoDeEntrega()
          throws EntregaJaFoiRetiradaException, NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException {
    entrega.retirarEntrega(morador);
    String nomeDoMoradorQueRetirouEsperado = "Marlon";
    String nomeDoMoradorQueRetirouObtido = entrega.getMoradorQueRetirou().getNome();

    Assert.assertEquals(nomeDoMoradorQueRetirouEsperado, nomeDoMoradorQueRetirouObtido);
  }

  @Test(expected = EntregaJaFoiRetiradaException.class)
  public void naoDeveSerPossivelRetirarEntregaQuandoEntregaJaEstiverSidoRetirada()
          throws EntregaJaFoiRetiradaException, NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException {
    entrega.retirarEntrega(morador);
    entrega.retirarEntrega(morador);
  }

  @Test(expected = NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException.class)
  public void naoDeveSerPossivelRetirarEntregaQuandoNumeroDoApartamentoDeQuemVaiRetirarForDiferenteDoNumeroApartamentoDoDestinatario()
          throws EntregaJaFoiRetiradaException, NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException {
    morador = new Morador("Marlon", "2244610441", 345);
    entrega.retirarEntrega(morador);
  }

  @Test
  public void entregaNaoFoiRetirada() {
    // arrange
    Entrega entrega1 = new Entrega(1, "", new Operador("Operador Teste"), 1);
    // act
    Boolean resultado = entrega1.entregaFoiRetirada();
    // assert
    Assert.assertFalse(resultado);
  }
  @Test
  public void entregaFoiRetirada_comDataDeRetirada() {
    // arrange
    Entrega entrega1 = new Entrega(1, "", new Operador("Operador Teste"), 1);
    entrega1.setDataRetirada(LocalDateTime.now());
    // act
    Boolean resultado = entrega1.entregaFoiRetirada();
    // assert
    Assert.assertTrue(resultado);
  }
  @Test
  public void entregaFoiRetirada_comMoradorQueRetirou() {
    // arrange
    Entrega entrega1 = new Entrega(1, "", new Operador("Operador Teste"), 1);
    entrega1.setMoradorQueRetirou(new Morador("Diego Souza", "999999", 1));
    // act
    Boolean resultado = entrega1.entregaFoiRetirada();
    // assert
    Assert.assertTrue(resultado);
  }
  @Test
  public void entregaFoiRetirada_ambosRequisitos() {
    // arrange
    Entrega entrega1 = new Entrega(1, "", new Operador("Operador Teste"), 1);
    entrega1.setDataRetirada(LocalDateTime.now());
    entrega1.setMoradorQueRetirou(new Morador("Diego Souza", "999999", 1));
    // act
    Boolean resultado = entrega1.entregaFoiRetirada();
    // assert
    Assert.assertTrue(resultado);
  }
  
    @Test
    public void validarControleUltimoIdEntrega() {
        Entrega entrega = new Entrega(1, "Teste", new Operador("teste"), 123);

        Assert.assertEquals(2, Entrega.getUProximoIdEntrega());
    }
}