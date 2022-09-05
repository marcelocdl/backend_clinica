package br.ufsm.csi.clinica.dao;

import br.ufsm.csi.clinica.model.Permissao;
import br.ufsm.csi.clinica.util.ConectaBD;

import java.sql.*;
import java.util.ArrayList;

public class PermissaoDAO {

    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Permissao> getPermissoes() throws SQLException {
        ArrayList<Permissao> permissoes = new ArrayList<>();

        try(Connection connection = new ConectaBD().getConexao()) {

            this.sql = "SELECT * FROM permissao p ORDER BY p.id_permissao;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Permissao permissao = new Permissao();
                permissao.setId(this.rs.getInt("id_permissao"));
                permissao.setTipo(this.rs.getString("tipo"));

                permissoes.add(permissao);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return permissoes;
    }

    public Permissao getPermissaoById(int id){
        Permissao permissao = null;

        try(Connection connection = new ConectaBD().getConexao()) {
            this.sql = "SELECT * FROM permissao WHERE id_permissao = "+id+";";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                int id_p = this.rs.getInt("id_permissao");
                String tipo = this.rs.getString("tipo");

                permissao = new Permissao(id_p, tipo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return permissao;
    }
}
