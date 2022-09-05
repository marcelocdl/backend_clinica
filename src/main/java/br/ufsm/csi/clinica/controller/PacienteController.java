package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.dao.PessoaDAO;
import br.ufsm.csi.clinica.model.Pessoa;
import br.ufsm.csi.clinica.services.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController() {
        this.pacienteService = new PacienteService();
    }

    @GetMapping("/pacientes")
    public ResponseEntity<List<Pessoa>> getPacientes(){
        final PessoaDAO pessoaDAO = new PessoaDAO();

        try {
            List<Pessoa> pessoas = new ArrayList<>();

            pessoas.addAll(pessoaDAO.getPacientes());

            if(pessoas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pessoas, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pessoa> cadastrarPaciente(@RequestBody Pessoa pessoa) {
        if(pacienteService.cadastrarPaciente(pessoa)){
            return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Pessoa> editarPaciente(@RequestBody Pessoa pessoa){
        if (this.pacienteService.atualizarPaciente(pessoa)) {
            return new ResponseEntity(pessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity excluirPaciente(@PathVariable("id") int id){

        if (this.pacienteService.deletarPaciente(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<Pessoa> getPacienteById(@PathVariable("id") int id){
        final PessoaDAO pessoaDAO = new PessoaDAO();

        try{
            Pessoa pac = new Pessoa();
            pac = pessoaDAO.getPessoaById(id);

            if(pac != null){
                return new ResponseEntity<>(pac, HttpStatus.OK);
            }

        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
