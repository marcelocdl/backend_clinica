package br.ufsm.csi.clinica.dao;

import br.ufsm.csi.clinica.model.Atestado;
import br.ufsm.csi.clinica.model.Consulta;
import br.ufsm.csi.clinica.model.Medico;
import br.ufsm.csi.clinica.model.Pessoa;
import br.ufsm.csi.clinica.util.ConectaBD;
import javassist.bytecode.ByteArray;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {

    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    private int idPessoa = 0;

    public ArrayList<Consulta> getConsultas(){
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT c.id_consulta, c.id_paciente, c.id_medico, " +
                    "pm.nome as nome_medico, pm.id_pessoa as id_p_medico, " +
                    "pa.nome as nome_paciente, pa.id_pessoa as id_paciente, c.dt_consulta " +
                    "FROM consulta c " +
                    "JOIN medico m ON c.id_medico = m.id_medico " +
                    "JOIN pessoa pm ON pm.id_pessoa = m.id_pessoa " +
                    "JOIN pessoa pa ON pa.id_pessoa = c.id_paciente;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Consulta consulta = new Consulta();
                Medico medico = new Medico();
                Pessoa paciente = new Pessoa();

                medico = new MedicoDAO().getMedicoById(this.rs.getInt("id_p_medico"));
                paciente = new PessoaDAO().getPessoaById(this.rs.getInt("id_paciente"));

                consulta.setDt_consulta(rs.getString("dt_consulta"));
                consulta.setId_consulta(rs.getInt("id_consulta"));
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);

                consultas.add(consulta);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return consultas;
    }

    public boolean cadastrarConsulta(Consulta consulta){
        try(Connection connection = new ConectaBD().getConexao()){
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO Consulta (id_paciente, id_medico, dt_consulta) VALUES" +
                    " (?, ?, ?);";

            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setInt(1, consulta.getPaciente().getId_pessoa());
            this.preparedStatement.setInt(2, consulta.getMedico().getId_medico());
            this.preparedStatement.setString(3, consulta.getDt_consulta());

            if(this.preparedStatement.executeUpdate() == 1){
                connection.commit();
                return true;
            }

            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarAtestado(byte[] atestado, int id_consulta){

        try (Connection connection = new ConectaBD().getConexao()){

            System.out.println("Entrou no atualizar atestado");

            connection.setAutoCommit(false);
            this.sql = "UPDATE consulta SET " +
                    "atestado = ?" +
                    " WHERE id_consulta = "+id_consulta+";";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setBytes(1, atestado);

            if(this.preparedStatement.executeUpdate() > 0){
                connection.commit();
                return true;

            }

            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Atestado getDadosAtestado(int id){

        Atestado atestado = null;

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT co.id_consulta, to_char(to_date(co.dt_consulta,'yyyy-mm-dd'),'dd/mm/yyyy')as dt_consulta, " +
                    "pe.nome as nome_paciente, " +
                    "pe.num_cpf as cpf_paciente, to_char(current_date,'dd/mm/yyyy') as dt_atual, " +
                    "mep.nome as nome_medico, me.num_crm as num_crm " +
                    "FROM consulta co " +
                    "JOIN pessoa pe ON pe.id_pessoa = co.id_paciente " +
                    "JOIN medico me ON me.id_medico = co.id_medico " +
                    "JOIN pessoa mep ON mep.id_pessoa = me.id_pessoa " +
                    "WHERE co.id_consulta = "+id+";";

            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while (rs.next()){
                int id_consulta = (this.rs.getInt("id_consulta"));
                String nome_paciente = (this.rs.getString("nome_paciente"));
                String cpf_paciente = (this.rs.getString("cpf_paciente"));
                String dt_consulta = (this.rs.getString("dt_consulta"));
                String dt_atual = (this.rs.getString("dt_atual"));
                String nome_medico = (this.rs.getString("nome_medico"));
                String num_crm = (this.rs.getString("num_crm"));

                atestado = new Atestado(
                        id_consulta,
                        nome_paciente,
                        cpf_paciente,
                        dt_consulta,
                        dt_atual,
                        nome_medico,
                        num_crm);

                return atestado;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return atestado;
    }

}
