package classes;

import exceptions.EntregaJaFoiRetiradaException;
import exceptions.NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

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

        assertEquals(idEntregaEsperado, idEntregaObtido);
    }

    @Test
    public void deveRetornarADescricaoDaEntregaCorretamente() {
        String descricaoEsperada = "Pacote grande e laranja";
        String descricaoObtida = entrega.getDescricao();

        assertEquals(descricaoEsperada, descricaoObtida);
    }

    @Test
    public void deveRetornarOOperadorDaEntregaCorretamente() {
        String nomeOperadorEsperado = "CA";
        String nomeOperadorObtido = entrega.getOperadorResponsavel().toString();

        assertEquals(nomeOperadorEsperado, nomeOperadorObtido);
    }

    @Test
    public void deveRetornarONumeroDoApartamentoDaEntregaCorretamente() {
        int nroApartamentoDeEntregaEsperado = 204;
        int nroApartamentoDeEntregaObtido = entrega.getNumeroApartamentoDestinatario();

        assertEquals(nroApartamentoDeEntregaEsperado, nroApartamentoDeEntregaObtido);
    }

    @Test
    public void deveRetornarAHoraDeRegistroCorretamente() {
        int horaEsperada = LocalDateTime.now().getHour();
        int horaObtida = entrega.getDataRegistro().getHour();

        assertEquals(horaEsperada, horaObtida);
    }

    @Test
    public void deveRetornarOMinutoDeRegistroCorretamente() {
        int minutosEsperado = LocalDateTime.now().getMinute();
        int minutosObtido = entrega.getDataRegistro().getMinute();

        assertEquals(minutosEsperado, minutosObtido);
    }

    @Test
    public void deveRetornarADataNoFormatoTextoCorretamente() {
        String dataFormatadaEsperada = DateFormater.getDataEmFormatoTexto(entrega.getDataRegistro());
        String dataFormatadaObtida = DateFormater.getDataEmFormatoTexto(LocalDateTime.now());

        assertEquals(dataFormatadaEsperada, dataFormatadaObtida);
    }

    @Test
    public void deveRetornarOHorarioNoFormatoTextoCorretamente() {
        String horarioFormatadoEsperada = DateFormater.getHorarioEmFormatoTexto(entrega.getDataRegistro());
        String horarioFormatadoObtida = DateFormater.getHorarioEmFormatoTexto(LocalDateTime.now());

        assertEquals(horarioFormatadoEsperada, horarioFormatadoObtida);
    }

    @Test
    public void deveSerPossivelRetirarUmaEntregaQuandoNumeroDoApartamentoDoMoradorQueVaiRetiraForIgualAoNumeroDoApartamentoDeEntrega()
            throws EntregaJaFoiRetiradaException, NumeroApartamentoDoMoradorQueVaiRetirarInvalidoException {
        entrega.retirarEntrega(morador);
        String nomeDoMoradorQueRetirouEsperado = "Marlon";
        String nomeDoMoradorQueRetirouObtido = entrega.getMoradorQueRetirou().getNome();

        assertEquals(nomeDoMoradorQueRetirouEsperado, nomeDoMoradorQueRetirouObtido);
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
    public void validarControleUltimoIdEntrega() {
        Entrega entrega = new Entrega(1, "Teste", new Operador("teste"), 123);

        assertEquals(2, Entrega.getUProximoIdEntrega());
    }
}