package br.unigoias.canil.repository;

import br.unigoias.canil.model.Raca;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Integer> {
}
