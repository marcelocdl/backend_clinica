package br.ufsm.csi.clinica.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @CrossOrigin(origins = "*")
    @GetMapping("/teste")
    public String getVagas(){
        return "TESTE";
    }

    /*public String testeJwt(){
        return "Feitoo";
    } */

}
