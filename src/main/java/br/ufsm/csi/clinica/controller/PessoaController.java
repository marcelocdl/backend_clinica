package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.dao.PessoaDAO;
import br.ufsm.csi.clinica.model.Pessoa;
import br.ufsm.csi.clinica.services.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController() {
        this.pessoaService = new PessoaService();
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> getPessoas(){
        final PessoaDAO pessoaDAO = new PessoaDAO();

        try {
            List<Pessoa> pessoas = new ArrayList<>();

            pessoas.addAll(pessoaDAO.getPessoas());

            if(pessoas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pessoas, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@GetMapping("/cliente/{id}")
    //public Pessoa getCliente(@PathVariable("id") int id){
    //    return this.clienteService.getClienteById(id);
    //}

    /*
    public String cadastrarCliente(@RequestParam(value = "nome") String nome,
                                   @RequestParam(value = "cpf") String cpf){
     */

    @PostMapping("/cadastrar")
    public String cadastrarPessoa(@RequestBody Pessoa pessoa){
        //Cliente c = new Cliente(nome, cpf);

        if(this.pessoaService.cadastrarPessoa(pessoa)){
            return "Cadastrado com sucesso";
        }else {
            return "Erro ao cadastrar";
        }

        //return new ClienteDAO().cadastrarCliente(new Cliente(nome, cpf));
    }

/*    @PutMapping("/editar/{id}")
    public String atualizarPessoa(@RequestBody Pessoa pessoa){

        //Cliente c = new Cliente(id, nome, cpf);
        if (this.pessoaService.atualizarPessoa(pessoa)) {
            return "Atualizado com sucesso";
        } else {
            return "Erro ao atualizar";
        }

        //return new ClienteDAO().atualizarCliente(new Cliente(id, nome, cpf));
    }

    @DeleteMapping("deletar/{id}")
    public String excluirPessoa(@PathVariable("id") int id){

        if (this.pessoaService.deletarPessoa(id)){
            return "Deletado com sucesso";
        }else{
            return "Erro ao deletar";
        }
    }
 */
}
