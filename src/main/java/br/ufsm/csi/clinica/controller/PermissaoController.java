package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.dao.PermissaoDAO;
import br.ufsm.csi.clinica.model.Permissao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {

    @GetMapping("/permissoes")
    public ResponseEntity<List<Permissao>> getTipos(){
        final PermissaoDAO permissaoDAO = new PermissaoDAO();

        try {
            List<Permissao> permissoes = new ArrayList<>();

            permissoes.addAll(permissaoDAO.getPermissoes());

            if(permissoes.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(permissoes, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permissao> getPermissao(@PathVariable("id") int id) {
        PermissaoDAO permissaoDAO = new PermissaoDAO();
        Permissao permissao = permissaoDAO.getPermissaoById(id);
        try {
            if (permissao == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(permissao, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
