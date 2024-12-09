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

    public String save(@RequestBody Carro carro){
        this.carroRepository.save(carro);
        return "Carro salvo com sucesso!";
    }

    public String update(@RequestBody Carro carro, @PathVariable long id){
        //Setando o ID de um objeto antes de salva-lo no BD garante que o SGBD
        // vai entender que esse carro ja existe no BD e ele apenas foi modificado.
        carro.setId(id);
        this.carroRepository.save(carro);
        return "Carro atualizado com sucesso!";
    }

    public String delete(@PathVariable long id){
        this.carroRepository.deleteById(id);
        return "Carro deletado com sucesso!";
    }

    public List<Carro> findAll(){
        return this.carroRepository.findAll();
    }

    public Carro findById(@PathVariable long id){
        return this.carroRepository.findById(id).get();
    }

    public List<Carro> findByNome(@RequestParam String nome){
       return this.carroRepository.findByNome(nome);
    }

    public List<Carro> findByMarca(@RequestParam long idMarca){
        Marca marca = new Marca();
        marca.setId(idMarca);
       return this.carroRepository.findByMarca(marca);
    }

    public List<Carro> findAcimaAno(@RequestParam int ano){
       return this.carroRepository.findAcimaAno(ano);
    }

    public List<Carro> findAcimaValor(@RequestParam Double valor){
       return this.carroRepository.findAcimaValor(valor);
    }
    public List<Carro> findAbaixoProprietarios(@RequestParam int nProprietarios){
     return this.carroRepository.findAbaixoProprietarios(nProprietarios);
    }
}
