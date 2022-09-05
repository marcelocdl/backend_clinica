package br.ufsm.csi.clinica.dao;

import br.ufsm.csi.clinica.model.Tipo;
import br.ufsm.csi.clinica.util.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    public Boolean cadastrarTipo(Tipo tipo){

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "INSERT INTO tipo (descr_tipo) VALUES (?);";
            this.preparedStatement = connection.prepareStatement(this.sql);

            System.out.println("Tipo: "+tipo.getDescr_tipo());
            this.preparedStatement.setString(1, tipo.getDescr_tipo());

            this.preparedStatement.execute();
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Tipo> getTodosTipos(){
        ArrayList<Tipo> tipos = new ArrayList<Tipo>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM tipo";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Tipo tipo = new Tipo();
                tipo.setId_tipo(this.rs.getInt("id_tipo"));
                tipo.setDescr_tipo(this.rs.getString("descr_tipo"));

                tipos.add(tipo);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return tipos;
    }

    public Tipo getTipoById(int id){
        Tipo tipo = null;
        try(Connection connection = new ConectaBD().getConexao()) {
            this.sql = "SELECT * FROM tipo WHERE id_tipo = "+id+";";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                String descr_tipo = this.rs.getString("descr_tipo");

                tipo = new Tipo(id, descr_tipo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return tipo;
    }

}
