package app.service;

import app.entity.Carro;
import app.entity.Marca;
import app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    //REGRA DE NEGOCIO DE VALIDACAO DE NOME DE CARRO
    public boolean verificarNomeCarro(String nome, int ano) {
        if (nome.equals("Jeep Compass") && ano < 2006) {
            throw new RuntimeException();
        }
        return true;
    }

    //REGRA DE NEGOCIO DE VALIDACAO DE VALOR DOUBLE
    public boolean verificaValorDouble(Double valor){
        if(valor < 0){
            throw new RuntimeException();
        }
        return true;
    }

    //REGRA DE NEGOCIO DE VALIDACAO DE VALOR INTEGER
    public boolean verificaValorInt(int valor){
        if(valor < 0){
            throw new RuntimeException();
        }
        return true;
    }

    //REGRA DE NEGOCIO DE VALIDACAO DE NOME NA LISTA
    public boolean verificaNomeNaLista(List<Carro> lista, String nome){
        for(Carro c : lista){
            if(c.getNome().equalsIgnoreCase(nome)){
                throw new RuntimeException();
            }
        }
        return true;
    }

    //REGRA DE NEGOCIO DE VALIDACAO DE STRING VAZIA
    public boolean verificaStringVazia(String nome){
            if(nome.isEmpty()){
                throw new RuntimeException();
            }
        return true;
    }

    public String save(@RequestBody Carro carro) {

        this.verificarNomeCarro(carro.getNome(), carro.getAno());

        this.carroRepository.save(carro);
        return "Carro salvo com sucesso!";
    }

    public String update(@RequestBody Carro carro, @PathVariable long id) {

        this.verificarNomeCarro(carro.getNome(), carro.getAno());
        this.verificaStringVazia(carro.getNome());

        //Setando o ID de um objeto antes de salva-lo no BD garante que o SGBD
        // vai entender que esse carro ja existe no BD e ele apenas foi modificado.
        carro.setId(id);
        this.carroRepository.save(carro);
        return "Carro atualizado com sucesso!";
    }

    public String delete(@PathVariable long id) {
        this.carroRepository.deleteById(id);
        return "Carro deletado com sucesso!";
    }

    public List<Carro> findAll() {
        return this.carroRepository.findAll();
    }

    public Carro findById(@PathVariable long id) {
        return this.carroRepository.findById(id).get();
    }

    public List<Carro> findByNome(@RequestParam String nome) {
        this.verificaStringVazia(nome);

        return this.carroRepository.findByNome(nome);
    }

    public List<Carro> findByMarca(@RequestParam long idMarca) {
        Marca marca = new Marca();
        marca.setId(idMarca);
        return this.carroRepository.findByMarca(marca);
    }

    public List<Carro> findAcimaAno(@RequestParam int ano) {
        return this.carroRepository.findAcimaAno(ano);
    }

    public List<Carro> findAcimaValor(@RequestParam Double valor) {
        this.verificaValorDouble(valor);
        return this.carroRepository.findAcimaValor(valor);
    }

    public List<Carro> findAbaixoProprietarios(@RequestParam int nProprietarios) {
        this.verificaValorInt(nProprietarios);
        return this.carroRepository.findAbaixoProprietarios(nProprietarios);
    }
}
