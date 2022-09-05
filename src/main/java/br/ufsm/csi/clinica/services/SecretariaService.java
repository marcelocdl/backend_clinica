package br.ufsm.csi.clinica.services;

import br.ufsm.csi.clinica.dao.PessoaDAO;
import br.ufsm.csi.clinica.model.Pessoa;

import java.util.ArrayList;

public class SecretariaService {

    private PessoaDAO PessoaDAO;

    public SecretariaService() {
        this.PessoaDAO = new PessoaDAO();
    }

    public ArrayList<Pessoa> getSecretarias() {
        return this.PessoaDAO.getSecretarias();
    }

    public Boolean cadastrarSecretaria(Pessoa pessoa){
        return this.PessoaDAO.cadastrarPessoa(pessoa);
    }

    public Boolean deletarSecretaria(int id) { return this.PessoaDAO.excluirPessoa(id); }

    public Boolean atualizarSecretaria(Pessoa pessoa) { return this.PessoaDAO.atualizarPessoa(pessoa); }
}
