package br.ufsm.csi.clinica.model;

import javax.persistence.*;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_pessoa;
    private String nome;
    private String num_cpf;
    private String genero;
    private String dt_nascimento;
    private String email;
    private String tel_contato;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;

    public Pessoa() {
    }

    public Pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public Pessoa(int id_pessoa, String nome, String num_cpf, String genero, String dt_nascimento, String email, String tel_contato) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.num_cpf = num_cpf;
        this.genero = genero;
        this.dt_nascimento = dt_nascimento;
        this.email = email;
        this.tel_contato = tel_contato;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNum_cpf() {
        return num_cpf;
    }

    public void setNum_cpf(String num_cpf) {
        this.num_cpf = num_cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(String dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel_contato() {
        return tel_contato;
    }

    public void setTel_contato(String tel_contato) {
        this.tel_contato = tel_contato;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
