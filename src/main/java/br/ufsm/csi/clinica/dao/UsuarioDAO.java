package br.ufsm.csi.clinica.dao;

import br.ufsm.csi.clinica.model.Permissao;
import br.ufsm.csi.clinica.util.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
/*
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    public Usuario acharUsuarioPeloNome(String usuario){

        Usuario u = null;

        try(Connection connection = new ConectaBD().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "SELECT * FROM usuario WHERE usuario = "+usuario+";";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                String user = this.rs.getString("usuario");
                String senha = this.rs.getString("senha");
                boolean ativo = this.rs.getBoolean("ativo");
                int id_permissao = this.rs.getInt("id_permissao");

                Permissao p = new PermissaoDAO().getPermissaoById(id_permissao);

                u = new Usuario(user, senha, id_permissao, ativo);
            }

            return u;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario u = null;

        try(Connection connection = new ConectaBD().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "SELECT * FROM usuario;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                String user = this.rs.getString("usuario");
                String senha = this.rs.getString("senha");
                boolean ativo = this.rs.getBoolean("ativo");
                int id_permissao = this.rs.getInt("id_permissao");

                Permissao p = new PermissaoDAO().getPermissaoById(id_permissao);

                u = new Usuario(user, senha, id_permissao, ativo);

                usuarios.add(u);
            }

            return usuarios;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public boolean cadastrarUsuario(Usuario usuario){

        try(Connection connection = new ConectaBD().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO usuario (usuario, senha, ativo, id_permissao) VALUES" +
                    " (?, ?, ?, ?);";
            //this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, usuario.getLogin());
            this.preparedStatement.setString(2, usuario.getSenha());
            this.preparedStatement.setBoolean(3, true);
            this.preparedStatement.setInt(4, usuario.getPermissao());

            if(this.preparedStatement.executeUpdate() == 1){
                connection.commit();
                return true;
            }else{
                return false;
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
*/
}
