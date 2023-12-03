package br.unigoias.canil.repository;

import br.unigoias.canil.model.Canino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface CaninoRepository extends JpaRepository<Canino, Integer> {
    List<Canino> findByRacaId(Integer raca);
}
