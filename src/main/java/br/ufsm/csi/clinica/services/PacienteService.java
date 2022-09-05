package br.ufsm.csi.clinica.services;

import br.ufsm.csi.clinica.dao.PessoaDAO;
import br.ufsm.csi.clinica.model.Pessoa;

import java.util.ArrayList;

public class PacienteService {
    private PessoaDAO PessoaDAO;

    public PacienteService() {
        this.PessoaDAO = new PessoaDAO();
    }

    public ArrayList<Pessoa> getPacientes() {
        return this.PessoaDAO.getPacientes();
    }

    public Boolean cadastrarPaciente(Pessoa pessoa){
        return this.PessoaDAO.cadastrarPessoa(pessoa);
    }

    public Boolean deletarPaciente(int id) { return this.PessoaDAO.excluirPessoa(id); }

    public Boolean atualizarPaciente(Pessoa pessoa) { return this.PessoaDAO.atualizarPessoa(pessoa); }
}
