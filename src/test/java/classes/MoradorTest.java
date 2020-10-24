package classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoradorTest {
  Morador morador;

  @Before
  public void setup() {
    morador = new Morador("Marlon", "2244610441", 204);
  }

  @Test
  public void deveCriarMoradorCorretamente() {
    Assert.assertNotNull(morador);
  }

  @Test
  public void deveRetornarONomeDoMoradorCorretamente() {
    String nomeEsperado = "Marlon";
    String nomeObtido = morador.getNome();

    Assert.assertEquals(nomeEsperado, nomeObtido);
  }

  @Test
  public void deveRetornarORgDoMoradorCorretamente() {
    String rgEsperado = "2244610441";
    String rgObtido = morador.getRg();

    Assert.assertEquals(rgEsperado, rgObtido);
  }

  @Test
  public void deveRetornarONumeroDoApartamentoDoMoradorCorretamente() {
    int nroApartamentoEsperado = 204;
    int nroApartamentoObtido = morador.getNumeroApartamento();

    Assert.assertEquals(nroApartamentoEsperado, nroApartamentoObtido);
  }
}