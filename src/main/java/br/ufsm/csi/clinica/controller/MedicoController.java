package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.services.MedicoService;

import br.ufsm.csi.clinica.dao.MedicoDAO;
import br.ufsm.csi.clinica.model.Medico;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private MedicoService MedicoService;

    public MedicoController(){
        this.MedicoService = new MedicoService();
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<Medico>> getMedicos(){
        final MedicoDAO medicoDAO = new MedicoDAO();

        try {
            List<Medico> medicos = new ArrayList<>();

            medicos.addAll(medicoDAO.getMedicos());

            if(medicos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(medicos, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody Medico medico) {
        if(MedicoService.cadastrarMedico(medico)){
            return new ResponseEntity<>(medico, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity deletarMedico(@PathVariable("id") int id){
        if (this.MedicoService.deletarMedico(id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Medico> atualizarMedico(@RequestBody Medico medico){
        if(this.MedicoService.editarMedico(medico)){
            return new ResponseEntity<>(medico, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable("id") int id){
        final MedicoDAO medicoDAO = new MedicoDAO();

        try{
            Medico med = new Medico();
            med = medicoDAO.getMedicoById(id);

            if(med != null){
                return new ResponseEntity<>(med, HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
