package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.dao.PessoaDAO;
import br.ufsm.csi.clinica.model.Pessoa;
import br.ufsm.csi.clinica.services.SecretariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/secretaria")
public class SecretariaController {

    private SecretariaService secretariaService;

    public SecretariaController() {
        this.secretariaService = new SecretariaService();
    }

    @GetMapping("/secretarias")
    public ResponseEntity<List<Pessoa>> getSecretarias(){
        final PessoaDAO pessoaDAO = new PessoaDAO();

        try {
            List<Pessoa> pessoas = new ArrayList<>();

            pessoas.addAll(pessoaDAO.getSecretarias());

            if(pessoas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pessoas, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pessoa> cadastrarSecretaria(@RequestBody Pessoa pessoa) {
        if(secretariaService.cadastrarSecretaria(pessoa)){
            return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Pessoa> editarSecretaria(@RequestBody Pessoa pessoa){
        if (this.secretariaService.atualizarSecretaria(pessoa)) {
            return new ResponseEntity(pessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity excluirSecretaria(@PathVariable("id") int id){

        if (this.secretariaService.deletarSecretaria(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
