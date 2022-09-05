package br.ufsm.csi.clinica.dao;

import br.ufsm.csi.clinica.model.PlanoSaude;
import br.ufsm.csi.clinica.util.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PlanoSaudeDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    public Boolean cadastrarPlanoSaude(PlanoSaude planoSaude){

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "INSERT INTO plano_saude (nome, valor_consulta) VALUES (?, ?);";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, planoSaude.getNome());
            this.preparedStatement.setDouble(2, planoSaude.getValor_consulta());

            this.preparedStatement.execute();
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<PlanoSaude> getTodosPlanosSaude(){
        ArrayList<PlanoSaude> planos = new ArrayList<PlanoSaude>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM plano_saude";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                PlanoSaude plano = new PlanoSaude();
                plano.setId_plano_saude(this.rs.getInt("id_plano_saude"));
                plano.setNome(this.rs.getString("nome"));
                plano.setValor_consulta(this.rs.getDouble("valor_consulta"));

                planos.add(plano);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return planos;
    }

    public PlanoSaude getPlanoById(int id){
        PlanoSaude planoSaude = null;

        /*
        SELECT id_plano_saude as id_plano, nome as nome_plano,
            SUM(CAST(valor_consulta as decimal(10,2))) OVER (PARTITION BY id_plano_saude) as valor
          FROM plano_saude
        */

        try(Connection connection = new ConectaBD().getConexao()) {
            this.sql = "SELECT * FROM plano_saude WHERE id_plano_saude = "+id+";";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                String nome = this.rs.getString("nome");
                Double valor_consulta = this.rs.getDouble("valor_consulta");

                planoSaude = new PlanoSaude(nome, valor_consulta);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return planoSaude;
    }

    public Boolean deletarPlanoSaude(int id){
        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "DELETE FROM plano_saude WHERE id_plano_saude = ?;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);

            this.preparedStatement.execute();
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;

    }

    public boolean atualizarPlano(PlanoSaude planoSaude){

        try (Connection connection = new ConectaBD().getConexao()) {
            connection.setAutoCommit(false);
            this.sql = "UPDATE plano_saude SET " +
                    "nome = ?, valor_consulta = ?" +
                    " WHERE id_plano_saude = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, planoSaude.getNome());
            this.preparedStatement.setDouble(2, planoSaude.getValor_consulta());
            this.preparedStatement.setInt(3, planoSaude.getId_plano_saude());

            if(this.preparedStatement.executeUpdate() == 1){
                connection.commit();
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
