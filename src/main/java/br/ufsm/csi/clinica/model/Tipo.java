package br.ufsm.csi.clinica.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_tipo;
    private String descr_tipo;

    public Tipo(String descr_tipo) {
        this.descr_tipo = descr_tipo;
    }

    public Tipo() {
    }

    public Tipo(int id_tipo){
        this.id_tipo = id_tipo;
    }

    public Tipo(int id_tipo, String descr_tipo){ this.id_tipo = id_tipo; this.descr_tipo = descr_tipo;}

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getDescr_tipo() {
        return descr_tipo;
    }

    public void setDescr_tipo(String descr_tipo) {
        this.descr_tipo = descr_tipo;
    }
}
