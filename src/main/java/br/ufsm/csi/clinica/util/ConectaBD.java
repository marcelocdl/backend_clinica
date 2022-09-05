package br.ufsm.csi.clinica.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBD {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL ="jdbc:postgresql://localhost:5432/clinica";
    private static final String USER = "postgres";
    private static final String SENHA = "marcelo02";

    public Connection getConexao(){
        Connection conn = null;

        try {
            Class.forName(this.DRIVER);
            conn = DriverManager.getConnection(this.URL, this.USER, this.SENHA);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}
