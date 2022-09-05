package br.ufsm.csi.clinica.services;

import br.ufsm.csi.clinica.dao.PlanoSaudeDAO;
import br.ufsm.csi.clinica.model.PlanoSaude;

import java.util.ArrayList;

public class PlanoSaudeService {

    private PlanoSaudeDAO PlanoSaudeDAO;

    public PlanoSaudeService() {
        this.PlanoSaudeDAO = new PlanoSaudeDAO();
    }

    public ArrayList<PlanoSaude> getPlanosSaude() {
        return this.PlanoSaudeDAO.getTodosPlanosSaude();
    }

    public PlanoSaude getPlanoSaude(int id) { return this.PlanoSaudeDAO.getPlanoById(id); }

    public Boolean cadastrarPlanoSaude(PlanoSaude plano){
        return this.PlanoSaudeDAO.cadastrarPlanoSaude(plano);
    }

    public Boolean deletarPlanoSaude(int id) { return this.PlanoSaudeDAO.deletarPlanoSaude(id); }

    public Boolean atualizarPlanoSaude(PlanoSaude plano) { return this.PlanoSaudeDAO.atualizarPlano(plano); }
}
