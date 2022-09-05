package br.ufsm.csi.clinica.services;

import br.ufsm.csi.clinica.dao.MedicoDAO;
import br.ufsm.csi.clinica.model.Medico;

import java.util.ArrayList;

public class MedicoService {
    private MedicoDAO MedicoDAO;

    public MedicoService() {
        this.MedicoDAO = new MedicoDAO();
    }

    public ArrayList<Medico> getMedicos() {
        return this.MedicoDAO.getMedicos();
    }

    public boolean cadastrarMedico(Medico medico){
        return this.MedicoDAO.cadastrarMedico(medico);
    }

    public boolean deletarMedico(int id) { return this.MedicoDAO.deletarMedico(id); }

    public boolean editarMedico(Medico medico) { return this.MedicoDAO.atualizarMedicos(medico); }

}
