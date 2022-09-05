package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.dao.TipoDAO;
import br.ufsm.csi.clinica.model.Tipo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tipo")
public class TipoController {

    @GetMapping("/tipos")
    public ResponseEntity<List<Tipo>> getTipos(){
        final TipoDAO tipoDAO = new TipoDAO();

        try {
            List<Tipo> tipos = new ArrayList<>();

            tipos.addAll(tipoDAO.getTodosTipos());

            if(tipos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tipos, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Tipo> cadastrarTipo(@RequestBody Tipo tipo){
        TipoDAO tipoDAO = new TipoDAO();
        //Tipo tipo = new Tipo(descr_tipo);

        try {
            if(tipoDAO.cadastrarTipo(tipo)){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> getTipo(@PathVariable("id") int id){
        TipoDAO tipoDAO = new TipoDAO();
        Tipo tipo = tipoDAO.getTipoById(id);
        try {
            if(tipo == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(tipo, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
