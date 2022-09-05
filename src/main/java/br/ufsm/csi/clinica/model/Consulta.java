package br.ufsm.csi.clinica.model;

import java.util.Base64;

public class Consulta {
    private int id_consulta;
    private Pessoa paciente;
    private Medico medico;
    private String dt_consulta;
    private Base64 atestado;

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public Pessoa getPaciente() {
        return paciente;
    }

    public void setPaciente(Pessoa paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getDt_consulta() {
        return dt_consulta;
    }

    public void setDt_consulta(String dt_consulta) {
        this.dt_consulta = dt_consulta;
    }
}
