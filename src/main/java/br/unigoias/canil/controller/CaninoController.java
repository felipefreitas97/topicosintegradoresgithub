
package br.unigoias.canil.controller;

import br.unigoias.canil.model.Canino;
import br.unigoias.canil.model.Raca;
import br.unigoias.canil.repository.CaninoRepository;
import br.unigoias.canil.repository.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caninos")
public class CaninoController {

    private final CaninoRepository caninoRepository;
    private final RacaRepository racaRepository;

    @Autowired
    public CaninoController(CaninoRepository caninoRepository, RacaRepository racaRepository) {
        this.caninoRepository = caninoRepository;
        this.racaRepository = racaRepository;
    }

    @PostMapping("/{racaId}")
    public ResponseEntity<Canino> cadastrarCanino(@PathVariable Integer racaId, @RequestBody Canino canino) {
        Optional<Raca> racaOptional = racaRepository.findById(racaId);
        if (racaOptional.isPresent()) {
            Raca raca = racaOptional.get();
            canino.setRaca(raca);
            Canino novoCanino = caninoRepository.save(canino);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCanino);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{racaId}")
    public ResponseEntity<List<Canino>> listarCaninosPorRaca(@PathVariable Integer racaId) {
        Optional<Raca> racaOptional = racaRepository.findById(racaId);
        if (racaOptional.isPresent()) {
            List<Canino> caninos = caninoRepository.findByRacaId(racaId);
            return ResponseEntity.ok().body(caninos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Canino>> listarTodosCaninos() {
        List<Canino> caninos = caninoRepository.findAll();
        return ResponseEntity.ok().body(caninos);
    }
}
