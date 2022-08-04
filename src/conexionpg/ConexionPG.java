package conexionpg;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionPG {
    private Connection con;
    private ResultSet rs;
    private final String HOST;
    private final String PORT;
    private final String DATABASE;
    private final String URL;
    private final String USER;
    private final String PASS;

    public ConexionPG(String HOST, String PORT, String DATABASE, String USER, String PASS) {
        this.HOST = HOST;
        this.PORT = PORT;
        this.DATABASE = DATABASE;
        this.USER = USER;
        this.PASS = PASS;
        this.URL = "jdbc:postgresql://"+HOST+":"+PORT+"/"+DATABASE;
    }
    
    public void abrirConexion(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarConexion(){
        try {
            if(con!=null) con=null;
        } catch (Exception ex) {
            Logger.getLogger(ConexionPG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCon() {
        if(con==null) abrirConexion();
        return con;
    }
    
   
    
    
    
}
