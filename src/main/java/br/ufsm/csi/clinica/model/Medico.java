package br.ufsm.csi.clinica.model;

public class Medico extends Pessoa{

    private int id_medico;
    private String num_crm;
    private String especialidade;

    public Medico() {
    }

    public Medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNum_crm() {
        return num_crm;
    }

    public void setNum_crm(String num_crm) {
        this.num_crm = num_crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}
