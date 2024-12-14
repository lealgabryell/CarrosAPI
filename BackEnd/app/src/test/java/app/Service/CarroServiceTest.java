package app.Service;

import app.entity.Carro;
import app.entity.Marca;
import app.repository.CarroRepository;
import app.service.CarroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarroServiceTest {

    @Autowired
    CarroService carroService;

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
        c2.getMarca().setCnpj("cnpjNISSAN");
        c2.setNome("Kicks");
        c2.setValorFIPE(80000.00);
        lista.add(c1);
        lista.add(c2);

        when(carroRepository.findAll()).thenReturn(lista); //findAll
        when(carroRepository.findById(1L)).thenReturn(Optional.of(c1)); //findById
        when(carroRepository.findAcimaAno(2019)).thenReturn(lista); //findAcimaAno
        when(carroRepository.findAcimaValor(20000.00)).thenReturn(lista); //findAcimaValor
    }

    @Test
    void cenario01() {
        //Encontra a quantidade da lista teste
        List<Carro> lista = carroService.findAll();
        assertEquals(2, lista.size());
    }
    @Test
    void cenario02() {
        //Encontra o carro com id 1
        Carro carro = carroService.findById(1L);
        assertEquals("Uno Mile", carro.getNome());
    }
    @Test
    void cenario03() {
        //Encontra a quantidade da lista teste
        List<Carro> lista = carroService.findAcimaAno(2019);
        assertEquals("Kicks", lista.get(1).getNome());
    }
    @Test
    void cenario04(){
        //Encontra carros com vlor cima de 20000
        List<Carro> lista = carroService.findAcimaValor(20000.00);
        assertEquals("Kicks", lista.get(1).getNome());
    }

    //testes unitarios
    @Test
    public void cenario05(){
        boolean retorno = this.carroService.verificarNomeCarro("Jeep Compass", 2006);

        assertEquals(true, retorno);
    }
    @Test
    public void cenario06(){
        assertThrows(Exception.class, () -> {
           boolean retorno = this.carroService.verificarNomeCarro("Jeep Compass", 1995);
        });
    }
}
