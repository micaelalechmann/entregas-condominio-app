package classes;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CondominioTest {
    Condominio condominio;
    Operador operador;

    @Before
    public void setup(){
        condominio = new Condominio();
        operador = new Operador("Operador Teste");
    }

    @Test
    public void buscaEntregasPelaDescricao_retornaUmaDeDuasEntregas() {
        // arrange
        String descricaoEsperada = "Descricao Teste";
        String descricaoNaoEsperada = "Pacote";
        Entrega entrega1 = new Entrega(1, descricaoEsperada, operador, 1);
        Entrega entrega2 = new Entrega(2, descricaoNaoEsperada, operador, 2);
        List<Entrega> entregas = Arrays.asList(entrega1, entrega2);
        condominio.setEntregas(entregas);

        // act
        List<Entrega> resultado = condominio.buscaEntregasPelaDescricao(descricaoEsperada);

        // assert
        assertEquals(1, resultado.size());
        assertEquals(descricaoEsperada, resultado.get(0).getDescricao());
    }

    @Test
    public void buscaEntregasPelaDescricao_retornaUmaDeDuasEntregas_contendoDescricao() {
        // arrange
        String textoContido = "Teste";
        String descricaoEsperada = "Descricao " + textoContido;
        String descricaoNaoEsperada = "Pacote";
        Entrega entrega1 = new Entrega(1, descricaoEsperada, operador, 1);
        Entrega entrega2 = new Entrega(2, textoContido, operador, 2);
        Entrega entrega3 = new Entrega(3, descricaoNaoEsperada, operador, 3);
        List<Entrega> entregas = Arrays.asList(entrega1, entrega2, entrega3);

        condominio.setEntregas(entregas);

        // act
        List<Entrega> resultado = condominio.buscaEntregasPelaDescricao(textoContido);

        // assert
        assertEquals(2, resultado.size());
        assertEquals(descricaoEsperada, resultado.get(0).getDescricao());
        assertTrue(resultado.get(1).getDescricao().contains(textoContido));
    }

    @Test
    public void buscaEntregasPelaDescricao_retornaUmaDeDuasEntregas_contendoDescricao_ignoreCase() {
        // arrange
        String textoContido = "teste";
        String descricaoEsperada = "Descricao " + textoContido.toUpperCase();
        String descricaoNaoEsperada = "Pacote";
        Entrega entrega1 = new Entrega(1, descricaoEsperada, operador, 1);
        Entrega entrega2 = new Entrega(2, textoContido, operador, 2);
        Entrega entrega3 = new Entrega(3, descricaoNaoEsperada, operador, 3);
        List<Entrega> entregas = Arrays.asList(entrega1, entrega2, entrega3);

        condominio.setEntregas(entregas);

        // act
        List<Entrega> resultado = condominio.buscaEntregasPelaDescricao(textoContido);

        // assert
        assertEquals(2, resultado.size());
        assertEquals(descricaoEsperada, resultado.get(0).getDescricao());
        assertTrue(resultado.get(1).getDescricao().contains(textoContido));
    }

    @Test
    public void buscaEntregasPelaDescricao_retornaZeroEntregas() {
        // arrange
        String descricaoEsperada = "Descricao Teste";
        String descricaoNaoEsperada = "Pacote";
        Entrega entrega1 = new Entrega(1, descricaoNaoEsperada, operador, 2);

        List<Entrega> entregas = Collections.singletonList(entrega1);
        condominio.setEntregas(entregas);

        // act
        List<Entrega> resultado = condominio.buscaEntregasPelaDescricao(descricaoEsperada);

        // assert
        assertEquals(0, resultado.size());
    }

    @Test
    public void validaCadastroEntrega() {

        Entrega entrega1 = new Entrega(
                1, "Teste", new Operador("teste"), 123);

        condominio.cadastrarEntrega(entrega1);

        assertTrue(condominio.getEntregas().contains(entrega1));
    }

}