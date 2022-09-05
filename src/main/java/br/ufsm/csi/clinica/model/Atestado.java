package br.ufsm.csi.clinica.model;

public class Atestado {

    int id_consulta;
    String nome_paciente;
    String cpf_paciente;
    String dt_consulta;
    String qtd_dia;
    String dt_atual;
    String nome_medico;
    String num_crm;

    public Atestado() {
    }

    public Atestado(int id_consulta, String nome_paciente, String cpf_paciente, String dt_consulta, String dt_atual, String nome_medico, String num_crm) {
        this.id_consulta = id_consulta;
        this.nome_paciente = nome_paciente;
        this.cpf_paciente = cpf_paciente;
        this.dt_consulta = dt_consulta;
        this.dt_atual = dt_atual;
        this.nome_medico = nome_medico;
        this.num_crm = num_crm;
    }

    public Atestado(int id_consulta, String nome_paciente, String cpf_paciente, String dt_consulta, String qtd_dia, String dt_atual, String nome_medico, String num_crm) {
        this.id_consulta = id_consulta;
        this.nome_paciente = nome_paciente;
        this.cpf_paciente = cpf_paciente;
        this.dt_consulta = dt_consulta;
        this.qtd_dia = qtd_dia;
        this.dt_atual = dt_atual;
        this.nome_medico = nome_medico;
        this.num_crm = num_crm;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }

    public String getCpf_paciente() {
        return cpf_paciente;
    }

    public void setCpf_paciente(String cpf_paciente) {
        this.cpf_paciente = cpf_paciente;
    }

    public String getDt_consulta() {
        return dt_consulta;
    }

    public void setDt_consulta(String dt_consulta) {
        this.dt_consulta = dt_consulta;
    }

    public String getQtd_dia() {
        return qtd_dia;
    }

    public void setQtd_dia(String qtd_dia) {
        this.qtd_dia = qtd_dia;
    }

    public String getDt_atual() {
        return dt_atual;
    }

    public void setDt_atual(String dt_atual) {
        this.dt_atual = dt_atual;
    }

    public String getNome_medico() {
        return nome_medico;
    }

    public void setNome_medico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    public String getNum_crm() {
        return num_crm;
    }

    public void setNum_crm(String num_crm) {
        this.num_crm = num_crm;
    }
}