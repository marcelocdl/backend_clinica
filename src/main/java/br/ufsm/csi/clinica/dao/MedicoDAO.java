package br.ufsm.csi.clinica.dao;

import br.ufsm.csi.clinica.model.Medico;
import br.ufsm.csi.clinica.model.Pessoa;
import br.ufsm.csi.clinica.util.ConectaBD;

import java.sql.*;
import java.util.ArrayList;

public class MedicoDAO {

    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    private int idPessoa = 0;

    public ArrayList<Medico> getMedicos(){
        ArrayList<Medico> medicos = new ArrayList<Medico>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM pessoa p JOIN medico m ON p.id_pessoa = m.id_pessoa ORDER BY m.id_medico;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Medico medico = new Medico();
                medico.setId_pessoa(this.rs.getInt("id_Pessoa"));
                medico.setNome(this.rs.getString("nome"));
                medico.setNum_cpf(this.rs.getString("num_cpf"));
                medico.setGenero(this.rs.getString("genero"));
                medico.setDt_nascimento(this.rs.getString("dt_nasc"));
                medico.setEmail(this.rs.getString("email"));
                medico.setTel_contato(this.rs.getString("tel_contato"));

                medico.setId_medico(this.rs.getInt("id_medico"));
                medico.setNum_crm(this.rs.getString("num_crm"));
                medico.setEspecialidade(this.rs.getString("especialidade"));

                medicos.add(medico);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return medicos;
    }

    public boolean cadastrarMedico(Medico medico){
        try(Connection connection = new ConectaBD().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO Pessoa (nome, num_cpf, genero, dt_nasc, email, tel_contato, id_tipo) VALUES" +
                    " (?, ?, ?, ?, ?, ?, ?);";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, medico.getNome());
            this.preparedStatement.setString(2, medico.getNum_cpf());
            this.preparedStatement.setString(3, medico.getGenero());
            this.preparedStatement.setString(4, medico.getDt_nascimento());
            this.preparedStatement.setString(5, medico.getEmail());
            this.preparedStatement.setString(6, medico.getTel_contato());
            this.preparedStatement.setInt(7, 1);

            this.preparedStatement.execute();

            this.rs = this.preparedStatement.getGeneratedKeys();
            boolean ret = rs.next();

            if(ret){
                if(this.rs.getInt(1) > 0){
                    idPessoa = this.rs.getInt(1);
                    this.status = "OK";
                }
            }

            if(this.status == "OK"){
                this.sql = "INSERT INTO medico (num_crm, especialidade, id_pessoa) VALUES " +
                        "(?, ?, ?);";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setString(1, medico.getNum_crm());
                this.preparedStatement.setString(2, medico.getEspecialidade());
                this.preparedStatement.setInt(3, idPessoa);
                if(this.preparedStatement.executeUpdate() > 0){
                    connection.commit();
                    return true;
                }
            }

            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletarMedico(int id){

        try(Connection connection = new ConectaBD().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "DELETE FROM medico m WHERE m.id_pessoa = "+id+";";
            this.preparedStatement = connection.prepareStatement(this.sql);

            if(this.preparedStatement.executeUpdate() > 0){
                System.out.println("Entrou no iif");
                this.sql = "DELETE FROM pessoa p WHERE p.id_pessoa = "+id+";";
                this.preparedStatement = connection.prepareStatement(this.sql);

                if(this.preparedStatement.executeUpdate() > 0){
                    System.out.println("entrou no commit");
                    connection.commit();
                    return true;
                }

            }else{
                return false;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarMedicos(Medico medico){

        try (Connection connection = new ConectaBD().getConexao()){

            connection.setAutoCommit(false);
            this.sql = "UPDATE pessoa SET " +
                    "nome = ?, num_cpf = ?, genero = ?, dt_nasc = ?, email = ?, tel_contato = ?" +
                    " WHERE id_pessoa = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, medico.getNome());
            this.preparedStatement.setString(2, medico.getNum_cpf());
            this.preparedStatement.setString(3, medico.getGenero());
            this.preparedStatement.setString(4, medico.getDt_nascimento());
            this.preparedStatement.setString(5, medico.getEmail());
            this.preparedStatement.setString(6, medico.getTel_contato());
            this.preparedStatement.setInt(7, medico.getId_pessoa());

            if(this.preparedStatement.executeUpdate() > 0){
                System.out.println("Entrou no if");
                this.sql = "UPDATE medico SET num_crm = ?, especialidade = ? WHERE id_pessoa = ?;";
                this.preparedStatement = connection.prepareStatement(this.sql);

                this.preparedStatement.setString(1, medico.getNum_crm());
                this.preparedStatement.setString(2, medico.getEspecialidade());
                this.preparedStatement.setInt(3, medico.getId_pessoa());

                if(this.preparedStatement.executeUpdate() > 0){
                    connection.commit();
                    return true;
                }
            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Medico getMedicoById(int id){
        Medico medico = new Medico();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT p.id_pessoa, p.nome, p.num_cpf, p.genero, p.dt_nasc, p.email, p.tel_contato " +
                    "FROM pessoa p WHERE p.id_pessoa = "+id+";";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while (rs.next()){
                medico.setId_pessoa(this.rs.getInt("id_pessoa"));
                medico.setNome(this.rs.getString("nome"));
                medico.setNum_cpf(this.rs.getString("num_cpf"));
                medico.setGenero(this.rs.getString("genero"));
                medico.setDt_nascimento(this.rs.getString("dt_nasc"));
                medico.setEmail(this.rs.getString("email"));
                medico.setTel_contato(this.rs.getString("tel_contato"));

                return medico;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return medico;
    }

}

