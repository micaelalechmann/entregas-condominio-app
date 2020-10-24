package classes;

import classes.Operador;
import org.junit.Assert;
import org.junit.Test;

public class OperadorTest {
  @Test
  public void deveCriarOperadorCorretamente() {
    Operador op = new Operador("Marlon Saldanha");

    Assert.assertNotNull(op);
  }

  @Test
  public void deveCriarOperadorComAsIniciaisMS() {
    Operador op = new Operador("Marlon Saldanha");
    String iniciaisEsperada = "MS";
    String iniciaisObtida = op.toString();

    Assert.assertEquals(iniciaisEsperada, iniciaisObtida);
  }

  @Test
  public void deveRetornarIniciaisMaiusculasQuandoNomeOperadorForMinusculo() {
    Operador op = new Operador("marlon saldanha");
    String iniciaisEsperada = "MS";
    String iniciaisObtida = op.toString();

    Assert.assertEquals(iniciaisEsperada, iniciaisObtida);
  }
}