package br.ufsm.csi.clinica.dao;

import br.ufsm.csi.clinica.model.Pessoa;
import br.ufsm.csi.clinica.model.Tipo;
import br.ufsm.csi.clinica.util.ConectaBD;

import java.sql.*;
import java.util.ArrayList;

public class PessoaDAO {
    private int idPessoa;
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    public boolean cadastrarPessoa(Pessoa pessoa){

        try(Connection connection = new ConectaBD().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO Pessoa (nome, num_cpf, genero, dt_nasc, email, tel_contato, id_tipo) VALUES" +
                    " (?, ?, ?, ?, ?, ?, ?);";
            //this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, pessoa.getNome());
            this.preparedStatement.setString(2, pessoa.getNum_cpf());
            this.preparedStatement.setString(3, pessoa.getGenero());
            this.preparedStatement.setString(4, pessoa.getDt_nascimento());
            this.preparedStatement.setString(5, pessoa.getEmail());
            this.preparedStatement.setString(6, pessoa.getTel_contato());
            this.preparedStatement.setInt(7, pessoa.getTipo().getId_tipo());

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

    public ArrayList<Pessoa> getPessoas(){
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM Pessoa ORDER BY id_pessoa;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId_pessoa(this.rs.getInt("id_Pessoa"));
                pessoa.setNome(this.rs.getString("nome"));
                pessoa.setNum_cpf(this.rs.getString("num_cpf"));
                pessoa.setGenero(this.rs.getString("genero"));
                pessoa.setDt_nascimento(this.rs.getString("dt_nasc"));
                pessoa.setEmail(this.rs.getString("email"));
                pessoa.setTel_contato(this.rs.getString("tel_contato"));

                Tipo tipo = new Tipo();
                tipo.setId_tipo(this.rs.getInt("id_tipo"));

                pessoa.setTipo(tipo);
                pessoas.add(pessoa);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return pessoas;
    }

    public boolean excluirPessoa(int id){

        try(Connection connection = new ConectaBD().getConexao()){
            connection.setAutoCommit(false);
            this.sql = "DELETE FROM pessoa AS p WHERE p.id_pessoa = "+id+";";
            this.preparedStatement = connection.prepareStatement(this.sql);

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

    public boolean atualizarPessoa(Pessoa pessoa){

        try (Connection connection = new ConectaBD().getConexao()) {
            connection.setAutoCommit(false);
            this.sql = "UPDATE pessoa SET " +
                    "nome = ?, num_cpf = ?, genero = ?, dt_nasc = ?, email = ?, tel_contato = ?" +
                    " WHERE id_pessoa = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, pessoa.getNome());
            this.preparedStatement.setString(2, pessoa.getNum_cpf());
            this.preparedStatement.setString(3, pessoa.getGenero());
            this.preparedStatement.setString(4, pessoa.getDt_nascimento());
            this.preparedStatement.setString(5, pessoa.getEmail());
            this.preparedStatement.setString(6, pessoa.getTel_contato());
            this.preparedStatement.setInt(7, pessoa.getId_pessoa());

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

    public Pessoa getPessoaById(int id){
        Pessoa pessoa = null;

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT id_pessoa, nome, num_cpf, genero, dt_nasc, email, tel_contato " +
                        "FROM pessoa WHERE id_pessoa = "+id+";";

            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while (rs.next()){
                String nome = (this.rs.getString("nome"));
                String num_cpf = (this.rs.getString("num_cpf"));
                String genero = (this.rs.getString("genero"));
                String dt_nascimento = (this.rs.getString("dt_nasc"));
                String email = (this.rs.getString("email"));
                String tel_contato = (this.rs.getString("tel_contato"));

                pessoa = new Pessoa(id, nome, num_cpf, genero, dt_nascimento, email, tel_contato);

                return pessoa;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return pessoa;
    }

    public ArrayList<Pessoa> getPacientes(){
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT p.id_pessoa, p.nome, p.num_cpf, p.genero, p.dt_nasc, p.email, p.tel_contato " +
                    "FROM pessoa p WHERE p.id_tipo = 3 ORDER BY id_pessoa;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId_pessoa(this.rs.getInt("id_pessoa"));
                pessoa.setNome(this.rs.getString("nome"));
                pessoa.setNum_cpf(this.rs.getString("num_cpf"));
                pessoa.setGenero(this.rs.getString("genero"));
                pessoa.setDt_nascimento(this.rs.getString("dt_nasc"));
                pessoa.setEmail(this.rs.getString("email"));
                pessoa.setTel_contato(this.rs.getString("tel_contato"));

                pessoas.add(pessoa);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return pessoas;
    }

    public ArrayList<Pessoa> getSecretarias(){
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT p.id_pessoa, p.nome, p.num_cpf, p.genero, p.dt_nasc, p.email, p.tel_contato " +
                    "FROM pessoa p WHERE p.id_tipo = 2 ORDER BY id_pessoa;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId_pessoa(this.rs.getInt("id_pessoa"));
                pessoa.setNome(this.rs.getString("nome"));
                pessoa.setNum_cpf(this.rs.getString("num_cpf"));
                pessoa.setGenero(this.rs.getString("genero"));
                pessoa.setDt_nascimento(this.rs.getString("dt_nasc"));
                pessoa.setEmail(this.rs.getString("email"));
                pessoa.setTel_contato(this.rs.getString("tel_contato"));

                pessoas.add(pessoa);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return pessoas;
    }

}
