package classes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GeradorIdTest {
        GeradorId geradorId;

        @Before
        public void setup() {
            geradorId = new GeradorId();
        }

        @Test
        public void deveRetornarPrimeiroId() {
            int idEsperado = 1;
            int idObtido = geradorId.getProximoId();

            Assert.assertEquals(idEsperado, idObtido);
        }

        @Test
        public void deveRetornarIdsSequencialmente() {
            int primeiroIdEsperado = 1;
            int segundoIdEsperado = 2;
            int terceiroIdEsperado = 3;

            int primeiroIdObtido = geradorId.getProximoId();
            int segundoIdObtido = geradorId.getProximoId();
            int terceiroIdObtido = geradorId.getProximoId();

            Assert.assertEquals(primeiroIdEsperado, primeiroIdObtido);
            Assert.assertEquals(segundoIdEsperado, segundoIdObtido);
            Assert.assertEquals(terceiroIdEsperado, terceiroIdObtido);
        }

}
