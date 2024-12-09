package app.repository;

import app.entity.Carro;
import app.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    public List<Carro> findByNome(String nome);

    public List<Carro> findByMarca(Marca marca);

    @Query("FROM Carro c WHERE c.ano > :ano")
    public List<Carro> findAcimaAno(int ano);

    @Query("FROM Carro c WHERE c.valorFIPE >= :valor")
    public List<Carro> findAcimaValor(Double valor);

    @Query("SELECT c FROM Carro c WHERE (SELECT COUNT(p) FROM c.proprietarios p) < :nProprietarios")
    public List<Carro> findAbaixoProprietarios(int nProprietarios);
}
