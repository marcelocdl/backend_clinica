package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.model.PlanoSaude;
import br.ufsm.csi.clinica.repository.PlanoSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planoSaude")
public class PlanoSaudeController {

    @Autowired
    private final PlanoSaudeRepository planoSaudeRepository;

    public PlanoSaudeController(PlanoSaudeRepository planoSaudeRepository) {
        this.planoSaudeRepository = planoSaudeRepository;
    }


    @GetMapping("/planos")
    public ResponseEntity<List<PlanoSaude>> getPlanos() {

        try {
            List<PlanoSaude> planos = new ArrayList<>();

            planos.addAll(planoSaudeRepository.findAll());

            if (planos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(planos, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoSaude> getTipo(@PathVariable("id") int id) {
        Optional<PlanoSaude> planoSaudeData = planoSaudeRepository.findById(id);

        return planoSaudeData.map(planoSaude -> new ResponseEntity<>(planoSaude, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PlanoSaude> cadastrarPlano(@RequestBody PlanoSaude plano) {
        PlanoSaude p = planoSaudeRepository.save(plano);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<PlanoSaude> editarPlano(@RequestBody PlanoSaude plano) {
        Optional<PlanoSaude> planoSaudeData = planoSaudeRepository.findById(plano.getId_plano_saude());

        if(planoSaudeData.isPresent()){
            PlanoSaude p = planoSaudeData.get();
            p.setNome(plano.getNome());
            p.setValor_consulta(plano.getValor_consulta());

            return new ResponseEntity<>(planoSaudeRepository.save(p), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity excluirPlanoSaude(@PathVariable("id") int id) {

        planoSaudeRepository.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
