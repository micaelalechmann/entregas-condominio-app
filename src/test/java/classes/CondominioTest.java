package classes;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        Assert.assertEquals(1, resultado.size());
        Assert.assertEquals(descricaoEsperada, resultado.get(0).getDescricao());
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
        Assert.assertEquals(2, resultado.size());
        Assert.assertEquals(descricaoEsperada, resultado.get(0).getDescricao());
        Assert.assertTrue(resultado.get(1).getDescricao().contains(textoContido));
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
        Assert.assertEquals(2, resultado.size());
        Assert.assertEquals(descricaoEsperada, resultado.get(0).getDescricao());
        Assert.assertTrue(resultado.get(1).getDescricao().contains(textoContido));
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
        Assert.assertEquals(0, resultado.size());
    }

    @Test
    public void validaCadastroEntrega() {

        Entrega entrega1 = new Entrega(
                1, "Teste", new Operador("teste"), 123);

        condominio.cadastrarEntrega(entrega1);

        Assert.assertTrue(condominio.getEntregas().contains(entrega1));
    }

    @Test
    public void buscaEntregasNaoRetiradas_todasRetiradas() {
        // arrange
        Entrega entrega1 = new Entrega(1, "", operador, 2);
        entrega1.setDataRetirada(LocalDateTime.now());
        Entrega entrega2 = new Entrega(2, "", operador, 2);
        entrega2.setMoradorQueRetirou(new Morador("Diego Souza", "999999", 1));

        List<Entrega> entregas = Arrays.asList(entrega1, entrega2);
        condominio.setEntregas(entregas);

        // act
        List<Entrega> resultado = condominio.buscaEntregasNaoRetiradas();

        // assert
        Assert.assertEquals(0, resultado.size());
    }

    @Test
    public void buscaEntregasNaoRetiradas_nenhumaRetirada() {
        // arrange
        Entrega entrega1 = new Entrega(1, "", operador, 1);
        Entrega entrega2 = new Entrega(2, "", operador, 2);

        List<Entrega> entregas = Arrays.asList(entrega1, entrega2);
        condominio.setEntregas(entregas);

        // act
        List<Entrega> resultado = condominio.buscaEntregasNaoRetiradas();

        // assert
        Assert.assertEquals(2, resultado.size());
    }

    @Test
    public void buscaEntregasNaoRetiradas_contendoRetiradasEnaoRetiradas() {
        // arrange
        Entrega entrega1 = new Entrega(1, "", operador, 1);
        entrega1.setDataRetirada(LocalDateTime.now());
        Entrega entrega2 = new Entrega(2, "", operador, 2);

        List<Entrega> entregas = Arrays.asList(entrega1, entrega2);
        condominio.setEntregas(entregas);

        // act
        List<Entrega> resultado = condominio.buscaEntregasNaoRetiradas();

        // assert
        Assert.assertEquals(1, resultado.size());
    }
}