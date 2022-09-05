package br.ufsm.csi.clinica.services;

import br.ufsm.csi.clinica.dao.PessoaDAO;
import br.ufsm.csi.clinica.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PessoaService {

    private PessoaDAO PessoaDAO;

    public PessoaService() {
        this.PessoaDAO = new PessoaDAO();
    }

    public ArrayList<Pessoa> getPessoas() {
        return this.PessoaDAO.getPessoas();
    }

    //public Pessoa getPessoaById(int id) { return this.PessoaDAO.getPessoaById(id); }

    public boolean cadastrarPessoa(Pessoa c){
        return this.PessoaDAO.cadastrarPessoa(c);
    }

   // public boolean atualizarPessoa(Pessoa c){
   //     return this.PessoaDAO.atualizarPessoa(c);
   // }

    public boolean deletarPessoa(int id){
        return this.PessoaDAO.excluirPessoa(id);
    }

}
