package br.ufsm.csi.clinica.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlanoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_plano_saude;
    private String nome;
    private Double valor_consulta;

    public PlanoSaude() {
    }

    public PlanoSaude(int id_plano_saude) {
        this.id_plano_saude = id_plano_saude;
    }

    public PlanoSaude(String nome, Double valor_consulta) {
        this.nome = nome;
        this.valor_consulta = valor_consulta;
    }

    public PlanoSaude(int id_plano_saude, String nome, Double valor_consulta) {
        this.id_plano_saude = id_plano_saude;
        this.nome = nome;
        this.valor_consulta = valor_consulta;
    }

    public int getId_plano_saude() {
        return id_plano_saude;
    }

    public void setId_plano_saude(int id_plano_saude) {
        this.id_plano_saude = id_plano_saude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor_consulta() {
        return valor_consulta;
    }

    public void setValor_consulta(Double valor_consulta) {
        this.valor_consulta = valor_consulta;
    }
}
