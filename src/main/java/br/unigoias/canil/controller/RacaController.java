package br.unigoias.canil.controller;

import br.unigoias.canil.model.Raca;
import br.unigoias.canil.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/racas")
public class RacaController {

    private final RacaRepository racaRepository;

    @Autowired
    public RacaController(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    @PostMapping("")
    public ResponseEntity<Raca> cadastrarRaca(@RequestBody Raca raca) {
        Raca novaRaca = racaRepository.save(raca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRaca);
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<Raca> buscarRacaPorId(@PathVariable Integer id) {
        Optional<Raca> racaOptional = racaRepository.findById(id);
        return racaOptional.map(raca -> ResponseEntity.ok().body(raca)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<Raca>> listarRacas() {
        List<Raca> racas = racaRepository.findAll();
        return ResponseEntity.ok().body(racas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRaca(@PathVariable Integer id) {
        Optional<Raca> racaOptional = racaRepository.findById(id);
        if (racaOptional.isPresent()) {
            racaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raca> atualizarRaca(@PathVariable Integer id, @RequestBody Raca racaAtualizada) {
        Optional<Raca> racaOptional = racaRepository.findById(id);
        if (racaOptional.isPresent()) {
            Raca raca = racaOptional.get();
            raca.setNome(racaAtualizada.getNome());
            Raca racaAtualizadaBanco = racaRepository.save(raca);
            return ResponseEntity.ok().body(racaAtualizadaBanco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}