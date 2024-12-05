package app.service;

import app.entity.Carro;
import app.entity.Marca;
import app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
        List<Carro> lista = this.carroRepository.findAll();
        return lista;
    }

    public Carro findById(@PathVariable long id){
        Carro carro = this.carroRepository.findById(id).get();
        return carro;
    }

    public List<Carro> findByNome(String nome){
       return this.carroRepository.findByNome(nome);
    }
    public List<Carro> findByMarca(long idMarca){
        Marca marca = new Marca();
        marca.setId(idMarca);
       return this.carroRepository.findByMarca(marca);
    }

    public List<Carro> findAcimaAno(int ano){
       return this.carroRepository.findAcimaAno(ano);
    }
}
