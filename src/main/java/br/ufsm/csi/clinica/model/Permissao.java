package br.ufsm.csi.clinica.model;

public class Permissao {

    int id_permissao;
    String tipo;

    public Permissao() {
    }

    public Permissao(int id, String tipo) {
        this.id_permissao = id;
        this.tipo = tipo;
    }

    public Permissao(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id_permissao;
    }

    public void setId(int id) {
        this.id_permissao = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
