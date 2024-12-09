package app.Service;

import app.service.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CarroServiceTest {

    @Autowired
    CarroService carroService;

    @Test
    void cenario01(){
        List<Integer> lista = new ArrayList<>();

        lista.add(1);
        lista.add(4);
        lista.add(5);

        int retorno = this.carroService.soma(lista);
        assertEquals(10, retorno);
    }

    @Test
    void cenario02(){
        List<Integer> lista = new ArrayList<>();

        lista.add(null);
        lista.add(4);
        lista.add(5);

        assertThrows(Exception.class, ()-> {
            int retorno = this.carroService.soma(lista);
        });
    }
}
