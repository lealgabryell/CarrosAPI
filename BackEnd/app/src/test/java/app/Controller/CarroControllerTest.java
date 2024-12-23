package app.Controller;

import app.controller.CarroController;
import app.entity.Carro;
import app.entity.Marca;
import app.entity.Proprietario;
import app.repository.CarroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CarroControllerTest {

    @Autowired
    CarroController carroController;

    @MockitoBean
    CarroRepository carroRepository;

    @BeforeEach
    void setup() {
        List<Carro> lista = new ArrayList<>();
        Carro c1 = new Carro();
        c1.setAno(2009);
        c1.setMarca(new Marca());
        c1.getMarca().setNome("Fiat");
        c1.getMarca().setCnpj("cnpjFIAT");
        c1.setNome("Uno Mile");
        c1.setValorFIPE(15000.00);


        Carro c2 = new Carro();
        c2.setAno(2020);
        c2.setMarca(new Marca());
        c2.getMarca().setNome("Nissan");
        c2.getMarca().setId(2);
        c2.getMarca().setCnpj("cnpjNISSAN");
        c2.setNome("Kicks");
        c2.setValorFIPE(80000.00);
        c2.setProprietarios(new ArrayList<>());
        c2.getProprietarios().add(new Proprietario());
        c2.getProprietarios().get(0).setNome("Maria");

        lista.add(c1);
        lista.add(c2);

        //utiliza mock para que nao haja conexao com banco de dados
        when(carroRepository.findAll()).thenReturn(lista); //findAll
        when(carroRepository.findById(1L)).thenReturn(Optional.of(c1)); //findById
        when(carroRepository.findAcimaValor(50000.00D)).thenReturn(lista); //findAcimaValor
        when(carroRepository.findAbaixoProprietarios(1)).thenReturn(lista); //findByMarca
        when(carroRepository.save(new Carro())).thenReturn(c1); //findByMarca
        when(carroRepository.findByNome("Kicks")).thenReturn(lista); //findByNome
        when(carroRepository.findByNome("")).thenReturn(null); //findByNome
        //OBS.: So se mocka testes que cheguem no repository, ou seja, so a
        // classe repository deve ser mockada
    }

    @Test
    void findAll_retornaOK() {
        //Teste de integracao do metodo findAll() para saber o status http do retorno
        //COM MOCK/MOCKITO
        ResponseEntity<List<Carro>> retorno = this.carroController.findAll();

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void findAll_retornaTamanhoDaLista_2() {
        //Teste de integracao do metodo findAll() para saber o tamanho da lista de retorno
        //COM MOCK/MOCKITO
        ResponseEntity<List<Carro>> retorno = this.carroController.findAll();

        assertEquals(2, retorno.getBody().size());
    }

    @Test
    void findById_recebe1_retornaOK() {
        //Teste de integracao do metodo findById() para saber o status http do retorno
        //COM MOCK/MOCKITO
        ResponseEntity<Carro> retorno = this.carroController.findById(1L);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void findById_recebe1_retornaUnoMile() {
        //Teste de integracao do metodo findById() para saber qual objeto do retorno
        //COM MOCK/MOCKITO
        ResponseEntity<Carro> retorno = this.carroController.findById(1L);

        assertEquals("Uno Mile", retorno.getBody().getNome());
    }

    @Test
    void findById_recebeLongNegativo_retornaBadRequest() {
        //Teste de integracao do metodo findById() para saber qual objeto do retorno
        //COM MOCK/MOCKITO
        ResponseEntity<Carro> retorno = this.carroController.findById(-1L);

        assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    }

    @Test
    void findByNome_recebeStringVazia_retornaBadRequest() {
        ResponseEntity<List<Carro>> retorno = this.carroController.findByNome("");

        assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    }

    @Test
    void findByNome_recebeKicks_retornaKicks() {
        ResponseEntity<List<Carro>> retorno = this.carroController.findByNome("Kicks");

        assertEquals("Kicks", retorno.getBody().get(1).getNome());
    }

    @Test
    void findAcimaValor_recebe5000_retornaOK() {
        ResponseEntity<List<Carro>> retorno = this.carroController.findAcimaValor(50000.00D);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void findAcimaValor_recebe5000_retornaKicks() {
        ResponseEntity<List<Carro>> retorno = this.carroController.findAcimaValor(50000.00);

        assertEquals("Kicks", retorno.getBody().get(1).getNome());
    }

    @Test
    void findAcimaValor_recebeDoubleNegativo_retornaBadRequest() {
        ResponseEntity<List<Carro>> retorno = this.carroController.findAcimaValor(-1D);

        assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    }

    @Test
    void findAbaixoProprietarios_recebeInteiroNegativ_retornaBadRequest() {
        ResponseEntity<List<Carro>> retorno = this.carroController.findAbaixoProprietarios(-1);

        assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    }

    @Test
    void findAbaixoProprietarios_recebeInteiro1_retornaOK() {
        ResponseEntity<List<Carro>> retorno = this.carroController.findAbaixoProprietarios(1);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void findAbaixoProprietarios_recebeInteiro1_retornaMaria() {
        ResponseEntity<List<Carro>> retorno = this.carroController.findAbaixoProprietarios(1);

        assertEquals("Maria", retorno.getBody().get(1).getProprietarios().get(0).getNome());
    }

    @Test
    void save_recebeCarro_retornaCREATED() {
        Carro c1 = new Carro();
        c1.setAno(2009);
        c1.setMarca(new Marca());
        c1.getMarca().setNome("Fiat");
        c1.getMarca().setCnpj("cnpjFIAT");
        c1.setNome("Uno Mile");
        c1.setValorFIPE(15000.00);


        ResponseEntity<String> retorno = this.carroController.save(c1);

        assertEquals(HttpStatus.CREATED, retorno.getStatusCode());
    }

    @Test
    void save_recebeCarroNull_retornaBadRequest() {
        Carro c1 = new Carro(); //CARRO COM EMPTY BODY GERA NULLPOINTER E LEVA O METODO AO CATCH(BAD REQUEST)
        ResponseEntity<String> retorno = this.carroController.save(c1);

        assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    }

}